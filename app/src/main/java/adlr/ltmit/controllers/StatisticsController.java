package adlr.ltmit.controllers;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;


import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adlr.ltmit.R;
import adlr.ltmit.bl.Calculator;
import adlr.ltmit.dao.DatabaseDao;
import adlr.ltmit.entities.Database;
import adlr.ltmit.entities.MonthStatistics;

/**
 * Created by Agata on 2015-03-08.
 */

public class StatisticsController {
    public static final int SIZE = 13;
    private static final String COLOR = "#4705F7";
    private static final int LINE_WIDTH = 3;
    private static final int Y_AXIS_MAX = 100;
    private static final int Y_AXIS_MIN = 0;
    private static final int X_AXIS_MAX = 12;
    private static final int X_AXIS_MIN = 0;
    private static final int X_LABELS = 0;
    private static final String PERCENTAGE = "Every month percentage";

    public List<MonthStatistics> listOfMonthStatistics(String db){
        List<MonthStatistics> ms = new ArrayList<>();
        Database dbase = DatabaseDao.getDabatabaseWithSomeName(db);
        if(!(dbase.monthStatistics().isEmpty())){
            ms = dbase.monthStatistics();
        }
        return ms;
    }

    public Map<Integer, Integer> getPercentageForEachStatistics(String db){
        List<MonthStatistics> listM = DatabaseDao.getDabatabaseWithSomeName(db).monthStatistics();
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();

        Database dbase = DatabaseDao.getDabatabaseWithSomeName(db);
        int currentYear = Calculator.getYear(new Date(System.currentTimeMillis()));
        for(int month = 1; month<13; month++){
            for(MonthStatistics ms : listM){
                if(ms.getWhichMonth()==month && ms.getWhichYear()==2015)
                myMap.put(month,(int) Math.round(ms.getPercentage()));
                else
                myMap.put(month,-1);
            }
        }
        return myMap;
    }


    public int[] getPercentageForEachStatisticsList(String db){
        int[] tab  = new int[SIZE];
        List<MonthStatistics> lm  = listOfMonthStatistics(db);

        tab[0] = -1;
        for(int month = 1; month<SIZE; month++){
            for(MonthStatistics mss :  lm){
                if(mss.getWhichMonth()==month && mss.getWhichYear()==2015)
                    tab[month] = (int) Math.round(mss.getPercentage());
                else
                    tab[month] = -1;
            }
        }
        return tab;
    }

    private XYSeries createSeries(String dbName){
        XYSeries series = new XYSeries(PERCENTAGE);
        int w =  DatabaseDao.getDabatabaseWithSomeName(dbName).monthStatistics().get(0).getWhichMonth();
        int tab[] = getPercentageForEachStatisticsList(dbName);

        for (int j = w; j<tab.length; j++) {
            if (tab[j] != -1) {
                series.add(j, tab[j]);
            }
        }
        return series;
    }

    private  XYSeriesRenderer createXYSeriesRenderer(){
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setLineWidth(LINE_WIDTH);
        renderer.setColor(Color.parseColor(COLOR));
        renderer.setPointStyle(PointStyle.CIRCLE);
        return renderer;
    }

    private XYMultipleSeriesRenderer createXYMultipleSeriesRenderer(Context c, XYSeriesRenderer renderer){
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00));
        mRenderer.setPanEnabled(false, false);

        float val = createMetrics(c,TypedValue.COMPLEX_UNIT_SP,10);
        mRenderer.setLabelsTextSize(val);

        mRenderer.setYAxisMax(Y_AXIS_MAX);
        mRenderer.setYAxisMin(Y_AXIS_MIN);
        mRenderer.setXAxisMax(X_AXIS_MAX);
        mRenderer.setXAxisMin(X_AXIS_MIN);

        mRenderer.setLegendTextSize(val);

        float pointSize = createMetrics(c,TypedValue.COMPLEX_UNIT_DIP, 4);
        mRenderer.setPointSize(pointSize);
        mRenderer.setShowGrid(true);

        String[] planets = c.getResources().getStringArray(R.array.months);
        int k = 1;
        mRenderer.setXLabels(X_LABELS);
        for(String p : planets){
            mRenderer.addXTextLabel(k,p);
            k++;
        }
        return mRenderer;
    }

    public GraphicalView makeStatistics(String dbName, Context c){
        XYSeries series = createSeries(dbName);
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);
        XYSeriesRenderer renderer = createXYSeriesRenderer();
        XYMultipleSeriesRenderer mRenderer = createXYMultipleSeriesRenderer(c, renderer);
        return ChartFactory.getLineChartView(c, dataset, mRenderer);
    }


    private float createMetrics(Context c, int unit, int size){
        DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        float val = TypedValue.applyDimension(unit, size, metrics);
        return val;
    }
}
