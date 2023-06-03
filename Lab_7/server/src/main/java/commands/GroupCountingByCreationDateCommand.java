package commands;

import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;

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
    public String execute(String argument, String login) {
        try{
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            collectionManager.groupCountingByCreationDate();
            return "Элементы были успешно сгруппированы";
        } catch (WrongCommandArgumentException e){
            return "Аргументов быть не должно в '" + getName() + "'";
        }
    }
}