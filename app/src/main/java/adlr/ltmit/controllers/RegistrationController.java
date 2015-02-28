package adlr.ltmit.controllers;

import android.content.SharedPreferences;



/**
 * Created by Agata on 2015-02-25.
 */
public class RegistrationController {

    public void saveData(String sname, String semail, boolean slearning, SharedPreferences sp){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",sname);
        editor.putBoolean("learning",slearning);

        if(slearning= true)
            editor.putString("email",semail);
        else
            editor.putString("email","");

        editor.putBoolean("isRegistered",true);
        editor.commit();

    }
}
