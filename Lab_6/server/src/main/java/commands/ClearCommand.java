package commands;

import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

/**
 * Класс, представляющий команду clear, очищяющую коллекцию
 */
public class ClearCommand extends BaseCommand {
    private CollectionManager collection;
    public ClearCommand(CollectionManager collection) {
        super("clear", "очистить коллекцию");
        this.collection = collection;
    }

    /**
     * Метод очищает коллекцию, удаляя из неё все элементы
     * @param argument
     */
    @Override
    public String execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongCommandArgumentException();
            collection.clearCollection();
            return "Коллекция очищена";
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды должен быть пустым!";
        }
    }
}