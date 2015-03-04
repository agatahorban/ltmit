package adlr.ltmit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import adlr.ltmit.R;
import adlr.ltmit.bl.Calculator;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.dao.StatisticsDao;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Statistics;
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
    private Database db;
    private TextView properAnswerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        rc = new RepeatingController();
        db = rc.findProperDatabase(dbName);
        wordETRepeating = (EditText) findViewById(R.id.wordETRepeating);
        translationETRepeating = (EditText) findViewById(R.id.translationETRepeating);
        properAnswerTv = (TextView) findViewById(R.id.properAnswerTv);

        if(savedInstanceState == null) {
            words = rc.changingOrder(rc.findProperDatabaseWords(dbName));
            rc.savingTemporaryData(words, dbName);
        }
        if (savedInstanceState !=null) {
            counter = savedInstanceState.getInt("COUNTER", counter);
            words = rc.findProperDatabaseWords("temporary");

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
        if(counter<words.size()-1){
        Word w = words.get(counter);
        Word previous = words.get(counter);
        if(translationETRepeating.getText().toString().equals(w.getTranslation())){
            w.setIsRemembered(1);
        }
        translationETRepeating.setText("");
        properAnswerTv.setText("Proper answer: " + previous.getTranslation());
            counter++;
            wordETRepeating.setText(words.get(counter).getMeaning());
        }

        else {
            double percentage = rc.countPercentage(words);

            Word previous = words.get(counter);
            properAnswerTv.setText("Proper answer: " + previous.getTranslation() +" END");


            if(db.getStatistics() == null) {
                Statistics stat = new Statistics(0.0, db);
                stat.save();
            }
            rc.setStatistics(dbName,percentage);

            Statistics stat = StatisticsDao.getStatistics(db);

            rc.setMonthStatistics(stat,percentage);

            for(Word word : db.words())
            {
                word.setIsRemembered(0);
                word.setIsCritical(0);
                word.setAmount(0);
                word.save();
            }

            db.setDateToRepeat(Calculator.calculateDate(System.currentTimeMillis(),db.getPriority(),percentage));

            rc.deleteTemporaryDb();




        }

    }

    public void finishMe(View view){
        this.finish();
    }
}
