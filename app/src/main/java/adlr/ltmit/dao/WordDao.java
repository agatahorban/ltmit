package adlr.ltmit.dao;

import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-02-22.
 */

public class WordDao  extends CrudDao<Word> {

    public WordDao() {
        super(Word.class);
    }
}
