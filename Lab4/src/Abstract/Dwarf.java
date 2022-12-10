package Abstract;

import Interface.ToBring;
import Interface.ToCelebrate;
import Interface.ToAsk;

public abstract class Dwarf implements ToCelebrate, ToBring, ToAsk {
    protected String name;
    protected static String[] deficit = new String[100];
    protected static int i = 0;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void rename(String str){
        System.out.println (this.name + " носит имя "+ str);
        this.setName(str);
    }
    public static int getI(){
        return i;
    }
    public static String[] getDeficit(){
        return deficit;
    }
    @Override
    public String ask(String str){
        return this.name+" спросил: '"+str+"'";
    }
    @Override
    public String toCelebrate(String name){
        double chance = Math.random();
        if (chance<0.25){
            return this.toString() + " С Новым Годом, " + name + "!";
        }
        else if (chance<0.5){
            return this.toString() + " Счастливого Нового Года, " + name + "!";
        }
        else if (chance<0.75){
            return this.toString() + " Удачи в Новом Году, " + name + "!";
        }
        else{
            return this.toString() + " Весёлых новогодних праздников, " + name + "!";
        }
    }
    @Override
    public String toBring(String product){
        double chance = Math.random();
        if (chance<0.7){
            return this.toString() + " Я принес " + product + "!";
        }
        else{
            deficit[i]=product;
            i++;
            return this.toString() + " Не выполнил миссию принести " + product + "!";
        }
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
