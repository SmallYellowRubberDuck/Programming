package Class;

import Abstract.Dwarf;
import Interface.Bravery;
import Interface.GoTo;
import Interface.OpenGate;

public class Travellers extends Dwarf implements Bravery, GoTo, OpenGate {
    public String brave(){
        return "отважные";
    }
    public String goTo(){
        return "направились к своему домику";
    }
    public String open(){
        return "открыли калитку";
    }
}
