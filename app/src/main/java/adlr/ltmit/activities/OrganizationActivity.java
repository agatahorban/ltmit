package adlr.ltmit.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import adlr.ltmit.R;

import adlr.ltmit.controllers.CategoriesController;
import adlr.ltmit.fragments.AddingFragment;
import adlr.ltmit.fragments.DatabasesListFragment;
import adlr.ltmit.fragments.ProfileFragment;

public class OrganizationActivity extends ActionBarActivity implements ActionBar.TabListener {

    private ActionBar actionBar;
    private ViewPager viewPager;

    private Fragment myFragment;

    private CategoriesController cc;

    private EditText categoryNameET2;
    private TextView categoryNameTv2;
    private Button buttonCategoryOk2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_acitivity);

        viewPager = (ViewPager) findViewById(R.id.pager);
        OrganizationAdapter adapter = new OrganizationAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("DATABASES");
        tab1.setTabListener(this);

        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText("YOUR PROFILE");
        tab2.setTabListener(this);

        ActionBar.Tab tab3 = actionBar.newTab();
        tab3.setText("CREATE NEW...");
        tab3.setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);

        cc = new CategoriesController();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_organization, menu);
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    class OrganizationAdapter extends FragmentPagerAdapter{

        public OrganizationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if(position == 0){
                myFragment = new DatabasesListFragment();
            }else if(position == 1) {
               myFragment = new ProfileFragment();
            } else {
                myFragment = new AddingFragment();
            }
            return myFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void addNewCategory(View view){
        if(getScreenOrientation()== Configuration.ORIENTATION_PORTRAIT){
        Intent intent = new Intent(this, CreateNewCategoryActivity.class);
        startActivity(intent);}
        else {
            categoryNameET2 = (EditText) findViewById(R.id.categoryNameET2);
            categoryNameTv2 = (TextView) findViewById(R.id.categoryNameTv2);
            buttonCategoryOk2 = (Button) findViewById(R.id.buttonCategoryOk2);
            categoryNameET2.setVisibility(View.VISIBLE);
            categoryNameTv2.setVisibility(View.VISIBLE);
            buttonCategoryOk2.setVisibility(View.VISIBLE);

        }
    }

    public void saveMe2(View view){

        categoryNameET2 = (EditText) findViewById(R.id.categoryNameET2);
        cc.addNewCategory(categoryNameET2.getText().toString());
    }


    public int getScreenOrientation()
    {
        Display getOrient = getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }
}
