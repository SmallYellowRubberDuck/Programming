package commands;

import data.*;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

import java.time.ZonedDateTime;

/**
 * Класс, представляющий команду remove_lower, удаляющую из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLowerCommand extends BaseCommand {
    private CollectionManager collection;

    public RemoveLowerCommand(CollectionManager collection) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.collection = collection;
    }


    /**
     * Метод удаляет из коллекции все элементы, меньшие, чем заданный
     * @param argument
     */
    @Override
    public String execute(String argument) {
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
                    new DragonCave(Long.parseLong(args[8]),Integer.parseInt(args[9]))
            );
            collection.removeLower(comparableDragon);
            return "Элементы, меньшие заданного, успешно удалены!";
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды не должен быть пустым!";
        } catch (CollectionIsEmptyException ex) {
            return "В этой коллекции нет элементов!";
        }
    }
}