package Abstract;

public abstract class Armageddon {
    public Armageddon(String string){
        this.title = string;
    }
    protected double chance;
    protected String title;
    protected void setChance(){
        this.chance = Math.random();
    }
    protected int start(){
        throw new RuntimeException(title);
    }
    public String checkStatus(){
        setChance();
        if (chance>0.5) start();
        return "Merry Christmas";
    }
}
