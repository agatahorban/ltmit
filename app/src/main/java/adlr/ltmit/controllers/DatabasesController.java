package adlr.ltmit.controllers;

import android.util.Log;

import java.util.List;

import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.dao.DatabaseDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-02-27.
 */
public class DatabasesController {
    DatabaseDao dd;

    public DatabasesController(){
        dd = new DatabaseDao();
    }

    public String[] getAllDatabases(String category){
        Category c = CategoryDao.getCategoryWithSomeName(category);
        List<Database> databs = c.databases();
        String[] dbsString = new String[databs.size()];
        for(int i = 0; i<databs.size(); i++ ){
            dbsString[i] = databs.get(i).getName();
        }
        return dbsString;
    }

    public void addNewDatabase(String name, String categoryName, int priority){
        Category c = CategoryDao.getCategoryWithSomeName(categoryName);
        Database db = new Database(priority, c, name);
        db.save();
    }

    public void addWord(String word, String translation, String database ){
        Database db = DatabaseDao.getDabatabaseWithSomeName(database);
        Word wordEntity = new Word(word,translation,db);
        wordEntity.save();
        for(Word w : db.words()){
            Log.d(database,w.getMeaning() + w.getTranslation());
        }
    }
}
