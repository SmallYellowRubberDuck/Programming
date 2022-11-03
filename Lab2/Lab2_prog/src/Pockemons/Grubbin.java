package Pockemons;

import Attacks.Physical.Vise_Grip;
import Attacks.Special.Thunderbolt;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Grubbin extends Pokemon {
    public Grubbin(String name, int lvl){
        super(name,lvl);
        this.setStats(47,62,45,55,45,46);
        this.setType(Type.BUG);
        this.addMove(new Thunderbolt());
        this.addMove(new Vise_Grip());
    }
}
