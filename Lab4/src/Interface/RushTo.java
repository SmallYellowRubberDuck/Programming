package Interface;

public interface RushTo {
    default String rushTo(Object object){
        return ("метнулся к " + object.toString());
    }
}
