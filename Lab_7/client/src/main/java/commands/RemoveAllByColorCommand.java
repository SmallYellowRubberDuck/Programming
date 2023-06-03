package commands;


import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;
import managers.RequestManager;

/**
 * Класс, представляет команду remove_all_by_color, которая удаляет из коллекции все элементы, значение поля color которых эквивалентно заданному
 */
public class RemoveAllByColorCommand extends BaseCommand {

    private RequestManager request;
    public RemoveAllByColorCommand(RequestManager request) {
        super("remove_all_by_color color", "удалить из коллекции все элементы, значение поля color которых эквивалентно заданному");
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
     * Метод удаляет из коллекции все элементы, значение поля color которых эквивалентно заданному
     * @param argument
     */
    @Override
    public String execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            return "remove_all_by_color"+"~~~"+argument;
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
            return "";
        }
    }
}