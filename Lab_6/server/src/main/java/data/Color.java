package data;

import java.util.Arrays;

public enum Color {
    GREEN,
    BLACK,
    BROWN;

    public static String nameList(){
        return (Arrays.toString(Color.values()));
    }
}