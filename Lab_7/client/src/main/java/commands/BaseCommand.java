package commands;

public abstract class BaseCommand {
    /**
     * Команда имеет имя
     */
    private final String name;
    /**
     * Команда имеет описание
     */
    private final String description;

    /**
     * Конструктор команды
     */
    public BaseCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает название команды
     * @return name
     */
    public String getName() {
        return name;
    }



    /**
     * Возвращает описание команды
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает строковое представление команды
     * @return Название и описание команды
     */
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }

    /**
     * Метод возвращает хэш-код для команды
     * @return hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    /**
     * Метод сравнивает команды между собой по описанию и имени
     * @param obj Объект для сравнения.
     * @return boolean type
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BaseCommand other = (BaseCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
    /**
     * Метод испольняет команду
     * @param argument
     */
    public abstract String execute(String argument);
}
