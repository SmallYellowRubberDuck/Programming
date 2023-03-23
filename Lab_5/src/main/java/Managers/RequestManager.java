package Managers;

import Exceptions.WrongAnswerFormatException;
import data.*;
import Exceptions.LowerThanMinValueException;
import Exceptions.MustBeNotEmptyException;


import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, запрашивающий у пользователя ввод значений элементов объекта - Dragon
 */
public class RequestManager {
    private Scanner scanner;

    public RequestManager(Scanner scanner){
        this.scanner = scanner;
    }

    /**
     * Метод задаёт объект класса Scanner, предназназначенный для считывания ввода
     * @param scanner объект класса Scanner
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод возвращает используемый объект класса Scanner
     * @return
     */
    public Scanner getScanner() {
        return scanner;
    }


    /**
     * Метод запрашивает у пользователя имя Dragon
     * @return name
     */
    public String requestName(){
        String name;
        while(true) {
            try {
                System.out.println("Введите имя Дракона:");
                name = scanner.nextLine().trim();
                if (name.equals("") || name.equals(null)) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException ex){
                ConsoleManager.printError("Название не должно быть пустым!");
            }
        }
        return name;
    }

    /**
     * Метод запрашивает у пользователя возраст дракона
     * @return age
     */
    public int requestAge() {
        String strAge;
        int age;
        while (true){
            try{
                System.out.println("Введите возраст дракона:");
                strAge = scanner.nextLine().trim();
                if (strAge.equals("") || strAge.equals(null)) throw new MustBeNotEmptyException();
                age = Integer.parseInt(strAge);
                if (age <= 0) throw new LowerThanMinValueException();
                break;
                //} catch(IOException ex) {
                //   ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex) {
                ConsoleManager.printError("Значение не может быть null!");
            } catch (LowerThanMinValueException ex) {
                ConsoleManager.printError("Значение должно быть больше 0!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата int!");
            }
        }
        return age;
    }

    /**
     * Метод запрашивает у пользователя координату X
     * @return y
     */
    private long requestY() {
        String strY;
        long y;
        while (true){
            try{
                System.out.println("Введите координату Y:");
                strY = scanner.nextLine().trim();
                if (strY.equals("") || strY.equals(null)) throw new MustBeNotEmptyException();
                y = Long.parseLong(strY);
                if (y <= -467) throw new LowerThanMinValueException();
                break;
            //} catch(IOException ex) {
             //   ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex) {
                ConsoleManager.printError("Значение не может быть null!");
            } catch (LowerThanMinValueException ex) {
                ConsoleManager.printError("Значение должно быть больше -467!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата long!");
            }
        }
        return y;
    }

    /**
     * Метод запрашивает у пользователя координату X
     * @return x
     */
    private Integer requestX() {
        String strX;
        Integer x;
        while (true){
            try{
                System.out.println("Введите координату X:");
                strX = scanner.nextLine().trim();
                if (strX.equals("") || strX.equals(null)) throw new MustBeNotEmptyException();
                x = Integer.parseInt(strX);
                break;
            //} catch(IOException ex) {
            //    ConsoleManager.printError("Операция ввода была прервана!");
            } catch (MustBeNotEmptyException ex) {
                ConsoleManager.printError("Значение не может быть null!");
            } catch (NumberFormatException ex) {
                ConsoleManager.printError("Вводимое значение должно быть числом формата Integer!");
            }
        }
        return x;
    }

    /**
     * Метод запрашивает у пользователя координаты
     * @return Coordinates
     */
    public Coordinates requestCoordinates() {
        Integer x;
        long y;
        x = requestX();
        y = requestY();
        return new Coordinates(x, y);
    }

    /**
     * Метод, устанавливающий дату и время
     * @return ZonedDateTime
     */
    public ZonedDateTime requestCreationDate() {
        while (true) {
            try {
                return ZonedDateTime.now();
            } catch (DateTimeException e) {
                ConsoleManager.printError("Проблема с датой.");
            }
        }
    }

    /**
     * Метод запрашивает у пользователя Тип дракона
     * @return dragonType
     */
    public DragonType requestDragonType() {
        String strDragonType;
        DragonType dragonType;
        while (true){
            try {
                System.out.println("Категории: " + data.DragonType.nameList());
                System.out.print("Введите тип дракона(если он есть): ");
                strDragonType = scanner.nextLine().trim();
                dragonType = DragonType.valueOf(strDragonType.toUpperCase());
                break;
            } catch(NoSuchElementException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            }
        }
        return dragonType;
    }

    /**
     * Метод запрашивает у пользователя Характер дракона
     * @return dragonCharacter
     */
    public DragonCharacter requestDragonCharacter() {
        String strDragonCharacter;
        DragonCharacter dragonCharacter;
        while (true){
            try {
                System.out.println("Категории: " + data.DragonCharacter.nameList());
                System.out.print("Введите характер дракона(если он есть): ");
                strDragonCharacter = scanner.nextLine().trim();
                dragonCharacter = DragonCharacter.valueOf(strDragonCharacter.toUpperCase());
                break;
            } catch(NoSuchElementException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            }
        }
        return dragonCharacter;
    }

    /**
     * Метод запрашивает у пользователя цвет дракона
     * @return color
     */
    public Color requestColor() {
        String strColor;
        Color color;
        while (true){
            try {
                System.out.println("Категории: " + data.Color.nameList());
                System.out.print("Введите цвет дракона: ");
                strColor = scanner.nextLine().trim();
                color = Color.valueOf(strColor.toUpperCase());
                break;
            } catch(NoSuchElementException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            }
        }
        return color;
    }

    /**
     * Метод запрашивает у пользователя глубину пещеры Дракона
     * @return depth
     */
    private Long requestDepth() {
        String strDepth;
        Long depth;
        while (true){
            try {
                System.out.print("Введите глубину пещеры дракона(если она есть): ");
                strDepth = scanner.nextLine().trim();
                depth = Long.parseLong(strDepth);
                break;
            } catch(NoSuchElementException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            }
        }
        return depth;
    }

    /**
     * Метод запрашивает у пользователя количество сокровищ в пещере Дракона
     * @return number
     */
    private int requestNumberOfTreasures() {
        String strNumber;
        int number;
        while (true){
            try {
                System.out.print("Введите количество сокровищ в пещере дракона: ");
                strNumber = scanner.nextLine().trim();
                number = Integer.parseInt(strNumber);
                break;
            } catch(NoSuchElementException ex) {
                ConsoleManager.printError("Операция ввода была прервана!");
            }
        }
        return number;
    }

    /**
     * Метод запрашивает у пользователя Пещеру дракона
     * @return DragonCave
     */
    public DragonCave requestDragonCave(){
        Long depth;
        int number;
        depth = requestDepth();
        number = requestNumberOfTreasures();
        return new DragonCave(depth, number);
    }

    /**
     * Метод запрашивает у пользователя ответ на вопрос, необходимо ли обновление данного параметра
     * @param argument
     * @return x - логическая переменная, являющаяся ответом на вопрос. true - ДА, false - НЕТ.
     */
    public boolean requestUpdateQuestion(String argument) {
        String question = argument + "  ДА/НЕТ";
        String answer;
        boolean x;
        while (true) {
            try {
                System.out.println(question);
                answer = scanner.nextLine().trim();
                if (answer.equals("ДА")) x = true;
                else if(answer.equals("НЕТ")) x = false;
                else throw new WrongAnswerFormatException();
                break;
            //} catch(IOException exc){
               // ConsoleManager.printError("Операция ввода была прервана!");
            } catch (WrongAnswerFormatException exc) {
                ConsoleManager.printError("Ответ должен быть 'ДА' или 'НЕТ'!");
            }
        }
        return x;
    }
}