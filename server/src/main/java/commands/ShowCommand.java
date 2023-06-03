package commands;

import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

/**
 * Класс, предлставляющий команду show, выводящую в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand extends BaseCommand {
    private CollectionManager collection;
    public ShowCommand(CollectionManager collection) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collection = collection;
    }

    /**
     * Метод выводит в стандартный поток вывода все элементы коллекции в строковом представлении
     * @param argument
     */
    @Override
    public String execute(String argument, String login) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            return collection.toString();
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды должен быть пустым!";
        }
    }
}