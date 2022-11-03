package Attacks.Physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Vise_Grip extends PhysicalMove {
    public Vise_Grip(){
        super(Type.NORMAL,55,1,0,1);
    }
    @Override
    protected String describe(){
        return "использует атаку Vise Grip";
    }
}
