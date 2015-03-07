package adlr.ltmit.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adlr.ltmit.R;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-03-07.
 */
public class EditDatabasesActivity extends ActionBarActivity {
    private String dbName;
    private RepeatingController rc;
    private TableLayout t1;
    private List<TextView> meanings;
    private List<TextView> translations;
    private List<ImageView> images;
    private Database db;
    private TableRow tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_words);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        rc = new RepeatingController();
        t1 = (TableLayout) findViewById(R.id.tableLayout1);
        meanings = new ArrayList<>();
        translations = new ArrayList<>();
        images = new ArrayList<>();

        db = rc.findProperDatabase(dbName);
        for(Word w : db.words()){
            tr = new TableRow(this);
            TextView tv = new TextView(this);
            TextView tv2 = new TextView(this);

            tv.setText(w.getMeaning());
            tv2.setText(w.getTranslation());

            tv.setGravity(Gravity.LEFT);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            tv.setLayoutParams(params);

            tv2.setGravity(Gravity.LEFT);
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            tv2.setLayoutParams(params);


            params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
            ImageView img = new ImageView(this);
            img.setImageResource(R.drawable.icon1);
            img.setLayoutParams(params);

            images.add(img);

            meanings.add(tv);
            translations.add(tv2);

            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(img);
            t1.addView(tr);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_database, menu);
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


}
