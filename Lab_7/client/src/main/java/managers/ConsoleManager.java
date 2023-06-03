package managers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

import static org.example.Main.receiveMessage;
import static org.example.Main.sendMessage;

/**
 * Класс, управляющий чтением введённых в консоль команд
 */
public class ConsoleManager {
    private CommandManager commandManager;
    private Scanner scanner;
    private InetSocketAddress serverAddress;
    private DatagramChannel datagramChannel;
    private String login;
    private static int scriptCount = 0;
    private Scanner previousScanner;

    public ConsoleManager(CommandManager commandManager, Scanner scanner, DatagramChannel datagramChannel, InetSocketAddress serverAddress, String login) {
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.datagramChannel = datagramChannel;
        this.serverAddress = serverAddress;
        this.login = login;
    }

    /**
     * Метод используется для считывания и выполнения команд, введённых в консоль построчно
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        try {
            while (true) {
                System.out.print(">>> ");
                try {
                    userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    String input = commandManager.doCommand(userCommand[0], userCommand[1]);
                    if (userCommand[0].equals("execute_script")) {
                        scriptCount += 1;
                        Scanner sc = new Scanner(new FileReader(userCommand[1]));
                        scriptMode(sc);
                    }
                    if (input.equals("")){continue;}
                    sendMessage(datagramChannel, input+"&&&"+login, serverAddress);
                    receiveMessage(datagramChannel);
                    if (input.equalsIgnoreCase("exit")) {
                        System.out.println("Client is closed");
                        datagramChannel.close();
                        System.exit(1);
                    }
                } catch (NullPointerException ex) {
                    printError("ctrl d");
                    break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException exc) {
            printError("Операция ввода была прервана!");
        }
    }

    /**
     * Метод используется для считывания и выполнения скрипта
     * @param sc reader файла со скриптом
     */
    public void scriptMode(Scanner sc) {
        String[] userCommand = {"", ""};
        try {
            RequestManager rm = new RequestManager(sc);
            while (true) {
                    try {
                        userCommand = (sc.nextLine().trim() + " ").split(" ", 2);
                        userCommand[1] = userCommand[1].trim();
                        System.out.println(">>>" + userCommand[0] + " " + userCommand[1]);
                        if (userCommand[0].equals("add")) commandManager.setAddCommand(rm);
                        else if (userCommand[0].equals("update")) commandManager.setUpdateIdCommand(rm);
                        else if (userCommand[0].equals("add_if_min")) commandManager.setAddIfMinCommand(rm);
                        else if (userCommand[0].equals("remove_lower")) commandManager.setRemoveLowerCommand(rm);
                        else if (userCommand[0].equals("remove_greater")) commandManager.setRemoveGreaterCommand(rm);
                        else if (userCommand[0].equals("remove_all_by_color")) commandManager.setRemoveAllByColorCommand(rm);
                        if (userCommand[0].equals("exit")) break;
                        String input = commandManager.doCommand(userCommand[0], userCommand[1]);
                        if (!(input.length()<1)) {
                            sendMessage(datagramChannel, input+"&&&"+login, serverAddress);
                            receiveMessage(datagramChannel);
                        }
                        if (userCommand[0].equals("execute_script")) {
                            scriptCount += 1;
                            previousScanner = sc;
                            Scanner sc2 = new Scanner(new FileReader(userCommand[1]));
                            scriptMode(sc2);
                        }
                    } catch (NullPointerException ex) {
                        break;
                    }
                }
                System.out.println("$\nСкрипт успешно выполнен!");
                scriptCount -= 1;
                if (scriptCount >= 1) {
                    scriptMode(previousScanner);
                }
                interactiveMode();
            } catch(FileNotFoundException ex){
                printError("Файл с таким именем не найден!");
            } catch(IOException | InterruptedException ex){
                printError("Операция ввода была прервана! Проверьте указанный путь до файла.");
            }
        }

    /**
     * Метод выводит ошибки в строковом представлении
     * @param argument описание ошибки в строковом представлении
     */
    public static void printError(String argument) {
        System.out.println("ОШИБКА: " + argument);
    }
}