package Abstract;

public abstract class Dwarf {
    protected String name;
    public String lookAtTheSky(Armageddon armageddon){
        return armageddon.checkStatus();
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return (this.name + ':');
    }

    @Override
    public boolean equals(Object object){
        if (getClass() != object.getClass()) return false;
        Dwarf dwarf = (Dwarf) object;
        return (this == dwarf);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

}
