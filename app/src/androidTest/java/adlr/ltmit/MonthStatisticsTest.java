package adlr.ltmit;

import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import junit.framework.TestCase;

import adlr.ltmit.bl.Priority;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.MonthStatistics;
import adlr.ltmit.entities.Statistics;

/**
 * Created by Agata on 2015-03-03.
 */
public class MonthStatisticsTest extends TestCase {
    @SmallTest
    public void test() {
        Category cat = new Category("cat11123992");
        cat.save();
        Database db = new Database(Priority.HIGH.getValue(), cat, "db11123992");
        db.save();

        Statistics st = new Statistics(85.0,db);
        st.setAmount(1);
        st.save();

        RepeatingController rc = new RepeatingController();
        rc.setMonthStatistics(st,95.0);

        assertEquals(1,st.monthStatistics().size());
        assertEquals(95.0,st.monthStatistics().get(0).getPercentage());
        assertEquals(1, st.monthStatistics().get(0).getAmount());

        rc.setMonthStatistics(st,97.0);

        for(MonthStatistics msss : st.monthStatistics()){
            Log.d("msss", msss.getWhichMonth() + " " + msss.getWhichYear() + " " + msss.getPercentage());
        }
        assertEquals(1,st.monthStatistics().size());
        assertEquals(96.0,st.monthStatistics().get(0).getPercentage());
        assertEquals(2,st.monthStatistics().get(0).getAmount());

        st.monthStatistics().get(0).delete();
        st.delete();
        db.delete();
        cat.delete();

    }
}
