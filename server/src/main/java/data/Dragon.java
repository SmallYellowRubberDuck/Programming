package data;

import XmlUtils.DateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс Dragon представляет Дракона
 */
@XmlRootElement(name="dragon")
@XmlAccessorType(XmlAccessType.NONE)
public class Dragon implements Comparable<Dragon> {
    private String login;
    @XmlElement
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    public void setName(String name){
        this.name = name;
    }
    @XmlElement
    private Coordinates coordinates; //Поле не может быть null
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private ZonedDateTime creationDate; //Поле не может быть null,
    // Значение этого поля должно генерироваться автоматически

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @XmlElement
    private int age; //Значение поля должно быть больше 0
    public void setAge(int age){
        this.age = age;
    }
    @XmlElement
    private Color color; //Поле не может быть null
    public void setColor(Color color){
        this.color = color;
    }
    @XmlElement
    private DragonType type; //Поле может быть null
    public void setType(DragonType type){
        this.type = type;
    }
    @XmlElement
    private DragonCharacter character; //Поле может быть null
    public void setCharacter(DragonCharacter character){
        this.character = character;
    }
    @XmlElement
    private DragonCave cave; //Поле не может быть null
    public void setCave(DragonCave cave){
        this.cave = cave;
    }

    public Dragon(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, int age, Color color,
                  DragonType type, DragonCharacter character, DragonCave cave, String login){
        this.id=id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
        this.login = login;
    }
    public Dragon(){}

    /**
     * Возвращает id
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * Устанавливает id Дракона
     * @param id id Дракона
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Возвращает имя Дракона
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает создателя Дракона
     * @return name
     */
    public String getLogin() {
        return login;
    }

    /**
     * Возвращает координаты Дракона
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает время создания Дракона
     * @return creationDate
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает возраст Дракона
     * @return age
     */
    public int getAge(){
        return age;
    }

    /**
     * Возвращает цвет Дракона
     * @return color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Возвращает тип Дракона
     * @return type
     */
    public DragonType getType(){
        return type;
    }

    /**
     * Возвращает характер Дракона
     * @return character
     */
    public DragonCharacter getCharacter(){
        return character;
    }

    /**
     * Возвращает пещеру Дракона
     * @return cave
     */
    public DragonCave getCave(){
        return cave;
    }

    /**
     * Метод сравнения двух объектов между собой
     * @param o Объект для сравнения
     * @return id
     */
    @Override
    public int compareTo(Dragon o) {
        return (int) (this.hashCode() - o.hashCode());
    }

    @Override
    public int hashCode(){
        return this.getCave().hashCode();
    }
    @Override
    public String toString(){
        String result = String.format("ID: %d\nName: %s\nCoordinates: {x: %d, y: %d}\nCreationDate: %s\n" +
                        "Age: %d\nColor: %s\n",
                getId(), getName(), getCoordinates().getX(), getCoordinates().getY(),
                getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.y H:mm:ss")),
                getAge(), getColor());
        if(getType() == null) result += "Type: null";
        else result += String.format("Type: %s\n",getType());
        if(getCharacter() == null) result += "Character: null";
        else result += String.format("Character: %s\n",getCharacter());
        if (getCave().getDepth() != null) result += String.format("Depth of cave: %d",getCave().getDepth());
        else result += "Depth of cave: null\n";
        result += String.format("Number of treasures in cave: %d\n\n",getCave().getNumberOfTreasures());
        return result;
    }
}
