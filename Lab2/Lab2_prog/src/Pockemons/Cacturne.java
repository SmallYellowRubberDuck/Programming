package Pockemons;

import Attacks.Special.Focus_Blast;
import ru.ifmo.se.pokemon.Type;

public class Cacturne extends Cacnea {
    public Cacturne(String name,int lvl){
        super(name,lvl);
        this.setStats(70,115,60,115,60,55);
        this.setType(Type.GRASS,Type.DARK);
        this.addMove(new Focus_Blast());
    }
}
