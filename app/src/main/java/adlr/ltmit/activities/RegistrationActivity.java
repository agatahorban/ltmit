package adlr.ltmit.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import adlr.ltmit.R;
import adlr.ltmit.controllers.RegistrationController;

public class RegistrationActivity extends ActionBarActivity {

    private EditText nameET, emailET;
    private CheckBox emailCheckBox;
    private static final String prefs = "MyPreferences";
    private SharedPreferences sp;

    RegistrationController rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        nameET = (EditText) findViewById(R.id.nameET);
        emailET = (EditText) findViewById(R.id.emailET);
        emailET.setEnabled(false);
        emailCheckBox = (CheckBox) findViewById(R.id.emailCheckBox);
        emailCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    emailET.setEnabled(true);
                else {
                    emailET.setText("");
                    emailET.setEnabled(false);
                }
            }
        });
        sp = getSharedPreferences(prefs, Context.MODE_PRIVATE);
        rc = new RegistrationController();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    public void registerMe(View view){
        rc.saveData(nameET.getText().toString(), emailET.getText().toString(), emailCheckBox.isChecked(),sp);

        Intent intent = new Intent(this, OrganizationActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void cancelMe(View view){
        this.finish();
    }



}
