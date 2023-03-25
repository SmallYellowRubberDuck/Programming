package Commands;

import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;

import java.time.LocalDateTime;

/**
 * Класс, представляющий команду info, выводящую в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class InfoCommand extends BaseCommand {
    private CollectionManager collection;
    public InfoCommand(CollectionManager collection) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collection = collection;
    }

    /**
     * Метод выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            System.out.println("Информация о коллекции: \n" +
                    "Тип: " + collection.collectionType() +
                    "\nКоличество элементов: " + collection.collectionSize());
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}