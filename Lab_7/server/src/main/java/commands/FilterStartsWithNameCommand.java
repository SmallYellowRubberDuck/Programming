package commands;

import data.Dragon;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

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
    public String execute(String argument, String login) {
        try {
            if(argument.isEmpty()) throw new WrongCommandArgumentException();
            if(collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int k = 0;
            String result = "Элементы, начинающиеся с данной подстроки: ";
            for (Dragon dragon : collection.getCollection()) {
                if (dragon.getName().startsWith(argument)) {
                    result = result + (dragon);
                    k += 1;
                }
            }
            if (k == 0) return"В коллекции нет подходящих элементов!";
            return result;
        } catch (WrongCommandArgumentException ex) {
            return "В аргументе данной команды должно содержаться название элемента!";
        } catch (CollectionIsEmptyException ex) {
            return "В коллекции не содержится элементов!";
        }
    }
}