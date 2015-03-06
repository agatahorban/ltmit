package adlr.ltmit.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private double percentage;
    private boolean isFirstTime;

    private Word seenWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        rc = new RepeatingController();
        db = rc.findProperDatabase(dbName);

        isFirstTime = true;

        wordETRepeating = (EditText) findViewById(R.id.wordETRepeating);
        translationETRepeating = (EditText) findViewById(R.id.translationETRepeating);
        properAnswerTv = (TextView) findViewById(R.id.properAnswerTv);

        if (savedInstanceState == null) {
            words = rc.changingOrder(rc.findProperDatabaseWords(dbName));
            rc.savingTemporaryData(words, dbName);
            words = rc.findProperDatabaseWords("temporary");
        }
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("COUNTER", counter);
            words = rc.findProperDatabaseWords("temporary");
            isFirstTime = savedInstanceState.getBoolean("FIRST", true);
        }
        seenWord = words.get(counter);
        wordETRepeating.setText(seenWord.getMeaning());
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
        savedInstanceState.putBoolean("FIRST", isFirstTime);
    }

    public void nextWord(View view) {
        if (counter < words.size() - 1) {
            if (translationETRepeating.getText().toString().equals(seenWord.getTranslation())) {
                seenWord.setIsRemembered(1);
                seenWord.save();
            }

            translationETRepeating.setText("");
            properAnswerTv.setText("Proper answer: " + seenWord.getTranslation());
            counter++;
            seenWord = words.get(counter);
            wordETRepeating.setText(seenWord.getMeaning());
        } else {
            if (translationETRepeating.getText().toString().equals(seenWord.getTranslation())) {
                seenWord.setIsRemembered(1);
                seenWord.save();
            }

            percentage = rc.countPercentage(words);
            properAnswerTv.setText("Proper answer: " + seenWord.getTranslation());

            if(isFirstTime) {
                if (db.getStatistics() == null) {
                    Statistics stat = new Statistics(0.0, db);
                    stat.save();
                }
                rc.setStatistics(dbName, percentage);
                Statistics stat = StatisticsDao.getStatistics(db);
                rc.setMonthStatistics(stat, percentage);
                Log.d("STATISTICS", "DONE");

            }

            for (int i = words.size() - 1; i > -1; i--) {
                if (words.get(i).getIsRemembered() == 1)
                    words.remove(words.get(i));
            }

            if(percentage!=100.0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
                builder.setTitle("");
                StringBuilder sb = new StringBuilder();
                sb.append(getResources().getString(R.string.percentage));
                sb.append(" ");
                sb.append(new DecimalFormat("##.##").format(percentage));
                sb.append("%");
                sb.append("\n");
                sb.append(getResources().getString(R.string.wantLearn));
                builder.setMessage(sb.toString());

                builder.setPositiveButton("SURE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        counter = 0;
                        seenWord = words.get(counter);
                        wordETRepeating.setText(seenWord.getMeaning());
                        isFirstTime = false;
                    }
                });
                builder.setNegativeButton("NOT NOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RepeatingActivity.this.finish();
                    }
                });
                builder.setCancelable(false);
                AlertDialog dialog = builder.show();

                int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
                View titleDivider = dialog.findViewById(titleDividerId);
                if (titleDivider != null)
                    titleDivider.setBackgroundColor(getResources().getColor(android.R.color.secondary_text_dark_nodisable));

                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setBackgroundResource(R.drawable.alert_button_layout);
                Button bt2 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                bt2.setBackgroundResource(R.drawable.alert_button_layout2);

            }
            else{
                Toast toast = Toast.makeText(this, getResources().getString(R.string.cong), Toast.LENGTH_SHORT);
                toast.show();
                Thread thread = new Thread(){
                    @Override
                    public void run(){
                        try{
                            sleep(2500);

                        } catch(InterruptedException e){

                        } finally{
                            RepeatingActivity.this.finish();
                        }
                    }
                };
                thread.start();
            }


            for (Word word : db.words()) {
                word.setIsRemembered(0);
                word.setIsCritical(0);
                word.setAmount(0);
                word.save();
            }

            db.setDateToRepeat(Calculator.calculateDate(System.currentTimeMillis(), db.getPriority(), percentage));

            rc.deleteTemporaryDb();
        }


    }

    public void finishMe(View view){
        this.finish();
    }
}
