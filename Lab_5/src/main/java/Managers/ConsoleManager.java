package Managers;

import data.Dragon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Класс, управляющий чтением введённых в консоль команд
 */
public class ConsoleManager {
    private CommandManager commandManager;
    private Scanner scanner;
    private static int scriptCount = 0;
    private Scanner previousScanner;

    public ConsoleManager(CommandManager commandManager, Scanner scanner) {
        this.commandManager = commandManager;
        this.scanner = scanner;
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
                    commandManager.doCommand(userCommand[0], userCommand[1]);
                    if (userCommand[0].equals("exit")) break;
                    if (userCommand[0].equals("execute_script")) {
                        scriptCount += 1;
                        Scanner sc = new Scanner(new FileReader(userCommand[1]));
                        scriptMode(sc);
                    }
                } catch (NullPointerException ex) {
                    printError("ctrl d");
                    break;
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
                    if (userCommand[0].equals("exit")) break;
                    commandManager.doCommand(userCommand[0], userCommand[1]);
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
        } catch (FileNotFoundException ex) {
            printError("Файл с таким именем не найден!");
        } catch (IOException ex) {
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