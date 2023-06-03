package commands;


import data.*;
import exceptions.CollectionIsEmptyException;
import exceptions.DragonIsNotFoundException;
import exceptions.WrongCommandArgumentException;
import exceptions.WrongUserRightsException;
import managers.CollectionManager;

import java.time.ZonedDateTime;

/**
 * Класс, представляющий команду update id, обновляющую значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand extends BaseCommand {
    private CollectionManager collection;

    public UpdateCommand(CollectionManager collection) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collection = collection;
    }


    /**
     * Метод обновляет значение элемента коллекции, id которого равен заданному
     * @param argument - заданное значение id
     */
    @Override
    public String execute(String argument, String login) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            String[] args = argument.split("~/");
            int id = Integer.parseInt(args[0]);
            Dragon dragonOld = collection.getSameId(id);
            if (dragonOld == null) throw new DragonIsNotFoundException();
            if (dragonOld.getLogin().equals(login)) throw new WrongUserRightsException();
            String name = dragonOld.getName();
            Coordinates coordinates = dragonOld.getCoordinates();
            int coordX = coordinates.getX();
            Long coordY = coordinates.getY();
            ZonedDateTime creationDate = dragonOld.getCreationDate();
            int age = dragonOld.getAge();
            Color color = dragonOld.getColor();
            DragonType type = dragonOld.getType();
            DragonCharacter character = dragonOld.getCharacter();
            DragonCave cave = dragonOld.getCave();
            Long depth = cave.getDepth();
            int treasures = cave.getNumberOfTreasures();

            collection.removeFromCollection(dragonOld);

            if(!args[1].equals("")) name = args[1];
            if(!args[2].equals("")) coordX = Integer.parseInt(args[2]);
            if(!args[3].equals("")) coordY = Long.parseLong(args[3]);
            if(!args[4].equals("")) creationDate = ZonedDateTime.parse(args[4]);
            if(!args[5].equals("")) age = Integer.parseInt(args[5]);
            if(!args[6].equals("")) color = Color.valueOf(args[6]);
            if(!args[7].equals("")) type = DragonType.valueOf(args[7]);
            if(!args[8].equals("")) character = DragonCharacter.valueOf(args[8]);
            if(!args[9].equals("")) depth = Long.parseLong(args[9]);
            if(!args[10].equals("")) treasures = Integer.parseInt(args[10]);

            collection.addToCollection(new Dragon(
                    id,
                    name,
                    new Coordinates(coordX, coordY),
                    creationDate,
                    age,
                    color,
                    type,
                    character,
                    new DragonCave(depth, treasures),
                    login
            ));
            return "Элемент коллекции успешно обновлён!";
        } catch (WrongCommandArgumentException ex) {
            return "В аргументе этой команды должен быть указан ID!";
        } catch (CollectionIsEmptyException ex) {
            return "В этой коллекции нет элементов!";
        } catch (DragonIsNotFoundException ex) {
            return "Элемент с таким ID не найден!";
        } catch (NumberFormatException ex) {
            return "В аргументе команды должно быть указано число другого формата!";
        } catch (WrongUserRightsException ex){
            return "Вы не являетесь создателем дракона!";
        }
    }
}