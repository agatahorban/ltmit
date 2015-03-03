package adlr.ltmit.controllers;

import com.activeandroid.query.Delete;

import java.util.Date;
import java.util.List;

import adlr.ltmit.bl.Calculator;
import adlr.ltmit.dao.DatabaseDao;
import adlr.ltmit.dao.MonthStatisticsDao;
import adlr.ltmit.dao.StatisticsDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.MonthStatistics;
import adlr.ltmit.entities.Statistics;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-03-02.
 */

public class RepeatingController {

    public static void mainWordLogic(Word word, int isRembembered){
        if(isRembembered==1)
            word.setIsRemembered(isRembembered);
        else {
            word.setAmount(word.getAmount()+1);
            word.save();
            if(word.getAmount()>=3)
                word.setIsCritical(1);
        }
    }
    public void editWordIsRemembered(Word word, int isRemembered){
        word.setIsRemembered(isRemembered);
        word.save();
    }

    public void setStatistics(String db, double percentage){
        Database database = DatabaseDao.getDatabaseWithSomeName(db);
        Statistics stat = StatisticsDao.getStatistics(database);
        int earlierAmount = stat.getAmount();
        double earlierPercentage = stat.getGeneralStatistics();
        int amount = earlierAmount+1;
        double newPercentage = (earlierAmount * earlierPercentage + percentage) / amount;
        stat.setAmount(amount);
        stat.setGeneralStatistics(newPercentage);
        stat.save();
    }

    public void setMonthStatistics(Statistics stat, double percentage){
        Date today = new Date(System.currentTimeMillis());
        int year = Calculator.getYear(today);
        int month = Calculator.getMonth(today);
        MonthStatistics statistics = MonthStatisticsDao.getStatistics(month, year, stat);
        if(statistics == null || !(statistics.getStatistics().equals(stat))){
            MonthStatistics ms = new MonthStatistics(year, month, stat);
            ms.setAmount(1);
            ms.setPercentage(percentage);
            ms.save();
        }
        else{
            int earlierAmount = statistics.getAmount();
            double earlierPercentage = statistics.getPercentage();
            int amount = earlierAmount+1;
            double newPercentage = (earlierAmount * earlierPercentage + percentage) / amount;

//            List<MonthStatistics> l = new Delete().from(MonthStatistics.class).where("WHICH_MONTH = ?",month).where("WHICH_YEAR = ?",year).where("STATISTICS = ?", stat.getId()).execute();

//            MonthStatistics ms = new MonthStatistics();
//            ms.setStatistics(stat);
            statistics.setAmount(amount);
            statistics.setPercentage(newPercentage);
            statistics.save();
        }
    }



}
