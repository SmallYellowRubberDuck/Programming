package commands;


import data.*;
import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;
import managers.RequestManager;

import java.time.ZonedDateTime;

/**
 * Класс, представляющий команду update id, обновляющую значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand extends BaseCommand {
    private RequestManager request;

    public UpdateCommand(RequestManager request) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
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
    public String execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();

            String Name;
            Coordinates Coordinates;
            ZonedDateTime CreationDate;
            int Age;
            Color Color;
            DragonType Type;
            DragonCharacter Character;
            DragonCave Cave;

            String name = "";
            String coordinateX = "";
            String coordinateY = "";
            String creationDate = "";
            String age = "";
            String color = "";
            String type = "";
            String character = "";
            String depth = "";
            String treasures = "";

            if(request.requestUpdateQuestion("Хотите изменить имя?")) {
                Name = request.requestName();
                name = Name;
            }
            if(request.requestUpdateQuestion("Хотите изменить координаты?")) {
                Coordinates = request.requestCoordinates();
                coordinateX = Coordinates.getX().toString();
                coordinateY = ((Long)Coordinates.getY()).toString();
            }
            if(request.requestUpdateQuestion("Хотите изменить время создания?")){
                CreationDate = ZonedDateTime.now();
                creationDate = CreationDate.toString();
            }
            if(request.requestUpdateQuestion("Хотите изменить возраст?")){
                Age = request.requestAge();
                age = ((Integer)Age).toString();
            }
            if(request.requestUpdateQuestion("Хотите изменить цвет?")){
                Color = request.requestColor();
                color = Color.toString();
            }
            if(request.requestUpdateQuestion("Хотите изменить тип?")){
                Type = request.requestDragonType();
                type = Type.toString();
            }
            if(request.requestUpdateQuestion("Хотите изменить характер?")){
                Character = request.requestDragonCharacter();
                character = Character.toString();
            }
            if(request.requestUpdateQuestion("Хотите изменить пещеру?")){
                Cave = request.requestDragonCave();
                depth = Cave.getDepth().toString();
                treasures = ((Integer)Cave.getNumberOfTreasures()).toString();
            }


            String result = "update"+"~~~"+argument+"~/"+name+"~/"+coordinateX+"~/"+coordinateY+"~/"+creationDate+"~/"+
                    age+"~/"+color+"~/"+type+"~/"+character+"~/"+depth+"~/"+treasures;
            return result;
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе этой команды должен быть указан ID!");
            return "";
        } catch (NumberFormatException ex) {
            ConsoleManager.printError("В аргументе команды должно быть указано число другого формата!");
            return "";
        }
    }
}