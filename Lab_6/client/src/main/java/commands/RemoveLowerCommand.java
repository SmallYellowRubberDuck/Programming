package commands;

import data.Coordinates;
import data.DragonCave;
import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;
import managers.RequestManager;

/**
 * Класс, представляющий команду remove_lower, удаляющую из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLowerCommand extends BaseCommand {
    private RequestManager request;

    public RemoveLowerCommand(RequestManager request) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
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
    public String execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongCommandArgumentException();
            String name = request.requestName();
            Coordinates coordinates = request.requestCoordinates();
            String coordinateX = coordinates.getX().toString();
            String coordinateY = ((Long)coordinates.getY()).toString();
            String creationDate = request.requestCreationDate().toString();
            String age = ((Integer)request.requestAge()).toString();
            String color = request.requestColor().toString();
            String type = request.requestDragonType().toString();
            String character = request.requestDragonCharacter().toString();
            DragonCave cave = request.requestDragonCave();
            String depth = cave.getDepth().toString();
            String treasures = ((Integer)cave.getNumberOfTreasures()).toString();
            String result = "remove_lower"+"~~~"+name+"~/"+coordinateX+"~/"+coordinateY+"~/"+creationDate+"~/"+
                    age+"~/"+color+"~/"+type+"~/"+character+"~/"+depth+"~/"+treasures;
            return result;
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
            return "";
        }
    }
}