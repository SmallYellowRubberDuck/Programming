package commands;

import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду remove_by_id, удаляющую элемент из коллекции по его id
 */
public class RemoveCommand extends BaseCommand{
    public RemoveCommand() {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
    }

    /**
     * Метод удаляет элемент из коллекции по его id
     * @param argument - заданное значение id
     */
    @Override
    public String execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            return "remove_by_id"+"~~~"+argument;
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе этой команды должен быть указан ID!");
            return "";
        } catch (NumberFormatException ex) {
            ConsoleManager.printError("В аргументе команды должно быть указано число формата Integer!");
            return "";
        }
    }
}