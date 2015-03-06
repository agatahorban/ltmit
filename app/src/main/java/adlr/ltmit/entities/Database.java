package adlr.ltmit.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;
import java.util.List;

/**
 * Created by Agata on 2015-02-21.
 */

@Table(name = "DATABS")
public class Database extends Model {

    @Column(name = "DATABASE_NAME", unique = true, onUniqueConflicts = Column.ConflictAction.REPLACE)
    private String name;

    @Column(name = "PRIORITY", notNull = true)
    private int priority;

    @Column(name = "DATE_TO_REPEAT")
    private Date dateToRepeat;

    @Column(name = "CATEGORY")
    private Category category;

//    @Column(name = "STATISTICS")
//    private Statistics statistics;

    @Column(name = "GENERAL_STATISTICS")
    private double generalStatistics;

    @Column(name="AMOUNT_OF_REPETITIONS")
    private int amount;

    public Database(){
        this.dateToRepeat =  new Date(System.currentTimeMillis());
    }

    public Database(int priority, Category category, String name) {
        this.priority = priority;
        this.category = category;
        this.name = name;
        this.dateToRepeat = new Date(System.currentTimeMillis());
        this.amount = 0;
        this.generalStatistics = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDateToRepeat() {
        return dateToRepeat;
    }

    public void setDateToRepeat(Date dateToRepeat) {
        this.dateToRepeat = dateToRepeat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public Database(Statistics statistics) {
//        this.statistics = statistics;
//    }
//
//    public Statistics getStatistics() {
//        return statistics;
//    }
//
//    public void setStatistics(Statistics statistics) {
//        this.statistics = statistics;
//    }

    public List<Word> words() {
        return getMany(Word.class, "DATABS");}


    public List<MonthStatistics> monthStatistics() {
                return getMany(MonthStatistics.class, "DATABS");
    }

    public double getGeneralStatistics() {
        return generalStatistics;
    }

    public void setGeneralStatistics(double generalStatistics) {
        this.generalStatistics = generalStatistics;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
