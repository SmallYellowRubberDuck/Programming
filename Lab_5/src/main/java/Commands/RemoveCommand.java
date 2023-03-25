package Commands;

import Exceptions.CollectionIsEmptyException;
import Exceptions.DragonIsNotFoundException;
import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import data.Dragon;

/**
 * Класс, представляющий команду remove_by_id, удаляющую элемент из коллекции по его id
 */
public class RemoveCommand extends BaseCommand{
    private CollectionManager collection;
    public RemoveCommand(CollectionManager collection) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.collection = collection;
    }

    /**
     * Метод удаляет элемент из коллекции по его id
     * @param argument - заданное значение id
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(argument);
            Dragon removingDragon = collection.getSameId(id);
            if (removingDragon == null) throw new DragonIsNotFoundException();
            collection.removeFromCollection(removingDragon);
            System.out.println("Элемент удалён из коллекции");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе этой команды должен быть указан ID!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В этой коллекции нет элементов!");
        } catch (DragonIsNotFoundException ex) {
            ConsoleManager.printError("Элемент с таким ID не найден!");
        } catch (NumberFormatException ex) {
            ConsoleManager.printError("В аргументе команды должно быть указано число формата Integer!");
        }
    }
}