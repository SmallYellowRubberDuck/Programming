package Pockemons;

import Attacks.Special.Absorb;
import Attacks.Special.Energy_Ball;
import Attacks.Status.Sand_Attack;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Cacnea extends Pokemon {
    public Cacnea(String name, int lvl){
        super(name,lvl);
        this.setStats(50,85,40,85,40,35);
        this.setType(Type.GRASS);
        this.addMove(new Energy_Ball());
        this.addMove(new Sand_Attack());
        this.addMove(new Absorb());
    }
}
