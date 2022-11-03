package Attacks.Special;

import ru.ifmo.se.pokemon.*;

public class Zap_Cannon extends SpecialMove {
    public Zap_Cannon(){
        super(Type.ELECTRIC,120,0.5);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon){
        Effect.paralyze(pokemon);
        Effect effect = new Effect().chance(0.25);
        if (effect.success()) {
            Effect.flinch(pokemon);
            pokemon.setMod(Stat.SPEED,-2);
        }
    }
    @Override
    protected String describe(){
        return "использует специальную атаку Zap Cannon";
    }

}
