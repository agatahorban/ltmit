package adlr.ltmit.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import adlr.ltmit.R;
import adlr.ltmit.bl.DatabaseItem;
import adlr.ltmit.bl.DbAdapter;
import adlr.ltmit.controllers.DatabasesController;
import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.entities.Category;


public class DatabaseListActivity extends ActionBarActivity {
    private DatabasesController dc;

    private ListView listViewDatabases;
    DatabaseItem[] dbItems;
    private String dbName;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);

        Intent intent = getIntent();
        category = intent.getStringExtra("CAT_NAME");
        listViewDatabases = (ListView) findViewById(R.id.listViewDatabases);

        dc = new DatabasesController();

        Category cat = CategoryDao.getCategoryWithSomeName(category);
        dbItems = dc.setDbItems(cat.databases());

        DbAdapter adapter = new DbAdapter(this,
                R.layout.listview_item_row, dbItems);

        listViewDatabases.setAdapter(adapter);
        listViewDatabases.setSelector(R.drawable.lselector);

        registerClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_database_list, menu);
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

    public void registerClick(){
        listViewDatabases = (ListView) findViewById(R.id.listViewDatabases);
        listViewDatabases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dbName = dbItems[position].getName();
                Intent intent = new Intent(DatabaseListActivity.this, DatabasesActivity.class);
                intent.putExtra("DB_NAME", dbName);
                intent.putExtra("CAT_NAME", category);
                startActivity(intent);
            }
        });
    }

}
