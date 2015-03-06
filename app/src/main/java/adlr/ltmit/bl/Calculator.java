package adlr.ltmit.bl;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Agata on 2015-02-22.
 */

public class Calculator {

    public static long DAY = 86400000;
    public static long HIGH_PERCENTAGE = 80;
    public static long MEDIUM_PERCENTAGE = 50;

    public static long MEDIUM_COEFFICIENT = 3;
    public static long HIGH_COEFFICIENT = 5;

    public static Date calculateDate(Long today, int priority, double percentage){
        if(percentage >= HIGH_PERCENTAGE){
            return new Date(today + HIGH_COEFFICIENT * priority * DAY);
        }
        else if(percentage > MEDIUM_PERCENTAGE && percentage < HIGH_PERCENTAGE){
            return new Date(today + MEDIUM_COEFFICIENT * priority * DAY);
        }
        else {
            return new Date(today +  priority * DAY);
        }
    }

    public static int getDay(Date date){
        return setCalendar(date).get(Calendar.DAY_OF_MONTH) + 1;
    }

    public static int getMonth(Date date){
        return setCalendar(date).get(Calendar.MONTH) + 1 ;
    }

    public static int getYear(Date date){
        return setCalendar(date).get(Calendar.YEAR);
    }

    private static Calendar setCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static boolean isMoreThan14Days(Date date){
        long today = System.currentTimeMillis();

        long d = date.getTime();
        long count = d - today;
            if(count>DAY*14)
                return true;
            else
                return false;

    }
    public static int calculateDays(Date date){
        double d = date.getTime() - System.currentTimeMillis();
        double t = Math.round(d / DAY);
        Log.d("DAYS",t+ "" );
        return (int)t;
    }
}
