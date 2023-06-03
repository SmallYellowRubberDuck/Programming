package managers;
import commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, управляющий командами
 */
public class CommandManager {
    private List<BaseCommand> CommandList = new ArrayList<>();
    private BaseCommand infoCommand;
    private BaseCommand showCommand;
    private AddCommand addCommand;
    private UpdateCommand updateCommand;
    private BaseCommand removeByIdCommand;
    private BaseCommand clearCommand;
    private BaseCommand exitCommand;
    private AddIfMinCommand addIfMinCommand;
    private RemoveGreaterCommand removeGreaterCommand;
    private RemoveLowerCommand removeLowerCommand;
    private RemoveAllByColorCommand removeAllByColorCommand;
    private BaseCommand groupCountingByCreationDateCommand;
    private BaseCommand filterStartsWithNameCommand;
    private BaseCommand authCommand;

    public CommandManager(BaseCommand infoCommand, BaseCommand showCommand,
                          AddCommand addCommand, UpdateCommand updateCommand, BaseCommand removeByIdCommand,
                          BaseCommand clearCommand, BaseCommand exitCommand, AddIfMinCommand addIfMinCommand,
                          RemoveGreaterCommand removeGreaterCommand, RemoveLowerCommand removeLowerCommand,
                          RemoveAllByColorCommand removeAllByColorCommand, BaseCommand groupCountingByCreationDateCommand,
                          BaseCommand filterStartsWithNameCommand, BaseCommand authCommand) {
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.exitCommand = exitCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.removeAllByColorCommand = removeAllByColorCommand;
        this.groupCountingByCreationDateCommand = groupCountingByCreationDateCommand;
        this.filterStartsWithNameCommand = filterStartsWithNameCommand;
        this.authCommand = authCommand;

        CommandList.add(infoCommand);
        CommandList.add(showCommand);
        CommandList.add(addCommand);
        CommandList.add(updateCommand);
        CommandList.add(removeByIdCommand);
        CommandList.add(clearCommand);
        CommandList.add(exitCommand);
        CommandList.add(addIfMinCommand);
        CommandList.add(removeGreaterCommand);
        CommandList.add(removeLowerCommand);
        CommandList.add(removeAllByColorCommand);
        CommandList.add(groupCountingByCreationDateCommand);
        CommandList.add(filterStartsWithNameCommand);
        CommandList.add(authCommand);
    }

    /**
     * Метод, возвращающий список всех команд
     * @return commandList
     */
    public List<BaseCommand> getCommandList() {
        return CommandList;
    }


    /**
     * Метод, выполняющий введённую команду
     * @param inputCommand (введённая команда)
     * @param argument (аргумент этой команды)
     */
    public String doCommand(String inputCommand, String argument, String login) {
        boolean flag = true;
        for (BaseCommand BaseCommand : CommandList) {
            if (BaseCommand.getName().split(" ")[0].equals(inputCommand)) {
                flag = false;
                return(BaseCommand.execute(argument, login));
            }
        }
        if (flag) System.out.println("Такой команды не существует!");
        return "";
    }


}