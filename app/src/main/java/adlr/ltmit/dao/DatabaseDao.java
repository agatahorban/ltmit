package adlr.ltmit.dao;

import com.activeandroid.query.Select;

import java.util.List;

import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;

/**
 * Created by Agata on 2015-02-22.
 */
public class DatabaseDao extends CrudDao<Database>  {

    public DatabaseDao() {
        super(Database.class);
    }

    public static Database getDatabaseWithSomeName(String name) {
        return new Select()
                .from(Database.class)
                .where("DATABASE_NAME = ?",name)
                .executeSingle();
    }
    public static List<Database> getDatabaseFromCategory(Category category) {
        return new Select()
                .from(Database.class)
                .where("Category = ?", category.getId())
                .orderBy("Name ASC")
                .execute();
    }


    public static Database getDabatabaseWithSomeName(String name) {
        return new Select()
                .from(Database.class)
                .where("DATABASE_NAME = ?",name)
                .executeSingle();
    }

}
