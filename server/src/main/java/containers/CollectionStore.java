package containers;

import data.Dragon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeSet;
@XmlRootElement(name="dragons")
@XmlAccessorType(XmlAccessType.NONE)
public class CollectionStore {

    // XmLElementWrapper generates a wrapper element around XML representation
    // XmlElement sets the name of the entities
    @XmlElement(name = "dragon")
    private TreeSet<Dragon> dragonCollection;
    public void addToCollection(Dragon dragon){
        dragonCollection.add(dragon);
    }

    public void removeFromCollection(Dragon dragon){
        dragonCollection.remove(dragon);
    }
    private String name;

    public void setDragonCollection(TreeSet<Dragon> dragonCollection) {
        this.dragonCollection = dragonCollection;
    }

    public TreeSet<Dragon> getDragonCollection() {
        return dragonCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
