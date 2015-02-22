package adlr.ltmit.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import adlr.ltmit.entities.Database;

/**
 * Created by Agata on 2015-02-21.
 */

@Table(name = "WORD")
public class Word extends Model{

    @Column(name = "MEANING", notNull = true)
    private String meaning;

    @Column(name = "TRANSLATION", notNull = true)
    private String translation;

    @Column(name = "IS_REMEMBERED")
    private int isRemembered;

    @Column(name="IS_CRITICAL")
    private int isCritical;

    @Column(name = "DATABASE", notNull = true)
    private Database database;

    public Word(){
        super();
    }

    public Word(String meaning, String translation, Database database) {
        super();
        this.meaning = meaning;
        this.database = database;
        this.translation = translation;
    }


}
