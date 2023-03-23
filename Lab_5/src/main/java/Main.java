
import Commands.*;
import Containers.CollectionStore;
import DAO.DragonDao;
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
        CommandManager commandManager = new CommandManager(
                helpCommand,
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, requestManager),
                new UpdateCommand(collectionManager, requestManager),
                new RemoveCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new AddIfMinCommand(collectionManager, requestManager),
                new RemoveGreaterCommand(collectionManager, requestManager),
                new RemoveLowerCommand(collectionManager,requestManager),
                new RemoveAllByColorCommand(collectionManager, requestManager),
                new GroupCountingByCreationDateCommand(collectionManager),
                new FilterStartsWithNameCommand(collectionManager)
        );
        helpCommand.setManager(commandManager);
        ConsoleManager consoleManager = new ConsoleManager(commandManager, scanner);
        consoleManager.interactiveMode();
    }
}