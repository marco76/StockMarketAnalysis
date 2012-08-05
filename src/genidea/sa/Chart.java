package genidea.sa;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.*;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.jfree.chart.ChartFactory.createScatterPlot;


/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 12, 2010
 * Time: 6:48:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Chart extends JFrame{

    StockList stockList = null;
    List<SimpleData> testData = null;
    ChartPanel chartPanel = null;

    private Day getDay(int yyyyMMdd){
      Day chartDay = new Day();
        String date = String.valueOf(yyyyMMdd);
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day =  Integer.parseInt(date.substring(6,8));
        chartDay = new Day(day,month,year);
        return chartDay;

    }

    public ChartPanel getChartPanel(HashMap<Integer, Float> buyData) {
        chartPanel = new ChartPanel(createChart(buyData));
        return chartPanel;
    }

    public ChartPanel getBubbleChartPanel(List<BackTestPerformance> backTestList){
        return new ChartPanel(createBackTest(backTestList));
    }

    public JFreeChart createBackTest(List<BackTestPerformance> list){
        BackTestPerformance btp;

        XYSeries superGood = new XYSeries("> 200%");
        XYSeries veryGood = new XYSeries("> 150%");
         XYSeries good = new XYSeries("> 120%");
        XYSeries minimum = new XYSeries("> 100%");

        Iterator iterator = list.iterator();
              while (iterator.hasNext()){
                  btp = (BackTestPerformance)iterator.next();
                  if(btp.capitalEnd >= 200)
                    superGood.add(btp.getBuyProperty(), btp.getSellProperty());
                  else
                  if (btp.capitalEnd > 150)
                  veryGood.add(btp.getBuyProperty(), btp.getSellProperty());
                  else
                  if (btp.capitalEnd > 120)
                      good.add(btp.getBuyProperty(), btp.getSellProperty());
                  else
                                   if (btp.capitalEnd > 100)
                                       minimum.add(btp.getBuyProperty(), btp.getSellProperty());

              }

                XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(minimum);

                dataset.addSeries(good);
                 dataset.addSeries(veryGood);
                 dataset.addSeries(superGood);
                 final JFreeChart chart = ChartFactory.createScatterPlot(null, "X", "y", dataset, PlotOrientation.HORIZONTAL, true, true, true);

//        chart.setLegend(null);

                // force aliasing of the rendered content..
        XYPlot plot = chart.getXYPlot();

       XYDotRenderer dot = new XYDotRenderer();
        dot.setDotHeight(5);
        dot.setDotWidth(5);

               plot.setRenderer(dot);
               NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
               domainAxis.setAutoRangeIncludesZero(false);

           return chart;
    }

    public JFreeChart createBubbleChart(List<BackTestPerformance> backTestList){
         NormalizedMatrixSeries series;
         series = createBubbleSeries(backTestList);
         MatrixSeriesCollection dataset = new MatrixSeriesCollection(series);
         final JFreeChart chart = ChartFactory.createBubbleChart(
            "Results", "X", "Y", dataset,
            PlotOrientation.VERTICAL,
            true,
            true, false);

        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0,
                1000, Color.blue));

        final XYPlot plot = chart.getXYPlot();
        plot.setForegroundAlpha(0.5f);

        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setLowerBound(-0.5);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        // rangeAxis.setInverted(true);  // uncoment to reproduce a bug in jFreeChart
        rangeAxis.setLowerBound(-0.5);


        return chart;
    }

    private JFreeChart createChart(HashMap<Integer, Float> buyData){
        TimeSeries pop = new TimeSeries("data");
        TimeSeries buy;
           buy = new TimeSeries("buy");

        TimeSeries popTest = new TimeSeries("test");
        StockDay sd = null;
        if (stockList != null){
          Collection<StockDay> c = stockList.getList().values();
          Iterator iterator = c.iterator();
          while (iterator.hasNext()){
           sd = (StockDay) iterator.next();
            pop.add(getDay(sd.getDateNumeric()),sd.getClose());
        }
        }    
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(pop);


        if (testData != null){
        SimpleData sda = null;
        Iterator ite =testData.iterator();
        while (ite.hasNext()){
           sda = (SimpleData) ite.next();
            popTest.add(getDay(sda.getDateNumeric()),sda.getValue());
            if (buyData != null)
            {
                buy.add(getDay(sda.getDateNumeric()), buyData.get(getDay(sda.getDateNumeric())));
            }
        }
            //dataset = new TimeSeriesCollection();
            dataset.addSeries(popTest);
        }


        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "XChartY Chart",
            "x-axis",
            "y-axis",
            dataset,
            true,
            true,
            false
    );
        return chart;
    }

    public static void main(String args[]){
      // Chart chart = new Chart();
     
    }

    public JFreeChart getChart (HashMap<Integer, Float> buyData){
        return createChart( buyData);
    }



    public void showChart(HashMap<Integer, Float> buyData){


        ChartPanel chartPanel = new ChartPanel(null);
               // default size
               chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
               // add it to our application
               setContentPane(chartPanel);
        setSize(new java.awt.Dimension(500, 270));
      setVisible(true);
    }
    public void setStockList(StockList stockList) {
           this.stockList = stockList;
       }

    public void setTestData(List<SimpleData> emaRecord) {
        this.testData = emaRecord;
    }


     private NormalizedMatrixSeries createBubbleSeries(List <BackTestPerformance> list) {
        final NormalizedMatrixSeries newSeries =
            new NormalizedMatrixSeries("Sample Grid 1", 100, 270);
          BackTestPerformance btp = null;
        // seed a few random bubbles
        Iterator iterator = list.iterator();
         while (iterator.hasNext()){
             btp = (BackTestPerformance)iterator.next();
            final int i = (int) btp.getBuyProperty();
            final int j = (int) btp.getSellProperty();

            final float mij = btp.getCapitalEnd();
            
             newSeries.update(i, j, mij);
        }

        newSeries.setScaleFactor(2000d);

        return newSeries;
    }
}
