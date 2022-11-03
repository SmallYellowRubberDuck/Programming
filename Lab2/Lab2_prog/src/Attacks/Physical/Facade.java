package Attacks.Physical;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade(int k){
        super(Type.NORMAL,70*k,1,0,1);
    }
    @Override
    protected String describe(){
        return "использует атаку Facade";
    }
}
