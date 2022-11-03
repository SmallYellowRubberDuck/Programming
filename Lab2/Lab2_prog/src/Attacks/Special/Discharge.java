package Attacks.Special;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Discharge extends SpecialMove {
    public Discharge(){
        super(Type.ELECTRIC,80,1);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().chance(0.3);
        if (effect.success()) {
            Effect.paralyze(pokemon);
        }
    }
    @Override
    protected String describe(){
        return "использует специальную атаку Discharge";
    }
}
