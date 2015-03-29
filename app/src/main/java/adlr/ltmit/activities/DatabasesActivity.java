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
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databases);
        Intent intent = getIntent();
        dbName = intent.getStringExtra("DB_NAME");
        category = intent.getStringExtra("CAT_NAME");
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
        intent.putExtra("CAT_NAME", category);
        startActivity(intent);
    }

    public void startRepeating(View view){
        Intent intent = new Intent(DatabasesActivity.this, RepeatingActivity.class);
        intent.putExtra("DB_NAME", dbName);
        intent.putExtra("CAT_NAME", category);
        startActivity(intent);
    }

    public void editDatabase(View view){
        Intent intent = new Intent(DatabasesActivity.this, EditDatabaseActivity.class);
        startAnotherActivity(intent);
    }

    public void seeStatistics(View view){
        Intent intent = new Intent(DatabasesActivity.this, DatabaseStatisticsActivity.class);
        startAnotherActivity(intent);
    }

    public void exit(View view){
        this.finish();
    }


    private void startAnotherActivity(Intent intent){
        intent.putExtra("DB_NAME", dbName);
        intent.putExtra("CAT_NAME", category);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DatabasesActivity.this, DatabaseListActivity.class);
        intent.putExtra("CAT_NAME", category);
        startActivity(intent);
    }
}
