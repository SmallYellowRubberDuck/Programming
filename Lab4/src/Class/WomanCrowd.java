package Class;

import Abstract.Dwarf;

public class WomanCrowd extends Dwarf {
    public String runBehind(Dwarf dwarf){
        return ("бежали за " + dwarf.getName()+"ом");
    }
    public String throwDaisies(){
        return "бросали маргаритки";
    }
}
