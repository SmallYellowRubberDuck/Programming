package commands;

import data.*;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

import java.time.ZonedDateTime;

public class AuthCommand extends BaseCommand {
    private CollectionManager collection;

    public AuthCommand(CollectionManager collection) {
        super("auth {element}", "аутентификация клиента");
        this.collection = collection;
    }

    /**
     * Метод возвращает используемый командой менеджер коллекций
     *
     * @return collectionManager
     */
    public CollectionManager getCollection() {
        return collection;
    }


    /**
     * Метод проводит аутентификацию пользователя
     *
     * @param argument
     */
    @Override
    public String execute(String argument, String login) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            String[] args = argument.split("~/");
            switch (args[1]) {
                case "1":
                    return (collection.checkUser(login, args[0]));
                case "0":
                    return (collection.addUser(login, args[0]));
                default:
                    return ("0");
            }
        } catch (WrongCommandArgumentException ex) {
            System.err.println("Аргумент этой команды не должен быть пустым!");
            return "Problem with adding new element";
        }
    }
}
