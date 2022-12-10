package Interface;

import Abstract.Dwarf;

public interface MoveFromBarrel {
    default String moveFromBarrel(Dwarf dwarf){
        return (dwarf.getName()+"а стащили с бочки");
    }
}
