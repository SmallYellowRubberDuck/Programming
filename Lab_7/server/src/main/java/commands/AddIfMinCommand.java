package commands;

import data.*;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

import java.time.ZonedDateTime;

/**
 * Класс, представляющий команду add_if_min, добавляющую новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */
public class AddIfMinCommand extends BaseCommand {
    private CollectionManager collection;

    public AddIfMinCommand(CollectionManager collection) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.collection = collection;
    }


    /**
     * Метод добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     * @param argument
     */
    @Override
    public String execute(String argument, String login) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            String[] args = argument.split("~/");
            Dragon addingDragon = new Dragon(
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
            if (collection.collectionSize() == 0 || addingDragon.compareTo(collection.getFirst()) < 0) {
                collection.addToCollection(addingDragon);
                return "Элемент добавлен в коллекцию";
            } else {
                return"Значение элемента не является наименьшим среди элементов коллекции";
            }
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды не должен быть пустым!";
        }
    }
}