package Class;

import Abstract.Holiday;

import java.util.Calendar;

public class NewYearEve extends Holiday {
    public NewYearEve(){
        this.name = "Новый Год";
    }
    private String getYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        return (String.valueOf(calendar.get(Calendar.YEAR)+1));
    }
    private String getYear(int i){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        return (String.valueOf(calendar.get(Calendar.YEAR)+i));
    }
    public boolean isCelebrating(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        if (((calendar.get(Calendar.MONTH)==11)&&(calendar.get(Calendar.DAY_OF_MONTH)==31))){
            System.out.println("Да, коротышка, можно!");
            System.out.println ("С Новым "+ this.getYear()+" годом!!!");
            return true;
        }
        else if ((calendar.get(Calendar.MONTH)==0)&&(calendar.get(Calendar.DAY_OF_MONTH)==1)){
            System.out.println("Да, коротышка, можно!");
            System.out.println("С Новым "+ this.getYear(0) + " годом!!!");
            return true;
        }
        else if ((calendar.get(Calendar.MONTH)==11)&&(calendar.get(Calendar.DAY_OF_MONTH)>=16)){
            System.out.println("Да, коротышка, можно!");
            System.out.println ("С наступающим Новым "+ this.getYear()+" годом!!!");
            return true;
        }
        else{
            System.out.println("К сожалению, коротышка, нельзя");
            return false;
        }
    }

}
