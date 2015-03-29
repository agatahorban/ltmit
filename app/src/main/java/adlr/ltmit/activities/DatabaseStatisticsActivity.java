package adlr.ltmit.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.DecimalFormat;
import java.util.Map;

import adlr.ltmit.R;
import adlr.ltmit.controllers.RepeatingController;
import adlr.ltmit.controllers.StatisticsController;
import adlr.ltmit.dao.DatabaseDao;

public class DatabaseStatisticsActivity extends ActionBarActivity {

    private String dbName;
    private String category;
    private RepeatingController rc;
    private StatisticsController sc;
    private TextView percentageTv, amountTv, dbStatNameTextView;
    private LinearLayout lyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_statistics);
        Intent intent = getIntent();

        dbName = intent.getStringExtra("DB_NAME");
        category = intent.getStringExtra("CAT_NAME");

        rc = new RepeatingController();
        sc = new StatisticsController();

        percentageTv = (TextView) findViewById(R.id.memorizationPercentageTv2);
        amountTv = (TextView) findViewById(R.id.amountTv2);
        dbStatNameTextView = (TextView) findViewById(R.id.dbStatNameTextView);

        percentageTv.setText(new DecimalFormat("##.##").format(rc.findProperDatabase(dbName).getGeneralStatistics()));
        amountTv.setText(Integer.toString(rc.findProperDatabase(dbName).getAmount()));
        dbStatNameTextView.setText(dbName);

        lyt = (LinearLayout) findViewById(R.id.chart);
        makeStatistics();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_database_statistics, menu);
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

    @Override
    public void onBackPressed() {
            Intent intent = new Intent(DatabaseStatisticsActivity.this, DatabasesActivity.class);
            intent.putExtra("DB_NAME", dbName);
            intent.putExtra("CAT_NAME", category);
            startActivity(intent);
    }

    public void makeStatistics(){
        GraphicalView chartView = sc.makeStatistics(dbName,this);
        lyt.addView(chartView,0);
    }
}
