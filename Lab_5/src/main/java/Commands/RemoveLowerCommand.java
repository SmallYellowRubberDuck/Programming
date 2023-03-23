package Commands;

import data.Dragon;
import Exceptions.CollectionIsEmptyException;
import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.RequestManager;

import java.time.LocalDateTime;

/**
 * Класс, представляющий команду remove_lower, удаляющую из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLowerCommand extends BaseCommand {
    private CollectionManager collection;
    private RequestManager request;

    public RemoveLowerCommand(CollectionManager collection, RequestManager request) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
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
     * Метод удаляет из коллекции все элементы, меньшие, чем заданный
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            Dragon comparableDragon = new Dragon(
                    collection.generateNextId(),
                    request.requestName(),
                    request.requestCoordinates(),
                    request.requestCreationDate(),
                    request.requestAge(),
                    request.requestColor(),
                    request.requestDragonType(),
                    request.requestDragonCharacter(),
                    request.requestDragonCave()
            );
            collection.removeLower(comparableDragon);
            System.out.println("Элементы, меньшие заданного, успешно удалены!");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В этой коллекции нет элементов!");
        }
    }
}