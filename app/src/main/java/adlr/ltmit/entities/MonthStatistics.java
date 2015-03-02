package adlr.ltmit.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Agata on 2015-02-21.
 */

@Table(name = "MONTH_STATISTICS")
public class MonthStatistics extends Model {

    @Column(name = "WHICH_MONTH")
    private int whichMonth;

    @Column(name = "WHICH_YEAR")
    private int whichYear;

    @Column(name= "STATISTICS", notNull = true)
    private Statistics statistics;

    @Column(name = "PERCENTAGE")
    private double percentage;

    @Column(name="AMOUNT_OF_REPETITIONS")
    private int amount;

    public MonthStatistics(){
        super();
    }

    public MonthStatistics(int whichYear, int whichMonth, Statistics statistics) {
        super();
        this.whichYear = whichYear;
        this.whichMonth = whichMonth;
        this.statistics = statistics;
        this.percentage = 0;
    }

    public int getWhichMonth() {
        return whichMonth;
    }

    public void setWhichMonth(int whichMonth) {
        this.whichMonth = whichMonth;
    }

    public int getWhichYear() {
        return whichYear;
    }

    public void setWhichYear(int whichYear) {
        this.whichYear = whichYear;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
