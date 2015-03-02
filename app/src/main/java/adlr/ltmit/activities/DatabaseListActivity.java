package adlr.ltmit.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import adlr.ltmit.R;
import adlr.ltmit.controllers.DatabasesController;

public class DatabaseListActivity extends ActionBarActivity {
    private DatabasesController dc;
    private String[] stringArray;
    private ListView listViewDatabases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);
        Intent intent = getIntent();
        String database = intent.getStringExtra("CAT_NAME");
        listViewDatabases = (ListView) findViewById(R.id.listViewDatabases);
        dc = new DatabasesController();
        stringArray = dc.getAllDatabases(database);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.row_layout, R.id.label,
                stringArray);
        listViewDatabases.setAdapter(adapter);
        registerClick();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database_list, menu);
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

    public void registerClick(){
        listViewDatabases = (ListView) findViewById(R.id.listViewDatabases);
        listViewDatabases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dbName =parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(DatabaseListActivity.this, DatabasesActivity.class);
                intent.putExtra("DB_NAME", dbName);
                startActivity(intent);
            }
        });
    }

}
