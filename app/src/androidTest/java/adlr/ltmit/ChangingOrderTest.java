package adlr.ltmit;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.util.List;

import adlr.ltmit.bl.Priority;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-03-04.
 */
public class ChangingOrderTest extends TestCase {
    @SmallTest
    public void test() {
        RepeatingController rc = new RepeatingController();

        Category cat = new Category("cat5558");
        cat.save();
        Database db = new Database(Priority.HIGH.getValue(), cat, "db5558");
        db.save();
        Word w1 = new Word("kot","cat",db);
        w1.save();
        Word w2 = new Word("pies","dog",db);
        w2.save();

        List<Word> newWordList = rc.changingOrder(db.words());
        assertEquals(2,newWordList.size());

        Word w3 = new Word("kon","horse",db);
        w3.save();
        Word w4 = new Word("slon","elephant",db);
        w4.save();

        newWordList = rc.changingOrder(db.words());
        assertEquals(4,newWordList.size());

        w1.delete();
        w2.delete();
        w3.delete();
        w4.delete();

        db.delete();
        cat.delete();

    }
}
