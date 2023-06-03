package commands;

import data.Dragon;
import exceptions.CollectionIsEmptyException;
import exceptions.DragonIsNotFoundException;
import exceptions.WrongCommandArgumentException;
import exceptions.WrongUserRightsException;
import managers.CollectionManager;

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
    public String execute(String argument, String login) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(argument);
            Dragon removingDragon = collection.getSameId(id);
            if (removingDragon == null) throw new DragonIsNotFoundException();
            if (removingDragon.getLogin().equals(login)) throw new WrongUserRightsException();
            collection.removeFromCollection(removingDragon);
            return "Элемент удалён из коллекции";
        } catch (WrongCommandArgumentException ex) {
            return "В аргументе этой команды должен быть указан ID!";
        } catch (CollectionIsEmptyException ex) {
            return "В этой коллекции нет элементов!";
        } catch (DragonIsNotFoundException ex) {
            return "Элемент с таким ID не найден!";
        } catch (NumberFormatException ex) {
            return "В аргументе команды должно быть указано число формата Integer!";
        } catch (WrongUserRightsException ex){
            return "Вы не являетесь создателем дракона!";
        }
    }
}