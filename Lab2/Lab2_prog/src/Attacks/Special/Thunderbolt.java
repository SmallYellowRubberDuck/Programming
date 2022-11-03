package Attacks.Special;

import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove {
    public Thunderbolt(){
        super(Type.ELECTRIC,90,1);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().chance(0.1);
        if (effect.success()){
            Effect.paralyze(pokemon);
        }
    }
    @Override
    protected String describe(){
        return "использует специальную атаку Thunderbolt";
    }
}
