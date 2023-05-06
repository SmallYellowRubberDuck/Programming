package org.example;

import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.ConsoleManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.getenv;

public class Main{
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    static int BUF_SZ = 8192;

    class Con {
        ByteBuffer req;
        ByteBuffer resp;
        SocketAddress sa;

        public Con() {
            req = ByteBuffer.allocate(BUF_SZ);
        }
    }

    static int port = 8080;
    private void process() {
        try {
            String envVar = getenv("laba5");
            LOGGER.info(()->"Используемый файл: " + envVar);
            Scanner scanner = new Scanner(System.in);
            CollectionManager collectionManager = new CollectionManager();
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
                    new FilterStartsWithNameCommand(collectionManager)
            );
            ConsoleManager consoleManager = new ConsoleManager(collectionManager);
            Selector selector = Selector.open();
            Thread myThread = new Thread(consoleManager);
            myThread.start();
            DatagramChannel channel = DatagramChannel.open();
            InetSocketAddress isa = new InetSocketAddress("localhost",port);
            LOGGER.info(() -> "set " + isa + " address");
            channel.socket().bind(isa);
            channel.configureBlocking(false);
            SelectionKey clientKey = channel.register(selector, SelectionKey.OP_READ);
            clientKey.attach(new Con());
            while (true) {
                try {
                        selector.select();
                        Iterator selectedKeys = selector.selectedKeys().iterator();
                        while (selectedKeys.hasNext()) {
                            try {
                                SelectionKey key = (SelectionKey) selectedKeys.next();
                                selectedKeys.remove();
                                if (!key.isValid()) {
                                    continue;
                                }

                                if (key.isReadable()) {
                                    String[] message = read(key).split("~~~");
                                    if (message.length < 2) {
                                        ((Con) key.attachment()).resp = ByteBuffer.wrap(commandManager.doCommand(message[0], "").getBytes());
                                    } else
                                        ((Con) key.attachment()).resp = ByteBuffer.wrap(commandManager.doCommand(message[0], message[1]).getBytes());
                                    key.interestOps(SelectionKey.OP_WRITE);
                                } else if (key.isWritable()) {
                                    write(key);
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            } catch (IOException e) {
                                System.err.println("Glitch, continuing... " + (e.getMessage() != null ? e.getMessage() : ""));
                            }
                        }
                } catch (IOException e) {
                    System.err.println("Glitch, continuing... " +(e.getMessage()!=null?e.getMessage():""));
                }
            }
        } catch (IOException e) {
            System.err.println("Network error: " + (e.getMessage()!=null?e.getMessage():""));
        }
    }

    private String read(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel) key.channel();
        Con con = (Con) key.attachment();
        con.sa = chan.receive(con.req);
        String message = new String(con.req.array(), "UTF-8").trim();
        LOGGER.info(()->"New message received: " + message);
        String str = new String("");
        if (con.req != null) {
            con.req = ByteBuffer.allocate(BUF_SZ);
        }
        con.resp = ByteBuffer.wrap(str.getBytes());
        return message;
    }

    private void write(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel) key.channel();
        Con con = (Con) key.attachment();
        if (con.resp.hasRemaining()) {
            chan.send(con.resp, con.sa);
            System.out.println(">>>>MESSAGE SENT TO " + (con.sa.toString()) + ": " + new String(con.resp.array(), "UTF-8").trim());
            if (new String(con.resp.array())=="Закрываю ваш рабочий процесс..."){
                chan.close();
            }
        }
    }


    static public void main(String[] args) {
        LOGGER.trace("the server is running");
        Main svr = new Main();
        svr.process();

    }

}