package adlr.ltmit.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Agata on 2015-02-22.
 */

@Table(name = "CATEGORIES")
public class Category extends Model {

    @Column(name="NAME")
    public String name;



}


