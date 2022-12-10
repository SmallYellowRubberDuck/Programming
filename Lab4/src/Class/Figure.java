package Class;

import Abstract.Dwarf;
import Exceptions.FigureIsLate;
import Interface.Look;
import Interface.LooksLike;
import Interface.RushTo;
import Interface.ScreamOfHappiness;

public class Figure extends Dwarf implements LooksLike, Look, RushTo, ScreamOfHappiness {
    private String address = "на противоположной стороне улицы, у забора";
    public String toLook(){
        return "глядела широко открытыми глазами";
    }
    public String screamOfHappiness(){
        return "взвизгнул от радости";
    }
    private interface Place{
        String getAddress();
    }
    public Place getAddressContainer(){
        class PersonalAddressContainer implements Place{
            final String place = Figure.this.address;
            @Override
            public String getAddress() throws FigureIsLate{
                    Double chance = Math.random();
                    if (chance < 0) throw new FigureIsLate("Фигура ещё не на месте");
                    else return Figure.this.address;
                }
            @Override
            public String toString(){
                return this.getAddress();
            }
        }
        return new PersonalAddressContainer();
    }
    @Override
    public String lookslike() {
        return "маленькая фигурка, которая стоит, разинув рот";
    }
    private class Eyes implements LooksLike {
        protected String name = "Глаза";
        public String lookslike(){
            return this.toString()+"полны любви и гордости за друга";
        }
        @Override
        public String toString() {
            return "его глаза ";
        }

    }
    Eyes eyes = new Eyes();
    public String eyesLike(){
        return eyes.lookslike();
    }
}
