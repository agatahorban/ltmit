package adlr.ltmit;

import android.test.suitebuilder.annotation.SmallTest;

import com.activeandroid.query.Delete;

import junit.framework.TestCase;

import java.util.List;

import adlr.ltmit.bl.Priority;
import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.dao.DatabaseDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.MonthStatistics;


/**
 * Created by Agata on 2015-02-25.
 */

public class DatabaseTest extends TestCase {
    @SmallTest
    public void test(){

        List<Category> d = new Delete().from(Database.class).execute();
        List<Category> l = new Delete().from(Category.class).execute();

        Category cat = new Category("cat1");
        cat.save();
        Category cat2 = new Category("cat2");
        cat2.save();

        CategoryDao cd = new CategoryDao();
        List<Category> list = cd.selectAll();

        assertEquals(2, list.size());

        Database db = new Database(Priority.HIGH.getValue(), cat, "db1");
        db.save();

        DatabaseDao dd = new DatabaseDao();

        Database db2 = dd.getDatabaseWithSomeName("db1");
        assertNotNull(db2);

    }
}
