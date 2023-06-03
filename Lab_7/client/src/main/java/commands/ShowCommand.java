package commands;

import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;

/**
 * Класс, предлставляющий команду show, выводящую в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand extends BaseCommand {
    public ShowCommand() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    /**
     * Метод выводит в стандартный поток вывода все элементы коллекции в строковом представлении
     * @param argument
     */
    @Override
    public String execute(String argument) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            return "show"+"~~~";
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
            return "";
        }
    }
}