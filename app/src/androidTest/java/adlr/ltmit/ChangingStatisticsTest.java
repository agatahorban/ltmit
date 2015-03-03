package adlr.ltmit;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import adlr.ltmit.bl.Priority;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.dao.StatisticsDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Statistics;

/**
 * Created by Agata on 2015-03-03.
 */
public class ChangingStatisticsTest extends TestCase {
    @SmallTest
    public void test(){
        Category cat = new Category("cat111237");
        cat.save();
        Database db = new Database(Priority.HIGH.getValue(), cat, "db111237");
        db.save();


        Statistics st = new Statistics(85.0,db);
        st.setAmount(1);
        st.save();
        RepeatingController rc = new RepeatingController();
        rc.setStatistics(db.getName(),95.0);

        Statistics st2 = StatisticsDao.getStatistics(db);
        assertEquals(2, st2.getAmount());
        assertEquals(90.0,st2.getGeneralStatistics());

        rc.setStatistics(db.getName(),96.0);
        assertEquals(3, st2.getAmount());
        assertEquals(92.0,st2.getGeneralStatistics());


        st2.delete();
        db.delete();
        cat.delete();

    }
}
