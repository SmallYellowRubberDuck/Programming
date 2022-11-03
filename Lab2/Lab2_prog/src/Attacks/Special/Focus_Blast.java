package Attacks.Special;

import ru.ifmo.se.pokemon.*;

public class Focus_Blast extends SpecialMove {
    public Focus_Blast(){
        super(Type.FIGHTING, 120, 0.7);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().chance(0.1);
        if (effect.success()){
            pokemon.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe() {
        return "использует специальную атаку Focus Blast";
    }
}