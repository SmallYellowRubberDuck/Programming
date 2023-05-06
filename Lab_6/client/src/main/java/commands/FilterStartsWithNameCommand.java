package commands;

import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду filter_starts_with_name, выводящую элементы, значение поля name которых начинается с заданной подстроки
 */
public class FilterStartsWithNameCommand extends BaseCommand {

    public FilterStartsWithNameCommand() {
        super("filter_starts_with_name name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
    }

    /**
     * Метод выводит элементы, значение поля name которых начинается с заданной подстроки
     * @param argument - заданная подстрока
     */
    @Override
    public String execute(String argument) {
        try {
            if(argument.isEmpty()) throw new WrongCommandArgumentException();
            return "filter_starts_with_name"+"~~~"+argument;
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе данной команды должно содержаться название элемента!");
            return "";
        }
    }
}