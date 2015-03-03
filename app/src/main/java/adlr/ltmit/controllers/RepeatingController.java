package adlr.ltmit.controllers;

import com.activeandroid.util.SQLiteUtils;

import java.util.List;

import adlr.ltmit.dao.DatabaseDao;
import adlr.ltmit.dao.StatisticsDao;
import adlr.ltmit.entities.Database;
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


}
