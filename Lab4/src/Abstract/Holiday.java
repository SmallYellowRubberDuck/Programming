package Abstract;

import java.io.IOException;
import java.util.Date;
import java.util.zip.DataFormatException;

public abstract class Holiday {
    protected String name;
    protected Date date;

    public Date getDate(){return this.date;}
    public void setDate(Date date){
            this.date = date;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString() {
        return ("Сегодня празднуют " + this.name);
    }

    @Override
    public boolean equals(Object object){
        if (getClass() != object.getClass()) return false;
        Holiday holiday = (Holiday) object;
        return (this == holiday);
    }

    @Override
    public int hashCode(){
        return (name+date).hashCode();
    }

}

