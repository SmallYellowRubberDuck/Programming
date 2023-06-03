package data;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cave")
@XmlAccessorType(XmlAccessType.NONE)
public class DragonCave {
    public DragonCave(Long depth, int numberOfTreasures) {
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    public DragonCave() {
    }

    @XmlElement
    private Long depth; //Поле может быть null
    @XmlElement
    private int numberOfTreasures; //Значение поля должно быть больше 0

    public Long getDepth() {
        return depth;
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }
}