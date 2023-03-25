package Commands;

import data.Dragon;
import Exceptions.CollectionIsEmptyException;
import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Класс, представляющий команду filter_starts_with_name, выводящую элементы, значение поля name которых начинается с заданной подстроки
 */
public class FilterStartsWithNameCommand extends BaseCommand {
    private CollectionManager collection;

    public FilterStartsWithNameCommand(CollectionManager collection) {
        super("filter_starts_with_name name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collection = collection;
    }

    /**
     * Метод выводит элементы, значение поля name которых начинается с заданной подстроки
     * @param argument - заданная подстрока
     */
    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new WrongCommandArgumentException();
            if(collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int k = 0;
            System.out.println("Элементы, начинающиеся с данной подстроки: ");
            for (Dragon dragon : collection.getCollection()) {
                if (dragon.getName().startsWith(argument)) {
                    System.out.println(dragon);
                    k += 1;
                }
            }
            if (k == 0) System.out.println("В коллекции нет подходящих элементов!");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе данной команды должно содержаться название элемента!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В коллекции не содержится элементов!");
        }
    }
}