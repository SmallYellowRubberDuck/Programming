package managers;
import commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, управляющий командами
 */
public class CommandManager {
    private List<BaseCommand> CommandList = new ArrayList<>();

    private BaseCommand helpCommand;
    private BaseCommand infoCommand;
    private BaseCommand showCommand;
    private AddCommand addCommand;
    private UpdateCommand updateCommand;
    private BaseCommand removeByIdCommand;
    private BaseCommand clearCommand;
    private BaseCommand executeScriptCommand;
    private BaseCommand exitCommand;
    private AddIfMinCommand addIfMinCommand;
    private RemoveGreaterCommand removeGreaterCommand;
    private RemoveLowerCommand removeLowerCommand;
    private RemoveAllByColorCommand removeAllByColorCommand;
    private BaseCommand groupCountingByCreationDateCommand;
    private BaseCommand filterStartsWithNameCommand;

    public CommandManager(BaseCommand helpCommand, BaseCommand infoCommand, BaseCommand showCommand,
                          AddCommand addCommand, UpdateCommand updateCommand, BaseCommand removeByIdCommand,
                          BaseCommand clearCommand, BaseCommand executeScriptCommand,
                          BaseCommand exitCommand, AddIfMinCommand addIfMinCommand, RemoveGreaterCommand removeGreaterCommand,
                          RemoveLowerCommand removeLowerCommand, RemoveAllByColorCommand removeAllByColorCommand,
                          BaseCommand groupCountingByCreationDateCommand, BaseCommand filterStartsWithNameCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.removeAllByColorCommand = removeAllByColorCommand;
        this.groupCountingByCreationDateCommand = groupCountingByCreationDateCommand;
        this.filterStartsWithNameCommand = filterStartsWithNameCommand;

        CommandList.add(helpCommand);
        CommandList.add(infoCommand);
        CommandList.add(showCommand);
        CommandList.add(addCommand);
        CommandList.add(updateCommand);
        CommandList.add(removeByIdCommand);
        CommandList.add(clearCommand);
        CommandList.add(executeScriptCommand);
        CommandList.add(exitCommand);
        CommandList.add(addIfMinCommand);
        CommandList.add(removeGreaterCommand);
        CommandList.add(removeLowerCommand);
        CommandList.add(removeAllByColorCommand);
        CommandList.add(groupCountingByCreationDateCommand);
        CommandList.add(filterStartsWithNameCommand);
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
    public String doCommand(String inputCommand, String argument) {
        for (BaseCommand BaseCommand : CommandList) {
            if (BaseCommand.getName().split(" ")[0].equals(inputCommand)) {
                return BaseCommand.execute(argument);
            }
        }
        System.out.println("Такой команды не существует!");
        return "";
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды add
     * @param requestManager
     */
    public void setAddCommand(RequestManager requestManager) {
        addCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды update id
     * @param requestManager
     */
    public void setUpdateIdCommand(RequestManager requestManager) {
        updateCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды add_if_min
     * @param requestManager
     */
    public void setAddIfMinCommand(RequestManager requestManager) {
        addIfMinCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды remove_lower
     * @param requestManager
     */
    public void setRemoveLowerCommand(RequestManager requestManager) {
        removeLowerCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды remove_greater
     * @param requestManager
     */
    public void setRemoveGreaterCommand(RequestManager requestManager) {
        removeGreaterCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды remove_all_by_color
     * @param requestManager
     */
    public void setRemoveAllByColorCommand(RequestManager requestManager) {
        removeAllByColorCommand.setRequest(requestManager);
    }
}