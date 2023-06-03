package commands;

import exceptions.WrongCommandArgumentException;

/**
 * Класс, представляющий команду exit, завершающую программу (без сохранения в файл)
 */
public class ExitCommand extends BaseCommand {

    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Метод завершает программу без сохранения коллекции в файл
     * @param argument
     */
    @Override
    public String execute(String argument, String login) {
        try {
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            return "Закрываю ваш рабочий процесс...";
        } catch (WrongCommandArgumentException ex) {
            return "Аргумент этой команды должен быть пустым!";
        }
    }
}