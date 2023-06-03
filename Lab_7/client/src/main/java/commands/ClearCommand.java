package commands;

import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду clear, очищяющую коллекцию
 */
public class ClearCommand extends BaseCommand {
    public ClearCommand() {
        super("clear", "очистить коллекцию");
    }

    /**
     * Метод очищает коллекцию, удаляя из неё все элементы
     * @param argument
     */
    @Override
    public String execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongCommandArgumentException();
            return "clear";
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
            return "";
        }
    }
}