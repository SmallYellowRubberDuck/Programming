package data;

import java.util.Arrays;

public enum DragonType {
    WATER,
    UNDERGROUND,
    AIR,
    FIRE;


    /**
     * Метод даёт строковое представление элементов enum
     * @return
     */
    public static String nameList(){
        return (Arrays.toString(DragonType.values()));
    }
}