package adlr.ltmit.controllers;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.List;

import adlr.ltmit.R;
import adlr.ltmit.bl.Calculator;
import adlr.ltmit.bl.DatabaseItem;
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

    public DatabaseItem[] setDbItems(List<Database> dbs){
        DatabaseItem[] dbItems = new DatabaseItem[dbs.size()];
        String date;
        int i = 0;
        for(Database d : dbs){
            if(Calculator.isMoreThan14Days(d.getDateToRepeat())) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                date = sdf.format(d.getDateToRepeat());
            }
            else{
                StringBuilder sb = new StringBuilder();
                sb.append(Calculator.calculateDays(d.getDateToRepeat()));
                sb.append(" days");
                date = sb.toString();
            }
            DatabaseItem di = new DatabaseItem(d.getName(), R.drawable.icon1, date);
            dbItems[i] = di;
            i++;
        }

        return dbItems;
    }
}
