import Abstract.Armageddon;
import Class.*;
public class Main {
    public static void main(String[] args) {
        Neznayka neznayka = new Neznayka();
        neznayka.setName("Незнайка");
        Figure figure = new Figure();
        figure.setName("Фигурка");
        Travellers travellers = new Travellers();
        travellers.setName("Путешественники");
        Tsvetik tsvetik = new Tsvetik();
        tsvetik.setName("Цветик");
        tell_a_story(neznayka,figure,travellers,tsvetik);
    }
    public static void tell_a_story(Neznayka neznayka, Figure figure, Travellers travellers, Tsvetik tsvetik) {
        System.out.println(tsvetik);
        System.out.println(tsvetik.famous());
        System.out.println(travellers);
        System.out.println(travellers.brave());
        System.out.println(travellers.open());
        System.out.println(travellers.goTo());
        System.out.println(neznayka);
        System.out.println(neznayka.toLook());
        System.out.println(neznayka.eyesLike());
        System.out.println(neznayka.eyesTransform());
        System.out.println(neznayka.toSee());
        System.out.println(figure);
        System.out.println(figure.getAddress());
        System.out.println(figure.lookslike());
        System.out.println(figure.toLook());
        System.out.println(neznayka);
        System.out.println(neznayka.toCongratulate(figure));
        double a = Math.random();
        if (a > 0.7) {
            System.out.println(figure);
            System.out.println(figure.toCongratulate(neznayka));
        } else {
            System.out.println("Фигурка не хотела Новый год");
            System.out.println(figure.lookAtTheSky(new Armageddon("Yo-ho-hum") {
            }));
        }
    }
}