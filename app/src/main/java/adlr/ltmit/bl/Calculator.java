package adlr.ltmit.bl;

import java.util.Date;

/**
 * Created by Agata on 2015-02-22.
 */

public class Calculator {

    public static long DAY = 86400000;
    public static long HIGH_PERCENTAGE = 80;
    public static long MEDIUM_PERCENTAGE = 50;

    public static long MEDIUM_COEFFICIENT = 2;
    public static long LOW_COEFFICIENT = 3;

    public static Date calculateDate(int priority, double percentage){
        Long today = System.currentTimeMillis();
        if(percentage >= HIGH_PERCENTAGE){
            return new Date(today + priority * DAY);
        }
        else if(percentage > MEDIUM_PERCENTAGE && percentage < HIGH_PERCENTAGE){
            return new Date(MEDIUM_COEFFICIENT * priority * DAY);
        }
        else {
            return new Date(LOW_COEFFICIENT * priority * DAY);
        }
    }
}
