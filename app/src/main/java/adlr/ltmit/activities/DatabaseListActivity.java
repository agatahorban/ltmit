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
import adlr.ltmit.bl.Calculator;
import adlr.ltmit.bl.DatabaseItem;
import adlr.ltmit.bl.DbAdapter;
import adlr.ltmit.controllers.DatabasesController;
import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;

public class DatabaseListActivity extends ActionBarActivity {
    private DatabasesController dc;
    private String[] stringArray;


    private ListView listViewDatabases;

    DatabaseItem[] dbItems;

    private String dbName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);
        Intent intent = getIntent();
        String database = intent.getStringExtra("CAT_NAME");
        listViewDatabases = (ListView) findViewById(R.id.listViewDatabases);
        dc = new DatabasesController();

        stringArray = dc.getAllDatabases(database);

        dbItems = new DatabaseItem[stringArray.length];

        Category cat = CategoryDao.getCategoryWithSomeName(database);
        int i = 0;
        for(Database d : cat.databases()){
            DatabaseItem di = new DatabaseItem(d.getName(),R.drawable.icon1, Calculator.getDay(d.getDateToRepeat())+ " " + Calculator.getMonth(d.getDateToRepeat()));
            dbItems[i] = di;
            i++;
        }

        DbAdapter adapter = new DbAdapter(this,
                R.layout.listview_item_row, dbItems);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, R.layout.row_layout, R.id.label,
//                stringArray);
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
                dbName = dbItems[position].getName();
                Intent intent = new Intent(DatabaseListActivity.this, DatabasesActivity.class);
                intent.putExtra("DB_NAME", dbName);
                startActivityForResult(intent, 1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            finish();
        }
    }


}
