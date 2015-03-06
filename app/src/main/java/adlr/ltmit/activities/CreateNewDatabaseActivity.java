package adlr.ltmit.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import adlr.ltmit.R;
import adlr.ltmit.bl.Priority;
import adlr.ltmit.controllers.DatabasesController;
import adlr.ltmit.dao.CategoryDao;

public class CreateNewDatabaseActivity extends ActionBarActivity {
    private DatabasesController dc;
    private CategoryDao cd;
    private EditText databaseNamePortraitET, categoryDbNamePortaitET;
    private RadioButton radioButton4, radioButton5, radioButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_database);
        dc = new DatabasesController();
        cd = new CategoryDao();
        databaseNamePortraitET = (EditText) findViewById(R.id.databaseNamePortraitET);
        categoryDbNamePortaitET = (EditText) findViewById(R.id.categoryDbNameET);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton6 = (RadioButton) findViewById(R.id.radioButton4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_new_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveMe(View view){
        int prio = 0;
        if(radioButton4.isChecked()) prio = Priority.HIGH.getValue();
        else if (radioButton5.isChecked()) prio = Priority.MEDIUM.getValue();
        else prio = Priority.LOW.getValue();
        dc.addNewDatabase(databaseNamePortraitET.getText().toString(),categoryDbNamePortaitET.getText().toString(), prio);
        this.finish();
    }

    public void cancelMe(View view){
        this.finish();
    }
}
