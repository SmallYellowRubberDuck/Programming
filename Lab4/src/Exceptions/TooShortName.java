package Exceptions;

public class TooShortName extends Exception{
    public TooShortName(){
        super();
    }
    public TooShortName(String string){
        super(string);
    }
    public TooShortName(String string, Throwable cause){
        super(string,cause);
    }
}
