package adlr.ltmit.dao;

import com.activeandroid.query.Select;

import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.Statistics;

/**
 * Created by Agata on 2015-02-22.
 */

public class StatisticsDao extends CrudDao<Statistics>{
    public static Statistics getStatistics(Database db){
        return new Select()
                .from(Statistics.class)
                .where("DATABS = ?", db.getId())
                .executeSingle();
    }

    public StatisticsDao() {
        super(Statistics.class);
    }
}
