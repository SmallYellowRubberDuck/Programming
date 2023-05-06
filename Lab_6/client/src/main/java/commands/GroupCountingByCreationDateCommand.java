package commands;

import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;

/**
 * Класс используется для группировки элементов коллекции по дате создания элемента
 */
public class GroupCountingByCreationDateCommand extends BaseCommand {

    public GroupCountingByCreationDateCommand() {
        super("group_counting_by_creation_date", "сгруппировать элементы по дате их создания");
    }

    @Override
    public String execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            return "group_counting_by_creation_date"+"~~~"+argument;
        } catch (WrongCommandArgumentException e){
            ConsoleManager.printError("Аргументов быть не должно в '" + getName() + "'");
            return "";
        }
    }
}