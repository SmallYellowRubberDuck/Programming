package Class;

import Abstract.Dwarf;
import Interface.Look;
import Interface.LooksLike;

public class Figure extends Dwarf implements LooksLike, Look {
    public String toCongratulate(Dwarf dwarf){
        return "С Новым годом, "+dwarf.getName();
    }
    private String address = "на противоположной стороне улицы, у забора";
    public String toLook(){
        return "глядела широко открытыми глазами";
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String newAddress){
        this.address = newAddress;
    }
    @Override
    public String lookslike() {
        return "маленькая фигурка, которая стоит, разинув рот";
    }
}
