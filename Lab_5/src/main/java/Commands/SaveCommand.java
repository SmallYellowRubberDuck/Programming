package Commands;

import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Класс, представляющий команду save, сохраняющий коллекцию в файл
 */
public class SaveCommand extends BaseCommand{
    private CollectionManager collection;

    public SaveCommand(CollectionManager collection) {
        super("save", "сохранить коллекцию в файл");
        this.collection = collection;
    }

    /**
     * Метод сохраняет коллекцию в файл
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongCommandArgumentException();
            collection.saveCollection();
            System.out.println("Коллекция успешно сохранена!");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}