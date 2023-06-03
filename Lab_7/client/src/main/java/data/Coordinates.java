package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="coordinates")
@XmlAccessorType(XmlAccessType.NONE)
public class Coordinates {
    @XmlElement
    private Integer x; //Поле не может быть null
    @XmlElement
    private long y; //Значение поля должно быть больше -467

    public Coordinates(Integer x, long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    /**
     * Возвращает координату x
     *
     * @return x
     */
    public Integer getX() {
        return x;
    }

    /**
     * Возвращает координату y
     *
     * @return y
     */
    public long getY() {
        return y;
    }
}
