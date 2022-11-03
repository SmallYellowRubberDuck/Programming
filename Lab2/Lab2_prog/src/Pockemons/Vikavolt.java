package Pockemons;

import Attacks.Special.Zap_Cannon;

public class Vikavolt extends Charjabug{
    public Vikavolt(String name, int lvl){
        super(name,lvl);
        this.setStats(77,70,90,145,75,43);
        this.addMove(new Zap_Cannon());
    }
}
