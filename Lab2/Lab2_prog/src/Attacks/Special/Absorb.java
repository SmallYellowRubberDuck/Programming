package Attacks.Special;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class Absorb extends SpecialMove {
    public Absorb(){
        super(Type.GRASS,20,1);
    }
    @Override
    protected void applySelfEffects(Pokemon pokemon){
        pokemon.setMod(Stat.HP,-10);
    }
    @Override
    protected String describe(){
        return "использует специальную атаку Absorb";
    }
}
