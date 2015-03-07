package adlr.ltmit.activities;

import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adlr.ltmit.MainActivity;
import adlr.ltmit.R;
import adlr.ltmit.controllers.RepeatingController;
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
    int orientation = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        rc = new RepeatingController();

        t1 = (TableLayout) findViewById(R.id.tableLayout1);
        meanings = new ArrayList<>();
        translations = new ArrayList<>();
        images = new ArrayList<>();
        rows = new ArrayList<>();

        db = rc.findProperDatabase(dbName);
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

                tv2.setGravity(Gravity.LEFT);
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv2.setLayoutParams(params);


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
                    for(Word w : db.words())
                        w.delete();
                }
                db.delete();


                Intent previousScreen = new Intent(getApplicationContext(), DatabasesActivity.class);
                setResult(1000, previousScreen);


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

    @Override
    public void onBackPressed(){
        Intent newIntent = new Intent(EditDatabaseActivity.this, OrganizationActivity.class);
        startActivity(newIntent);
    }
}
