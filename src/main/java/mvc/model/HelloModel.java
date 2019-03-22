package mvc.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HelloModel {
    private String username;
    private String message;
    
    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
    
    public void setUsername(String username) {
        this.username = username;
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date 
        int h = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        if(h >= 17 && h <= 21) {
            message = "晚安";
        } else {
            message = "安安";
        }
    }
    
}
