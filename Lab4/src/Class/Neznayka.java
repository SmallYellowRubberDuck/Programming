package Class;

import Abstract.Dwarf;
import Interface.Look;
import Interface.LooksLike;
import Interface.RushTo;
import Interface.See;

public class Neznayka extends Dwarf implements See, Look, RushTo {
    public String toLook() {
        return "печально смотрел вслед удаляющейся толпе, потом огляделся";
    }

    public String toSee() {
        return "увидел на противоположной стороне улицы, в тени забора, маленькую фигурку";
    }
    Eyes eyes = new Eyes();
    public String eyesLike(){
        return eyes.lookslike();
    }
    public String eyesTransform(){
        return eyes.transform();
    }
    private class Eyes implements LooksLike {
        protected String name = "Глаза";
        enum Stats {NO, YES}
        static Stats stats = Stats.NO;
        public String lookslike(){
            if (stats == Stats.NO) return this.toString()+"были печальными";
            else return this.toString()+"стали ещё печальнее";
        }
        public String transform(){
            stats = Stats.YES;
            return lookslike();
        }
        @Override
        public String toString() {
            return "его глаза ";
        }

    }
}
