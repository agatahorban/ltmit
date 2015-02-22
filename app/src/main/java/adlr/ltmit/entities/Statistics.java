package adlr.ltmit.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Agata on 2015-02-21.
 */

@Table(name = "STATISTICS")
public class Statistics extends Model {

    @Column(name = "GENERAL_STATISTICS")
    private long generalStatistics;

    @Column(name="DATABASE", notNull = true)
    private Database database;

    public Statistics() {
        super();
    }

    public Statistics(long generalStatistics, Database database) {
        this.generalStatistics = generalStatistics;
        this.database = database;
    }

    public long getGeneralStatistics() {
        return generalStatistics;
    }

    public void setGeneralStatistics(long generalStatistics) {
        this.generalStatistics = generalStatistics;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;

    }
    public List<MonthStatistics> monthStatistics() {
        return getMany(MonthStatistics.class, "STATISTICS");
    }
}
