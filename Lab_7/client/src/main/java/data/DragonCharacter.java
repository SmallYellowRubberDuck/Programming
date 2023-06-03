package data;

import java.util.Arrays;

public enum DragonCharacter {
    EVIL,
    GOOD,
    CHAOTIC_EVIL,
    FICKLE;

    /**
     * Метод даёт строковое представление элементов enum
     * @return
     */
    public static String nameList(){
        return (Arrays.toString(DragonCharacter.values()));
    }
}