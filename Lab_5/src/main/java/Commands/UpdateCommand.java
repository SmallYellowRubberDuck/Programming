package Commands;


import data.*;
import Exceptions.CollectionIsEmptyException;
import Exceptions.DragonIsNotFoundException;
import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.RequestManager;

import java.time.ZonedDateTime;

/**
 * Класс, представляющий команду update id, обновляющую значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand extends BaseCommand {
    private CollectionManager collection;
    private RequestManager request;

    public UpdateCommand(CollectionManager collection, RequestManager request) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
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
     * Метод обновляет значение элемента коллекции, id которого равен заданному
     * @param argument - заданное значение id
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(argument);
            Dragon dragonOld = collection.getSameId(id);
            if (dragonOld == null) throw new DragonIsNotFoundException();

            String name = dragonOld.getName();
            Coordinates coordinates = dragonOld.getCoordinates();
            ZonedDateTime creationDate = dragonOld.getCreationDate();
            int age = dragonOld.getAge();
            Color color = dragonOld.getColor();
            DragonType type = dragonOld.getType();
            DragonCharacter character = dragonOld.getCharacter();
            DragonCave cave = dragonOld.getCave();

            collection.removeFromCollection(dragonOld);

            if(request.requestUpdateQuestion("Хотите изменить имя?")) name = request.requestName();
            if(request.requestUpdateQuestion("Хотите изменить координаты?")) coordinates = request.requestCoordinates();
            if(request.requestUpdateQuestion("Хотите изменить время создания?")) creationDate = ZonedDateTime.now();
            if(request.requestUpdateQuestion("Хотите изменить возраст?")) age = request.requestAge();
            if(request.requestUpdateQuestion("Хотите изменить цвет?")) color = request.requestColor();
            if(request.requestUpdateQuestion("Хотите изменить тип?")) type = request.requestDragonType();
            if(request.requestUpdateQuestion("Хотите изменить характер?")) character = request.requestDragonCharacter();
            if(request.requestUpdateQuestion("Хотите изменить пещеру?")) cave = request.requestDragonCave();

            collection.addToCollection(new Dragon(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    age,
                    color,
                    type,
                    character,
                    cave
            ));
            System.out.println("Элемент коллекции успешно обновлён!");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе этой команды должен быть указан ID!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В этой коллекции нет элементов!");
        } catch (DragonIsNotFoundException ex) {
            ConsoleManager.printError("Элемент с таким ID не найден!");
        } catch (NumberFormatException ex) {
            ConsoleManager.printError("В аргументе команды должно быть указано число другого формата!");
        }
    }
}