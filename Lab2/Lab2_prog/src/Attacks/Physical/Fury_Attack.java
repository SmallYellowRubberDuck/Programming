package Attacks.Physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Fury_Attack extends PhysicalMove {
    public Fury_Attack(){
        super(Type.NORMAL, 15, 0.85, 0, 1);
        double chance=Math.random();
        if (chance>0.375){
            super.hits = 1;
        }
        if ((chance > 0.140625) && (chance <= 0.375)){
            super.hits = 2;
        }
        if ((chance > 0.017578125) && (chance <= 0.140625)){
            super.hits = 3;
        }
        if ((chance > 0.00219726563) && (chance <= 0.017578125)){
            super.hits = 4;
        }
        if (chance <=0.00219726563) {
            super.hits = 5;
        }
    }
    @Override
    protected String describe(){
        return "использует атаку Fury Attack";
    }

}
