package Commands;

import data.Dragon;
import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.RequestManager;

import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Класс, представляющий команду add, которая добавляет новый элемент в коллекцию
 */
public class AddCommand extends BaseCommand {
    private CollectionManager collection;
    private RequestManager request;

    public AddCommand(CollectionManager collection, RequestManager request) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collection = collection;
        this.request = request;
    }

    /**
     * Метод возвращает используемый командой менеджер коллекций
     * @return collectionManager
     */
    public CollectionManager getCollection() {
        return collection;
    }

    /**
     * Метод устанавливает используемый командой менеджер запросов
     * @param request2
     */
    public void setRequest(RequestManager request2) {
        this.request = request2;
    }

    /**
     * Метод добавляет новый элемент в коллекцию
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            collection.addToCollection(new Dragon(
                    collection.generateNextId(),
                    request.requestName(),
                    request.requestCoordinates(),
                    request.requestCreationDate(),
                    request.requestAge(),
                    request.requestColor(),
                    request.requestDragonType(),
                    request.requestDragonCharacter(),
                    request.requestDragonCave()
                    ));
            System.out.println("Элемент добавлен в коллекцию");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}