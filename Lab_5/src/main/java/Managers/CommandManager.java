package Managers;
import Commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, управляющий командами
 */
public class CommandManager {
    private List<BaseCommand> CommandList = new ArrayList<>();

    /**
     * Метод, возвращающий список всех команд
     * @return commandList
     */
    public List<BaseCommand> getCommandList() {
        return CommandList;
    }

    public void addCommand(BaseCommand baseCommand){
        CommandList.add(baseCommand);
    }

    /**
     * Метод, выполняющий введённую команду
     * @param inputCommand (введённая команда)
     * @param argument (аргумент этой команды)
     */
    public void doCommand(String inputCommand, String argument) {
        boolean flag = true;
        for (BaseCommand BaseCommand : CommandList) {
            if (BaseCommand.getName().split(" ")[0].equals(inputCommand)) {
                BaseCommand.execute(argument);
                flag = false;
            }
        }
        if (flag) System.out.println("Такой команды не существует!");
    }
}