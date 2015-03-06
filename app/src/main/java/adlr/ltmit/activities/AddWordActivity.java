package adlr.ltmit.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import adlr.ltmit.R;
import adlr.ltmit.controllers.DatabasesController;

public class AddWordActivity extends ActionBarActivity {

    private DatabasesController dc;
    private String databaseName;
    private EditText wordET, translationET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        dc = new DatabasesController();
        Intent intent = getIntent();
        databaseName = intent.getStringExtra("DB_NAME");
        wordET = (EditText) findViewById(R.id.wordET);
        translationET = (EditText) findViewById(R.id.translationET);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_word, menu);
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

    public void addWord(View view){
        dc.addWord(wordET.getText().toString(), translationET.getText().toString(),databaseName);
    }

    public void finishMe(View view){
        this.finish();
    }
}
