package adlr.ltmit.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import adlr.ltmit.bl.Calculator;
import adlr.ltmit.dao.DatabaseDao;
import adlr.ltmit.dao.MonthStatisticsDao;
import adlr.ltmit.entities.Category;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.MonthStatistics;
import adlr.ltmit.entities.Word;

/**
 * Created by Agata on 2015-03-02.
 */

public class RepeatingController {

    public void editWordIsRemembered(Word word, int isRemembered){
        word.setIsRemembered(isRemembered);
        word.save();
    }

    public void removeWordsRemembered(List<Word> words){
        for (int i = words.size() - 1; i > -1; i--) {
            if (words.get(i).getIsRemembered() == 1)
                words.remove(words.get(i));
        }
    }

    public void zeroAllWords(List<Word> words){
        for (Word word : words) {
            word.setIsRemembered(0);
            word.setIsCritical(0);
            word.setAmount(0);
            word.save();
        }
    }

    public void setNewDate(Database db, double percentage){
        db.setDateToRepeat(Calculator.calculateDate(System.currentTimeMillis(), db.getPriority(), percentage));
        db.save();
    }

    public void setStatistics(String db, double percentage){
        Database database = DatabaseDao.getDatabaseWithSomeName(db);
        int earlierAmount = database.getAmount();
        double earlierPercentage = database.getGeneralStatistics();
        int amount = earlierAmount+1;
        double newPercentage = ((double) earlierAmount * earlierPercentage + percentage) / amount;
        database.setAmount(amount);
        database.setGeneralStatistics(newPercentage);
        database.save();
    }

    public void setMonthStatistics(Database db, double percentage){
        Date today = new Date(System.currentTimeMillis());
        int year = Calculator.getYear(today);
        int month = Calculator.getMonth(today);
        MonthStatistics statistics = MonthStatisticsDao.getStatistics(month, year);
        if(statistics == null || !(statistics.getDb().getName().equals(db.getName())))
        {
            MonthStatistics ms = new MonthStatistics(year, month, db);
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

    public void savingTemporaryData(List<Word> words, String db, String tempDb){
        deleteTemporaryDb(tempDb);

        Category cat = DatabaseDao.getDabatabaseWithSomeName(db).getCategory();
        Database datb = new Database(1,cat,tempDb);
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

    public void deleteTemporaryDb(String tempDb){
        if(DatabaseDao.getDabatabaseWithSomeName(tempDb)!=null) {
            if (!DatabaseDao.getDabatabaseWithSomeName(tempDb).words().isEmpty()) {
                for (Word w : DatabaseDao.getDabatabaseWithSomeName(tempDb).words())
                    w.delete();
            }
            DatabaseDao.getDabatabaseWithSomeName(tempDb).delete();
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
