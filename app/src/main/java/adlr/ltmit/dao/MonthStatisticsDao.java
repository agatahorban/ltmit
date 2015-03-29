package adlr.ltmit.dao;

import com.activeandroid.query.Select;

import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.MonthStatistics;


/**
 * Created by Agata on 2015-02-22.
 */
public class MonthStatisticsDao  extends CrudDao<MonthStatistics> {

    public MonthStatisticsDao() {
        super(MonthStatistics.class);
    }

    public static MonthStatistics getStatistics(int month, int year){
        return new Select()
                .from(MonthStatistics.class)
                .where("WHICH_MONTH = ?",month)
                .where("WHICH_YEAR = ?",year)
                .executeSingle();
    }

    public static MonthStatistics getStatistics(int month, int year, Database db){
        return new Select()
                .from(MonthStatistics.class)
                .where("WHICH_MONTH = ?",month)
                .where("WHICH_YEAR = ?",year)
                .where("DATABS = ?", db.getId())
                .executeSingle();
    }
}
