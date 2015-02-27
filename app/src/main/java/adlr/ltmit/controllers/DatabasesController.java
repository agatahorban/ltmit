package adlr.ltmit.controllers;

import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.dao.DatabaseDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;

/**
 * Created by Agata on 2015-02-27.
 */
public class DatabasesController {

    private DatabaseDao dd;

    public DatabasesController(){
        dd = new DatabaseDao();
    }

    public void addNewDatabase(String name, String categoryName, int priority){
        Category c = CategoryDao.getCategoryWithSomeName(categoryName);
        Database db = new Database(priority, c, name);
        db.save();
    }
}
