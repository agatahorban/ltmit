package adlr.ltmit.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import adlr.ltmit.R;

public class DatabasesActivity extends ActionBarActivity {

    private String dbName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databases);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_databases, menu);
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

    public void addWords(View view){
        Intent intent = new Intent(DatabasesActivity.this, AddWordActivity.class);
        intent.putExtra("DB_NAME", dbName);
        startActivity(intent);
    }

    public void startRepeating(View view){
        Intent intent = new Intent(DatabasesActivity.this, RepeatingActivity.class);
        intent.putExtra("DB_NAME", dbName);
        startActivity(intent);
    }

    public void editDatabase(View view){
        Intent intent = new Intent(DatabasesActivity.this, EditDatabaseActivity.class);
        intent.putExtra("DB_NAME", dbName);
        startActivityForResult(intent, 1000);

    }
    public void exit(View view){
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            Intent previousScreen = new Intent(getApplicationContext(), DatabaseListActivity.class);
            previousScreen.putExtra("DB_NAME", dbName);
            setResult(1001, previousScreen);
            finish();
        }
    }

}
