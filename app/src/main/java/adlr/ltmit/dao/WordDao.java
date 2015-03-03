package adlr.ltmit.dao;

import com.activeandroid.query.Select;

import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-02-22.
 */

public class WordDao  extends CrudDao<Word> {

    public WordDao() {
        super(Word.class);
    }

    public static Word getWord(String meaning, String translation, Database db){
        return new Select()
                .from(Word.class)
                .where("MEANING = ?",meaning)
                .where("TRANSLATION = ?",translation)
                .where("DATABS = ?", db.getId())
                .executeSingle();
    }

}
