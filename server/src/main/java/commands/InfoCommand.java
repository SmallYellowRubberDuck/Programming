package commands;

import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

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
    public String execute(String argument, String login) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            String result = "Информация о коллекции: \n" +
                    "Тип: " + collection.collectionType() +
                    "\nКоличество элементов: " + collection.collectionSize();
            return result;
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды должен быть пустым!";
        }
    }
}