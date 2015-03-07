package adlr.ltmit.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
        getMenuInflater().inflate(R.menu.menu_databases, menu);
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
        Intent intent = new Intent(DatabasesActivity.this, EditDatabasesActivity.class);
        intent.putExtra("DB_NAME", dbName);
        startActivity(intent);
//        startActivityForResult(intent, 1000);

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
