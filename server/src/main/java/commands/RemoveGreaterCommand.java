package commands;

import data.*;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

import java.time.ZonedDateTime;

/**
 * Класс, представляющий команду remove_greater, удаляющую из коллекции все элементы, большие, чем заданный
 */
public class RemoveGreaterCommand extends BaseCommand {
    private CollectionManager collection;

    public RemoveGreaterCommand(CollectionManager collection) {
        super("remove_greater {element}", "удалить из коллекции все элементы, большие, чем заданный");
        this.collection = collection;
    }



    /**
     * Метод удаляет из коллекции все элементы, большие, чем заданный
     * @param argument
     */
    @Override
    public String execute(String argument, String login) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            String[] args = argument.split("~/");
            Dragon comparableDragon = new Dragon(
                    collection.generateNextId(),
                    args[0],
                    new Coordinates(Integer.parseInt(args[1]), Long.parseLong(args[2])),
                    ZonedDateTime.parse(args[3]),
                    Integer.parseInt(args[4]),
                    Color.valueOf(args[5]),
                    DragonType.valueOf(args[6]),
                    DragonCharacter.valueOf(args[7]),
                    new DragonCave(Long.parseLong(args[8]),Integer.parseInt(args[9])),
                    login
            );
            collection.removeGreater(comparableDragon);
            return "Элементы, большие заданного, успешно удалены!";
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды не должен быть пустым!";
        } catch (CollectionIsEmptyException ex) {
            return "В этой коллекции нет элементов!";
        }
    }
}