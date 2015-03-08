package adlr.ltmit.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import adlr.ltmit.R;
import adlr.ltmit.controllers.CategoriesController;

public class CreateNewCategoryActivity extends ActionBarActivity {
    private CategoriesController cc;
    private EditText categoryNameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_category);
        categoryNameET = (EditText) findViewById(R.id.categoryNameET);
        cc = new CategoriesController();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_new_category, menu);
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
        cc.addNewCategory(categoryNameET.getText().toString());
        this.finish();
    }

    public void cancelMe(View view){
        this.finish();
    }


}
