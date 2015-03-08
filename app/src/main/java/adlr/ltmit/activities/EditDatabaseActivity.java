package adlr.ltmit.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;

import java.util.ArrayList;
import java.util.List;

import adlr.ltmit.R;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.dao.WordDao;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Word;


public class EditDatabaseActivity extends ActionBarActivity {
    private String dbName;
    private RepeatingController rc;

    private TableLayout t1;
    private List<TextView> meanings;
    private List<TextView> translations;
    private List<ImageView> images;
    private List<TableRow> rows;
    private Database db;
    private TableRow tr;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        category = intent.getStringExtra("CAT_NAME");
        rc = new RepeatingController();

        t1 = (TableLayout) findViewById(R.id.tableLayout1);
        meanings = new ArrayList<>();
        translations = new ArrayList<>();
        images = new ArrayList<>();
        rows = new ArrayList<>();


        db = rc.findProperDatabase(dbName);
        settingUp();

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

    public void deleteDatabase(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("");
        builder.setMessage(getResources().getString(R.string.delete_question));

        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!(db.words().isEmpty())){
                    ActiveAndroid.beginTransaction();
                    try {
                    for(Word w : db.words())
                    { w.delete(); }

                    ActiveAndroid.setTransactionSuccessful();
                    }finally{
                        ActiveAndroid.endTransaction();
                    }
                }
                db.delete();

                Intent intent = new Intent(EditDatabaseActivity.this, DatabaseListActivity.class);
                intent.putExtra("DB_NAME", dbName);
                intent.putExtra("CAT_NAME", category);
                startActivity(intent);

            }
        });
        builder.setNegativeButton(getResources().getString(R.string.no), null);
        builder.setCancelable(false);
        AlertDialog dialog =  builder.show();

        int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = dialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundColor(getResources().getColor(android.R.color.secondary_text_dark_nodisable));

        Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        bt.setBackgroundResource(R.drawable.alert_button_layout);
        Button bt2 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        bt2.setBackgroundResource(R.drawable.alert_button_layout2);

    }

    public void refreshViews(){
        for(TableRow r : rows) {
            t1.removeView(r);
        }
        meanings = new ArrayList<>();
        translations = new ArrayList<>();
        images = new ArrayList<>();
        rows = new ArrayList<>();

        settingUp();
    }

    public void settingUp(){
        if(db!=null ) {
            for (Word w : db.words()) {
                tr = new TableRow(this);
                TextView tv = new TextView(this);
                TextView tv2 = new TextView(this);

                tv.setText(w.getMeaning());
                tv2.setText(w.getTranslation());

                tv.setGravity(Gravity.LEFT);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
                tv.setLayoutParams(params);



                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textV = (TextView) v;
                        int z = -1;
                        for(int i = 0; i < images.size(); i++){
                            if(textV == meanings.get(i)){
                                z = i;
                                final Word w =WordDao.getWord(meanings.get(i).getText().toString(),translations.get(i).getText().toString(),db);
                                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(EditDatabaseActivity.this, R.style.AlertDialogCustom));
                                builder.setTitle("");
                                final EditText input = new EditText(EditDatabaseActivity.this);
                                input.setTextColor(getResources().getColor(R.color.fontColor));
                                input.setBackgroundResource(R.drawable.editext_layout);
                                builder.setTitle("");
                                builder.setMessage(getResources().getString(R.string.change_meaning));
                                builder.setView(input);

                                builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        w.setMeaning(input.getText().toString());
                                        w.save();
                                        refreshViews();
                                    }
                                });

                                builder.setNegativeButton(getResources().getString(R.string.no), null);

                                AlertDialog dialog = builder.create();
                                dialog.show();

                                int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
                                View titleDivider = dialog.findViewById(titleDividerId);
                                if (titleDivider != null)
                                    titleDivider.setBackgroundColor(getResources().getColor(android.R.color.secondary_text_dark_nodisable));

                                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                bt.setBackgroundResource(R.drawable.alert_button_layout);
                                Button bt2 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                                bt2.setBackgroundResource(R.drawable.alert_button_layout2);

                            }
                        }
                    }
                });

                tv2.setGravity(Gravity.LEFT);
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv2.setLayoutParams(params);

                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textV = (TextView) v;
                        int z = -1;
                        for(int i = 0; i < images.size(); i++){
                            if(textV == translations.get(i)){
                                z = i;
                                final Word w =WordDao.getWord(meanings.get(i).getText().toString(),translations.get(i).getText().toString(),db);
                                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(EditDatabaseActivity.this, R.style.AlertDialogCustom));
                                builder.setTitle("");
                                final EditText input = new EditText(EditDatabaseActivity.this);
                                input.setTextColor(getResources().getColor(R.color.fontColor));
                                input.setBackgroundResource(R.drawable.editext_layout);
                                builder.setTitle("");
                                builder.setMessage(getResources().getString(R.string.change_translation));
                                builder.setView(input);

                                builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        w.setTranslation(input.getText().toString());
                                        w.save();
                                        refreshViews();
                                    }
                                });

                                builder.setNegativeButton(getResources().getString(R.string.no), null);

                                AlertDialog dialog = builder.create();
                                dialog.show();

                                int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
                                View titleDivider = dialog.findViewById(titleDividerId);
                                if (titleDivider != null)
                                    titleDivider.setBackgroundColor(getResources().getColor(android.R.color.secondary_text_dark_nodisable));

                                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                bt.setBackgroundResource(R.drawable.alert_button_layout);
                                Button bt2 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                                bt2.setBackgroundResource(R.drawable.alert_button_layout2);

                            }
                        }
                    }
                });

                params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
                ImageView img = new ImageView(this);
                img.setImageResource(R.drawable.icon1);
                img.setLayoutParams(params);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView imgV = (ImageView) v;
                        int z = -1;
                        for(int i = 0; i < images.size(); i++){
                            if(imgV == images.get(i)){
                                z = i;
                                db.words().get(z).delete();
                                t1.removeView(rows.get(z));

                            }
                        }
                    }
                });

                images.add(img);

                meanings.add(tv);
                translations.add(tv2);

                tr.addView(tv);
                tr.addView(tv2);
                tr.addView(img);
                rows.add(tr);
                t1.addView(tr);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent;
        if(rc.findProperDatabase(dbName)!=null) {
            intent = new Intent(EditDatabaseActivity.this, DatabasesActivity.class);
            intent.putExtra("DB_NAME", dbName);
        }
        else {
            intent = new Intent(EditDatabaseActivity.this, DatabaseListActivity.class);
            intent.putExtra("DB_NAME", dbName);
            intent.putExtra("CAT_NAME", category);
        }
        startActivity(intent);
    }

}
