package adlr.ltmit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import adlr.ltmit.R;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-03-02.
 */

public class RepeatingActivity extends ActionBarActivity {
    private String dbName;
    private RepeatingController rc;
    private List<Word> words;
    private int counter = 0;
    private EditText wordETRepeating, translationETRepeating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        rc = new RepeatingController();

        wordETRepeating = (EditText) findViewById(R.id.wordETRepeating);
        translationETRepeating = (EditText) findViewById(R.id.translationETRepeating);

        if(savedInstanceState == null)
            words = rc.changingOrder(rc.findProperDatabaseWords(dbName));

        if (savedInstanceState !=null) {
            counter = savedInstanceState.getInt("COUNTER", counter);

        }
        wordETRepeating.setText(words.get(counter).getMeaning());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_word, menu);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("COUNTER", counter);
    }

    public void nextWord(View view){
        counter++;
        wordETRepeating.setText(words.get(counter).getMeaning());
    }
}
