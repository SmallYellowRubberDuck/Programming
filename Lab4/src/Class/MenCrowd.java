package Class;

import Abstract.Dwarf;
import Interface.MoveFromBarrel;
import Interface.ToPickUp;
import Interface.ToDragHome;

public class MenCrowd extends Dwarf implements MoveFromBarrel, ToPickUp, ToDragHome {
    public String pickUp(Dwarf dwarf){
        return (dwarf.getName()+"а подняли на руки");
    }
    public String dragHome(Dwarf dwarf){
        return (dwarf.getName()+"а потащили домой");
    }
}
