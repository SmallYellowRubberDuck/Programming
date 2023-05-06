package commands;


import data.Color;
import data.Dragon;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

import java.util.TreeSet;

/**
 * Класс, представляет команду remove_all_by_color, которая удаляет из коллекции все элементы, значение поля color которых эквивалентно заданному
 */
public class RemoveAllByColorCommand extends BaseCommand {
    private CollectionManager collection;
    public RemoveAllByColorCommand(CollectionManager collection) {
        super("remove_all_by_color color", "удалить из коллекции все элементы, значение поля color которых эквивалентно заданному");
        this.collection = collection;
    }

    /**
     * Метод удаляет из коллекции все элементы, значение поля color которых эквивалентно заданному
     * @param argument
     */
    @Override
    public String execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            if(collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            Color checkingColor = Color.valueOf(argument);
            TreeSet<Dragon> removingDragons = new TreeSet<>();
            String result = "Удален следующий список элементов: ";
            for (Dragon dragon : collection.getCollection()) {
                if (dragon.getColor().equals(checkingColor)) {
                    result = result + ("Элемент коллекции № " + dragon.getId()+"\n");
                    removingDragons.add(dragon);
                }
            }
            for (Dragon removingDragon : removingDragons) {
                collection.removeFromCollection(removingDragon);
            }
            return result;
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды должен быть пустым!";
        } catch (CollectionIsEmptyException ex) {
            return "В коллекции нет элементов!";
        }
    }
}