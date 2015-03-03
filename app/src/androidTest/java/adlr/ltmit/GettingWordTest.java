package adlr.ltmit;

import android.test.suitebuilder.annotation.SmallTest;

import com.activeandroid.query.Delete;

import junit.framework.TestCase;

import java.util.List;

import adlr.ltmit.bl.Priority;
import adlr.ltmit.dao.WordDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-03-02.
 */
public class GettingWordTest extends TestCase {
    @SmallTest
    public void test(){
        Category cat = new Category("cat1112");
        cat.save();
        Database db = new Database(Priority.HIGH.getValue(), cat, "db1112");
        db.save();
        Word w1 = new Word("kot","cat",db);
        w1.save();
        Word w2 = new Word("pies","dog",db);
        w2.save();

        Word w = WordDao.getWord("pies","dog",db);
        assertEquals("pies",w.getMeaning());
        assertEquals("dog",w.getTranslation());

        w1.delete();
        w2.delete();
        db.delete();
        cat.delete();
    }
}
