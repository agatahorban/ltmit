package adlr.ltmit.controllers;

import com.activeandroid.query.Delete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
            statistics.setAmount(amount);
            statistics.setPercentage(newPercentage);
            statistics.save();
        }
    }

    public List<Word> findProperDatabaseWords(String databaseName){
        return DatabaseDao.getDabatabaseWithSomeName(databaseName).words();
    }

    public Database findProperDatabase(String databaseName){
        return DatabaseDao.getDabatabaseWithSomeName(databaseName);
    }
    public List<Word> changingOrder(List<Word> words){
        Word[] sortedWords = new Word[words.size()];
        int k = 0;
        List<Integer> ints = new ArrayList<>();

        Random random = new Random();

        int i = 0;

        int index;
        while(i  < words.size()){
            index = random.nextInt(words.size());
            boolean isInList = false;

            for(Integer integer : ints){
                if(index == integer) {
                    isInList = true;
                    break;
                }
            }

            while(isInList){
                isInList = false;
                index = random.nextInt(words.size());

                for(Integer integer : ints){
                    if(index == integer) {
                        isInList = true;
                        break;
                    }
                }
            }
            sortedWords[index] = words.get(i);
            ints.add(index);
            i++;
        }

        return new ArrayList<>(Arrays.asList(sortedWords));
    }

    public void savingTemporaryData(List<Word> words, String db){
        deleteTemporaryDb();

        Category cat = DatabaseDao.getDabatabaseWithSomeName(db).getCategory();
        Database datb = new Database(1,cat,"temporary");
        datb.save();
        for(Word w : words){
            Word tempW = new Word();
            tempW.setAmount(w.getAmount());
            tempW.setIsCritical(w.getIsCritical());
            tempW.setIsRemembered(w.getIsRemembered());
            tempW.setDatabase(datb);
            tempW.setMeaning(w.getMeaning());
            tempW.setTranslation(w.getTranslation());
            tempW.save();
        }
    }

    public void deleteTemporaryDb(){
        if(DatabaseDao.getDabatabaseWithSomeName("temporary")!=null) {
            if (!DatabaseDao.getDabatabaseWithSomeName("temporary").words().isEmpty()) {
                for (Word w : DatabaseDao.getDabatabaseWithSomeName("temporary").words())
                    w.delete();
            }
            DatabaseDao.getDabatabaseWithSomeName("temporary").delete();
        }
    }


    public double countPercentage(List<Word> words) {
        int remem = 0;
        for(Word w : words){
            if(w.getIsRemembered()==1){
               remem++;
            }
        }
        return (double) remem / words.size() * 100;
    }

}
