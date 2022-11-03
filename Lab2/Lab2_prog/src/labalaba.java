import Pockemons.*;
import ru.ifmo.se.pokemon.*;
public class labalaba {
    public static void main(String[] args){
        Battle b = new Battle();
        Pokemon Vikavolt = new Vikavolt("Vika", 64);
        Pokemon Charjabug = new Charjabug("Charg", 64);
        Pokemon Grubbin = new Grubbin("Grubbi9In",9);
        Pokemon Cacturne = new Cacturne("Cactus",44);
        Pokemon Cacnea = new Cacnea("Cacnibud",42);
        Pokemon Chatot = new Chatot("ChatoBordo",41);
        b.addAlly(Cacturne);
        b.addAlly(Chatot);
        b.addAlly(Vikavolt);
        b.addFoe(Grubbin);
        b.addFoe(Cacnea);
        b.addFoe(Charjabug);
        b.go();
    }
}

