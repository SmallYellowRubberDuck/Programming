package Class;

import Abstract.Dwarf;
import Exceptions.TooShortName;
import Interface.Fame;

public class Tsvetik extends Dwarf implements Fame{
    @Override
    public void setName(String name){
        try {
            betterSetName(name);
        } catch (TooShortName e) {
            this.name="Anonim";
        }
    }
    public void betterSetName(String name) throws TooShortName{
        if (name.length() <= 5) throw new TooShortName("Неправильное имя");
        else this.name = name;
    }
        public String famous(){
            return "прославился благодаря стихам";
    }
}
