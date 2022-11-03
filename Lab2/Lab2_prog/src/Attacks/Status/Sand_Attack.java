package Attacks.Status;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Sand_Attack extends StatusMove {
    public Sand_Attack(){
        super(Type.GROUND,0,1);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.ACCURACY, -1);
    }
    @Override
    protected String describe(){
        return "использует статусную атаку Sand Attack";
    }
}
