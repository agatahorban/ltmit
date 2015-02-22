package adlr.ltmit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;

import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;


public class MainActivity extends ActionBarActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryDao dao = new CategoryDao();

        Category c2 = dao.select(1);
        tv = (TextView) findViewById(R.id.hello);
        tv.setText(c2.name);
//

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Category getRandom(String name) {
        return new Select()
                .from(Category.class)
                .where("Name = ?", "kategoria7")
                .executeSingle();
    }

    public static Database getRandomDatabase(Category cat) {
        return new Select()
                .from(Database.class)
                .where("CATEGORY = ?", cat.getId())
                .orderBy("RANDOM()")
                .executeSingle();
    }
}
