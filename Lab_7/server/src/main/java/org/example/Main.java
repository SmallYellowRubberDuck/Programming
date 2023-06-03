package org.example;

import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.ConsoleManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;



public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    static int BUF_SZ = 1024;
    static int port = 8080;
    private static int poolSize = 4;

    private AbstractMap.SimpleEntry<SocketAddress, byte[]> handleRequest(DatagramPacket packet, DatagramSocket serverSocket, CommandManager commandManager) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        String newMes = new String(packet.getData(), StandardCharsets.UTF_8).trim();
        String[] logMes = newMes.split("&&&");
        String login = logMes[1];
        String[] message = logMes[0].split("~~~");
        LOGGER.info(() -> "New message received: " + newMes);
        if (message.length < 2) {
            reentrantLock.unlock();
            return (new AbstractMap.SimpleEntry<>(packet.getSocketAddress(), commandManager.doCommand(message[0], "", login).getBytes()));
        } else {
            reentrantLock.unlock();
            return (new AbstractMap.SimpleEntry<>(packet.getSocketAddress(), commandManager.doCommand(message[0], message[1], login).getBytes()));
        }
    }

    private void sendDataToClient(DatagramSocket serverSocket, AbstractMap.SimpleEntry<SocketAddress, byte[]> toSend) {
        byte[] response = toSend.getValue();
        DatagramPacket responsePacket = new DatagramPacket(response, response.length, toSend.getKey());
        try {
            serverSocket.send(responsePacket);
        } catch (IOException e) {
            System.err.println("Glitch, continuing... " + (e.getMessage() != null ? e.getMessage() : ""));
        }
    }

    private void process() {
        try {

            LOGGER.info(() -> "Подключены к базе данных");
            Scanner scanner = new Scanner(System.in);
            CollectionManager collectionManager = new CollectionManager();
            LOGGER.info("Connected to DB");
            CommandManager commandManager = new CommandManager(
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager),
                    new UpdateCommand(collectionManager),
                    new RemoveCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new ExitCommand(),
                    new AddIfMinCommand(collectionManager),
                    new RemoveGreaterCommand(collectionManager),
                    new RemoveLowerCommand(collectionManager),
                    new RemoveAllByColorCommand(collectionManager),
                    new GroupCountingByCreationDateCommand(collectionManager),
                    new FilterStartsWithNameCommand(collectionManager),
                    new AuthCommand(collectionManager)
            );
            ConsoleManager consoleManager = new ConsoleManager(collectionManager);
            DatagramSocket serverSocket = new DatagramSocket(port);
            serverSocket.setReuseAddress(true);
            Thread myThread = new Thread(consoleManager);
            myThread.start();

            Queue<DatagramPacket> packets = new LinkedList<>();
            Queue<AbstractMap.SimpleEntry<SocketAddress, byte[]>> toSend = new LinkedList();
            System.out.println(0);
            while (true) {
                ExecutorService fixedExecutor = Executors.newFixedThreadPool(poolSize);
                    fixedExecutor.execute(() -> {
                        byte[] buffer = new byte[BUF_SZ];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                                try {
                                    serverSocket.receive(packet);
                                } catch (IOException e) {
                                    System.err.println("Glitch, continuing... " + (e.getMessage() != null ? e.getMessage() : ""));
                                }
                                packets.add(packet);
                            }
                    );
                    fixedExecutor.shutdown();
                if (!packets.isEmpty()) {
                    System.out.println(2);
                    DatagramPacket pack = packets.poll();
                    if (pack == null) {
                        continue;
                    } else {
                        Thread handleThread = new Thread(() -> {toSend.add(handleRequest(pack, serverSocket, commandManager));
                        });
                        handleThread.start();
                        handleThread.interrupt();
                    }
                }
                if (!toSend.isEmpty()) {
                    System.out.println(3);
                    AbstractMap.SimpleEntry<SocketAddress, byte[]> sendingMes = toSend.poll();
                    if (sendingMes == null){
                        continue;
                    }else {
                        Thread sendThread = new Thread(() -> {
                            sendDataToClient(serverSocket, sendingMes);
                        });
                        sendThread.start();
                        sendThread.interrupt();

                    }
                }
            }
            } catch(IOException e){
                System.err.println("Network error: " + (e.getMessage() != null ? e.getMessage() : ""));
            }
    }
        public static void main(String[] args) {
        LOGGER.trace("the server is running");
        Main svr = new Main();
        svr.process();

    }

}