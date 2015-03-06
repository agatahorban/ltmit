package adlr.ltmit.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.text.DecimalFormat;

import adlr.ltmit.R;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Word;


public class EditDatabaseActivity extends ActionBarActivity {
    private String dbName;
    private RepeatingController rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        rc = new RepeatingController();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_database, menu);
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

    public void deleteDatabase(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("");
        builder.setMessage(getResources().getString(R.string.delete_question));

        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Database db = rc.findProperDatabase(dbName);
                if(!(db.words().isEmpty())){
                    for(Word w : db.words())
                        w.delete();
                }
                db.delete();

                Intent previousScreen = new Intent(getApplicationContext(), DatabasesActivity.class);
                previousScreen.putExtra("DB_NAME", dbName);
                setResult(1000, previousScreen);
                EditDatabaseActivity.this.finish();

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

}
