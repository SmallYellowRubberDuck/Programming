package Exceptions;

public class FigureIsLate extends RuntimeException{
    public FigureIsLate(){
        super();
    }
    public FigureIsLate(String string){
        super(string);
    }
    public FigureIsLate(String string, Throwable cause){
        super(string, cause);
    }
    public FigureIsLate(Throwable cause){
        super(cause);
    }
}
