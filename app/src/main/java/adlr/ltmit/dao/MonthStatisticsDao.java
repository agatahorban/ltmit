package adlr.ltmit.dao;

import adlr.ltmit.entities.MonthStatistics;


/**
 * Created by Agata on 2015-02-22.
 */

public class MonthStatisticsDao  extends CrudDao<MonthStatistics> {
    public MonthStatisticsDao() {
        super(MonthStatistics.class);
    }
}
