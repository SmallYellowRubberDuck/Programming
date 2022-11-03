package Pockemons;

import Attacks.Physical.*;
import Attacks.Status.Roost;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Type;

public class Chatot extends Pokemon {
    public Chatot(String name, int lvl){
        super(name,lvl);
        this.setStats(76,65,45,92,42,91);
        int k=1;
        if ((this.getCondition()==Status.BURN)||(this.getCondition()==Status.POISON)||(this.getCondition()==Status.PARALYZE)){
            k=2;
        }
        this.setType(Type.FLYING);
        this.addMove(new Facade(k));
        this.addMove(new Peck());
        this.addMove(new Fury_Attack());
        this.addMove(new Roost());

    }
}
