package adlr.ltmit.dao;

import adlr.ltmit.entities.Statistics;

/**
 * Created by Agata on 2015-02-22.
 */

public class StatisticsDao extends CrudDao<Statistics>{
    public StatisticsDao() {
        super(Statistics.class);
    }
}
