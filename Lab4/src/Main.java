import Abstract.Dwarf;
import Class.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        MenCrowd menCrowd = new MenCrowd();
        menCrowd.setName("Малыши");
        WomanCrowd womanCrowd = new WomanCrowd();
        womanCrowd.setName("Малышки");
        Neznayka neznayka = new Neznayka();
        neznayka.setName("Незнайка");
        Figure figure = new Figure();
        figure.setName("Фигурка");
        Travellers travellers = new Travellers();
        travellers.setName("Путешественники");
        Tsvetik tsvetik = new Tsvetik();
        tsvetik.setName("Цветик");
        Ponchik ponchik = new Ponchik();
        ponchik.setName("Пончик");
        Znayka znayka = new Znayka();
        znayka.setName("Знайка");
        NewYearEve newYearEve = new NewYearEve();

        tell_a_story(neznayka, figure, travellers, tsvetik, menCrowd, womanCrowd, newYearEve, ponchik, znayka);
    }

    public static void tell_a_story(Neznayka neznayka, Figure figure, Travellers travellers, Tsvetik tsvetik,
                                    MenCrowd menCrowd, WomanCrowd womanCrowd, NewYearEve newYearEve,
                                    Ponchik ponchik, Znayka znayka) throws IOException {
        String name = "Эрик";
        System.out.println(menCrowd);
        System.out.println(menCrowd.moveFromBarrel(tsvetik));
        System.out.println(menCrowd.pickUp(tsvetik));
        System.out.println(menCrowd.dragHome(tsvetik));
        System.out.println(womanCrowd);
        System.out.println(womanCrowd.runBehind(tsvetik));
        System.out.println(womanCrowd.throwDaisies());
        System.out.println(tsvetik);
        System.out.println(tsvetik.famous());
        System.out.println(travellers);
        System.out.println(travellers.brave());
        System.out.println(travellers.open());
        System.out.println(travellers.goTo());
        System.out.println(new Outside.Home());
        System.out.println(neznayka);
        System.out.println(neznayka.toLook());
        System.out.println(neznayka.eyesLike());
        System.out.println(new Outside());
        System.out.println(neznayka.eyesTransform());
        System.out.println(neznayka.toSee());
        System.out.println(figure);
        System.out.println(figure.getAddressContainer());
        System.out.println(figure.lookslike());
        System.out.println(figure.toLook());
        figure.rename("Гунька");
        System.out.println(figure.screamOfHappiness());
        System.out.println(figure.rushTo(neznayka));
        System.out.println(neznayka);
        System.out.println(neznayka.rushTo(figure));
        System.out.println(figure);
        System.out.println(figure.eyesLike());
        System.out.println(figure.ask("Уже можно праздновать Новый Год?"));
        Scanner in = new Scanner(System.in);
        System.out.print("Какое сегодня число, "+name+"?: ");
        String whatDate = in.next();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        try {
            date = df.parse(whatDate);
            newYearEve.setDate(date);
            //String newDateString = df.format(date);
            //System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (newYearEve.isCelebrating()) {
            System.out.println(figure.toCelebrate(name));
            System.out.println(neznayka.toCelebrate(name));
            System.out.println(menCrowd.toCelebrate(name));
            System.out.println(womanCrowd.toCelebrate(name));
            System.out.println(travellers.toCelebrate(name));
            System.out.println(tsvetik.toCelebrate(name));
            System.out.print("Что нужно принести для празднования, "+name+"?: ");
            String sailNeed = in.next();
            String[] saillist = sailNeed.split(",", -1);
            Dwarf[] persons = new Dwarf[]{neznayka, tsvetik, figure,ponchik,znayka};
            for (int i = 0; i < saillist.length; i++) {
                System.out.println(persons[i % persons.length].toBring(saillist[i]));
            }
            if (Dwarf.getI() > 0) {
                String result = "";
                for (String product : Dwarf.getDeficit()){
                    if (product!=null) {
                        result = result + " " + product + ", ";
                    }
                }
                System.out.println("Что ж... Осталось принести только" + result + "веселье");
            } else {
                System.out.println("Новый Год выйдет на славу)))");
            }
        } else {
            System.out.println("Эх, Эрик... До Нового года ещё далеко...");
        }

    }
}