package Attacks.Physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Peck extends PhysicalMove{
    public Peck(){
        super(Type.FLYING,35,1,0,1);
        }
        @Override
        protected String describe(){
            return "использует атаку Peck";
        }
    }

