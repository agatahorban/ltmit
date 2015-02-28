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
    private String name;

    public Category() {
        super();
    }


    public Category(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Database> databases() {
        return getMany(Database.class, "CATEGORY");}
}


