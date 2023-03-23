package Commands;

import Exceptions.MustBeNotEmptyException;
import Exceptions.WrongCommandArgumentException;
import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Класс используется для группировки элементов коллекции по дате создания элемента
 */
public class GroupCountingByCreationDateCommand extends BaseCommand {
    /**
     Менеджер коллекции
     */
    private final CollectionManager collectionManager;
    public GroupCountingByCreationDateCommand(CollectionManager collectionManager) {
        super("group_counting_by_creation_date", "сгруппировать элементы по дате их создания");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            collectionManager.groupCountingByCreationDate();
            System.out.println("Элементы были успешно сгруппированы");
        } catch (WrongCommandArgumentException e){
            ConsoleManager.printError("Аргументов быть не должно в '" + getName() + "'");
        }
    }
}