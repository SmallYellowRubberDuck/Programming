package Commands;


import data.Dragon;
import data.Color;
import Exceptions.CollectionIsEmptyException;
import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.RequestManager;

import java.util.TreeSet;

/**
 * Класс, представляет команду remove_all_by_color, которая удаляет из коллекции все элементы, значение поля color которых эквивалентно заданному
 */
public class RemoveAllByColorCommand extends BaseCommand {
    private CollectionManager collection;
    private RequestManager request;
    public RemoveAllByColorCommand(CollectionManager collection, RequestManager request) {
        super("remove_all_by_color color", "удалить из коллекции все элементы, значение поля color которых эквивалентно заданному");
        this.collection = collection;
        this.request = request;
    }
    /**
     * Метод устанавливает используемый командой менеджер запросов
     * @param request2
     */
    public void setRequest(RequestManager request2) {
        this.request = request2;
    }

    /**
     * Метод удаляет из коллекции все элементы, значение поля color которых эквивалентно заданному
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            if(collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            Color checkingColor = request.requestColor();
            TreeSet<Dragon> removingDragons = new TreeSet<>();
            for (Dragon dragon : collection.getCollection()) {
                if (dragon.getColor().equals(checkingColor)) {
                    System.out.println("Элемент коллекции № " + dragon.getId() + " удален");
                    removingDragons.add(dragon);
                }
            }
            for (Dragon removingDragon : removingDragons) {
                collection.removeFromCollection(removingDragon);
            }
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В коллекции нет элементов!");
        }
    }
}