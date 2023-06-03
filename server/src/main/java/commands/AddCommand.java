package commands;

import data.*;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

import java.time.ZonedDateTime;


/**
 * Класс, представляющий команду add, которая добавляет новый элемент в коллекцию
 */
public class AddCommand extends BaseCommand {
    private CollectionManager collection;

    public AddCommand(CollectionManager collection) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collection = collection;
    }

    /**
     * Метод возвращает используемый командой менеджер коллекций
     * @return collectionManager
     */
    public CollectionManager getCollection() {
        return collection;
    }


    /**
     * Метод добавляет новый элемент в коллекцию
     * @param argument
     */
    @Override
    public String execute(String argument, String login) {
        try {
            if (argument.isEmpty())  throw new WrongCommandArgumentException();
            String[] args = argument.split("~/");
            collection.addToCollection(new Dragon(
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
                    ));
            return"Элемент добавлен в коллекцию";
        } catch (WrongCommandArgumentException ex) {
            System.err.println("Аргумент этой команды не должен быть пустым!");
            return "Problem with adding new element";
        }
    }
}