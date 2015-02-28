package adlr.ltmit.controllers;

import android.content.SharedPreferences;

/**
 * Created by Agata on 2015-02-28.
 */

public class ProfileController {

    public String getName(SharedPreferences sp){
        return sp.getString("name","");

    }

    public String getEmail(SharedPreferences sp){
        return sp.getString("email","");

    }

    public boolean getWantEmail(SharedPreferences sp){
        return sp.getBoolean("learning",false);
    }


}
