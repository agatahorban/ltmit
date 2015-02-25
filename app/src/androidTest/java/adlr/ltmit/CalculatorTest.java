package adlr.ltmit;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

import adlr.ltmit.bl.Calculator;


/**
 * Created by Agata on 2015-02-24.
 */
public class CalculatorTest extends TestCase {
      @SmallTest
      public void test(){
        Long secondOfJanuary = 1420153200000L;
        Date date = new Date(secondOfJanuary);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        assertEquals(1,day);
        Date date2 = Calculator.calculateDate(secondOfJanuary,1,82);
        cal.setTime(date2);
        int day2 = cal.get(Calendar.DAY_OF_MONTH);

        assertEquals(day+5,day2);

        date2 = Calculator.calculateDate(secondOfJanuary,1,52);
        cal.setTime(date2);
        day2 = cal.get(Calendar.DAY_OF_MONTH);
        assertEquals(day+3,day2);

        date2 = Calculator.calculateDate(secondOfJanuary,2,82);
        cal.setTime(date2);
        day2 = cal.get(Calendar.DAY_OF_MONTH);
        assertEquals(day+10,day2);

        date2 = Calculator.calculateDate(secondOfJanuary,2,42);
        cal.setTime(date2);
        day2 = cal.get(Calendar.DAY_OF_MONTH);
        assertEquals(day+2,day2);

        date2 = Calculator.calculateDate(secondOfJanuary,3,82);
        cal.setTime(date2);
        day2 = cal.get(Calendar.DAY_OF_MONTH);
        assertEquals(day+15,day2);

        date2 = Calculator.calculateDate(secondOfJanuary,3,52);
        cal.setTime(date2);
        day2 = cal.get(Calendar.DAY_OF_MONTH);
        assertEquals(day+9,day2);
    }

    public CalculatorTest() {
        super();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
}
