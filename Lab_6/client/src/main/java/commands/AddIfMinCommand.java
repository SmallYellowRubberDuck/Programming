package commands;

import data.Coordinates;
import data.DragonCave;
import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;
import managers.RequestManager;

/**
 * Класс, представляющий команду add_if_min, добавляющую новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */
public class AddIfMinCommand extends BaseCommand {
    private RequestManager request;

    public AddIfMinCommand(RequestManager request) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
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
     * Метод добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
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
            String result = "add_if_min"+"~~~"+name+"~/"+coordinateX+"~/"+coordinateY+"~/"+creationDate+"~/"+
                            age+"~/"+color+"~/"+type+"~/"+character+"~/"+depth+"~/"+treasures;
            return result;
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
            return "";
        }
    }
}