package commands;

import managers.ConsoleManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс, представляющий команду execute_script, считывающий и исполняющий скрипт из указанного файла
 */
public class ExecuteScriptCommand extends BaseCommand {

    public ExecuteScriptCommand() {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла");
    }

    /**
     * Метод проверяет наличие скрипта по указанному адресу. Само чтение и выполнение скрипта реализовано в ConsoleManager
     * @param argument - путь до файла со скриптом
     */
    @Override
    public String execute(String argument) {
        try (Scanner sc = new Scanner(new FileReader(argument))){
            System.out.println("Скрипт из файла " + argument + " загружен!");
            System.out.println("$");
            return "";
        } catch (IOException ex) {
            ConsoleManager.printError("Операция ввода была прервана!");
            return "";
        }
    }
}