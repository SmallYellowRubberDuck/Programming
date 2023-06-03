package org.example;

import commands.*;
import managers.CommandManager;
import managers.ConsoleManager;
import managers.RequestManager;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Main {
    private static int BUF_SZ = 1024;
    private static String login;
    private static String password;
    public static void sendMessage(DatagramChannel client, String msg, SocketAddress serverAddress) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        client.send(buffer, serverAddress);
    }
    public static void receiveMessage(DatagramChannel client) throws IOException, InterruptedException {
        int k = 0;
        while (true) {
            if (k>70){
                System.err.println("Server is unavailable");
                client.close();
                System.out.println("Stopping the app");
                System.exit(1);
            }
            Thread.sleep(100);
            ByteBuffer buffer = ByteBuffer.allocate(BUF_SZ);
            SocketAddress remoteAdd = client.receive(buffer);
            String message = extractMessage(buffer);
            if (message.length() < 2) {
                if (message.equalsIgnoreCase("0")) {
                    System.out.println("Ошибка авторизации");
                    client.close();
                    System.exit(1);
                }
                if (message.equalsIgnoreCase("1")){
                    System.out.println("Авторизация прошла успешно");
                    break;
                }
                k++;
                continue;
            } else {
                System.out.println("Server at #" + remoteAdd + "  sent: " + message);
                break;
            }
        }
    }
    private static String extractMessage(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String msg = new String(bytes);
        return msg;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int i = 0;
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8080);
        try {
            InetSocketAddress address = new InetSocketAddress("localhost", 65000);
            datagramChannel.bind(address);
        }catch (BindException e){
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        RequestManager requestManager = new RequestManager(scanner);
        HelpCommand helpCommand = new HelpCommand();
        CommandManager commandManager = new CommandManager(
                helpCommand,
                new InfoCommand(),
                new ShowCommand(),
                new AddCommand(requestManager),
                new UpdateCommand(requestManager),
                new RemoveCommand(),
                new ClearCommand(),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new AddIfMinCommand(requestManager),
                new RemoveGreaterCommand(requestManager),
                new RemoveLowerCommand(requestManager),
                new RemoveAllByColorCommand(requestManager),
                new GroupCountingByCreationDateCommand(),
                new FilterStartsWithNameCommand()
        );
        helpCommand.setManager(commandManager);
        boolean key = requestManager.logIn("Вы хотите авторизоваться или зарегистрироваться?");
        String str = requestManager.askPassword(key);
        login = str.split("&&&")[1];
        ConsoleManager consoleManager = new ConsoleManager(commandManager, scanner, datagramChannel, serverAddress, login);
        sendMessage(datagramChannel,str,serverAddress);
        receiveMessage(datagramChannel);
        consoleManager.interactiveMode();
    }
}