package adlr.ltmit;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.util.Date;

import adlr.ltmit.bl.Calculator;

/**
 * Created by Agata on 2015-02-25.
 */
public class GettingDateTest extends TestCase {
    @SmallTest
    public void test(){
        Long secondOfJanuary = 1420153200000L;
        Date date = new Date(secondOfJanuary);

        assertEquals(2,Calculator.getDay(date));
        assertEquals(1,Calculator.getMonth(date));
        assertEquals(2015,Calculator.getYear(date));

        Long twentiethOfAprilFourteen = 1397944800000L;
        Date date2 = new Date(twentiethOfAprilFourteen);

        assertEquals(20,Calculator.getDay(date2));
        assertEquals(4,Calculator.getMonth(date2));
        assertEquals(2014,Calculator.getYear(date2));


    }
}
