package adlr.ltmit.dao;

import com.activeandroid.query.Select;

import adlr.ltmit.entities.Category;

/**
 * Created by Agata on 2015-02-22.
 */

public class CategoryDao extends CrudDao<Category> {
    public CategoryDao() {
        super(Category.class);
    }

    public static Category getCategoryWithSomeName(String name) {
        return new Select()
                .from(Category.class)
                .where("NAME = ?",name)
                .executeSingle();
    }

}
