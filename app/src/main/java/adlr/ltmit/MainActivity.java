package adlr.ltmit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;

import adlr.ltmit.activities.OrganizationActivity;
import adlr.ltmit.activities.RegistrationActivity;
import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;


public class MainActivity extends ActionBarActivity {

    private SharedPreferences sp;
    private static final String prefs = "MyPreferences";
    private boolean isRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(prefs, Context.MODE_PRIVATE);
        isRegistered = sp.getBoolean("isRegistered", false);
        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(5000);

                } catch(InterruptedException e){

                } finally{
                    Intent intent;
                    if(!isRegistered)
                        intent = new Intent(MainActivity.this, RegistrationActivity.class);
                    else
                        intent = new Intent(MainActivity.this, OrganizationActivity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        };
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
