
import Commands.*;
import Managers.*;


import java.util.Scanner;

import static java.lang.System.getenv;

public class Main {
    public static void main(String[] args) {
        String envVar = getenv("laba5");
        System.out.println("Добро пожаловать!");
        System.out.println("Используемый файл: " + envVar);
        Scanner scanner = new Scanner(System.in);
        RequestManager requestManager = new RequestManager(scanner);
        CollectionManager collectionManager = new CollectionManager();
        HelpCommand helpCommand = new HelpCommand();
        CommandManager commandManager = new CommandManager();
        commandManager.addCommand(helpCommand);
        commandManager.addCommand(new InfoCommand(collectionManager));
        commandManager.addCommand(new ShowCommand(collectionManager));
        commandManager.addCommand(new AddCommand(collectionManager, requestManager));
        commandManager.addCommand(new UpdateCommand(collectionManager, requestManager));
        commandManager.addCommand(new RemoveCommand(collectionManager));
        commandManager.addCommand(new ClearCommand(collectionManager));
        commandManager.addCommand(new SaveCommand(collectionManager));
        commandManager.addCommand(new ExecuteScriptCommand());
        commandManager.addCommand(new ExitCommand());
        commandManager.addCommand(new AddIfMinCommand(collectionManager, requestManager));
        commandManager.addCommand(new RemoveGreaterCommand(collectionManager, requestManager));
        commandManager.addCommand(new RemoveLowerCommand(collectionManager,requestManager));
        commandManager.addCommand(new RemoveAllByColorCommand(collectionManager, requestManager));
        commandManager.addCommand(new GroupCountingByCreationDateCommand(collectionManager));
        commandManager.addCommand(new FilterStartsWithNameCommand(collectionManager));
        helpCommand.setManager(commandManager);
        ConsoleManager consoleManager = new ConsoleManager(commandManager, scanner);
        consoleManager.interactiveMode();
    }
}