package genidea.sa;

import dao.BackTestResultRepository;
import dao.StockDayRepository;
import genidea.sa.algo.EmaAnalyzer;

import static genidea.sa.algo.Filters.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 12, 2010
 * Time: 11:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainGuiIJ {
    private JButton button1;
    private JTextField txtStockName;
    private JPanel mainPanel;
    private JPanel pnlResult;
    private JTextField txtDateBegin;
    private JTextField txtDateEnd;
    private JRadioButton EMARadioButton;
    private JRadioButton trendRadioButton;
    private JTabbedPane tabbedPane1;
    private JTextArea taResult;
    private JPanel pnlChart;
    private JPanel pnlClassicalChart;
    private JCheckBox cbAll;

    Integer dateBegin;
    Integer dateEnd;
    String stockName;

    public static void main(String[] args) {
        MainGuiIJ mg = new MainGuiIJ();

    }


    public void setStocks(StockDayRepository stocks) {
        this.stocks = stocks;
    }

    StockDayRepository stocks;
    BackTestResultRepository backTestRepository;
    List<BackTestPerformance> backTestList = null;

    FiltersBuilder buyFilter = new FiltersBuilder();
    FiltersBuilder sellFilter = new FiltersBuilder();

    public MainGuiIJ() {
        JFrame frame = new JFrame("MainGuiIJ");
        frame.setContentPane(mainPanel);
        dateBegin = Integer.parseInt(txtDateBegin.getText());
        dateEnd = Integer.parseInt(txtDateEnd.getText());
        SwingBeanInitalizer.initialize();
        stocks = SwingBeanInitalizer.getStockDayRepository();
        backTestRepository = SwingBeanInitalizer.getBackTestRepository();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 500);

        frame.setVisible(true);
        System.out.println("record in the table:" + stocks.countTableRecords());

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showChart();
            }
        });
    }


    public void showChart() {
        backTestList = new ArrayList<BackTestPerformance>();

        /* database repository */
        backTestRepository.truncateTable();

        StockSearch ss = null;

        StockAnalysis sAnalysis = null;
        sAnalysis = new StockAnalysis();


        /*
        TODO automatically set the offset to load the dates
         */

        dateBegin = Integer.parseInt(txtDateBegin.getText());
        dateEnd = Integer.parseInt(txtDateEnd.getText());

        stockName = txtStockName.getText();

        String[] stockNames = stockName.split(",");


        /* Common parameters */

        ParametersFilter par = new ParametersFilter();
        par.setStartDate(dateBegin);
        par.setEndDate(dateEnd);
        sAnalysis.setParametersFilter(par);

        for (String stockName : stockNames) {


            /**
             * TODO it takes the same data and the date of 20100301
             */

            ss = new StockSearch();
            buyFilter = new FiltersBuilder();
            sellFilter = new FiltersBuilder();

            ss.setStockName(stockName);
            ss.setBeginDate(20040601);
            ss.setEndDate(20100616);

            sAnalysis.setList(null);
            sAnalysis.setStockSearch(ss);
            BackTestPerformance best = null;


            // best = doEma(ss, sAnalysis, par, stockName, best);
           best = doBolchian(ss, sAnalysis, par, stockName, best);


            // start the crossover analysis
            // best = doEmaCrossover(ss, sAnalysis, par, stockName, best);

            // show the best result
            publishResult(best);


        }
        EmaAnalyzer ema = new EmaAnalyzer(ss);
        ema.setSdRepository(stocks);
        //    ema.prepareData(ss);

        Chart bubbleChart = new Chart();
        pnlChart.removeAll();
        pnlChart.add(bubbleChart.getBubbleChartPanel(backTestList));
        Chart chart = new Chart();

        chart.setStockList(stocks.getStocksFromToDate(dateBegin, dateEnd, stockName));

        pnlClassicalChart.removeAll();
        pnlClassicalChart.add(chart.getChartPanel(null));

        // pnlChart.add(chart.getChartPanel());
        pnlChart.invalidate();
    }

    private BackTestPerformance doBolchian(StockSearch ss, StockAnalysis sAnalysis, ParametersFilter par, String stockName, BackTestPerformance best) {
        /*
       BOLCHIAN
        */
        buyFilter.addFilter(BOLCHIAN, ss, par);
        sellFilter.setSell(true);
        sellFilter.addFilter(EMA, ss, par);


        sAnalysis.setBuyFilter(buyFilter);
        sAnalysis.setSellFilter(sellFilter);


        sellFilter.addFilter(PERCENT, ss, par);
        sellFilter.getCharger(PERCENT).setParameter(15);

        sellFilter.addFilter(DAY_TREND, ss, par);
        sellFilter.getCharger(DAY_TREND).setParameter(10);
     
        for (int buy = 20; buy <= 95; buy += 2) {
            for (int sell = 20; sell <= 110; sell += 5) {
                buyFilter.getCharger(BOLCHIAN).setParameter(buy);
                sellFilter.getCharger(EMA).setParameter(sell);

                buyFilter.prepareData();
                sellFilter.prepareData();


                BackTestPerformance btp = sAnalysis.analyzeData();
                btp.setSellProperty(sell);
                btp.setBuyProperty(buy);
                btp.setStockName(stockName);

                if (best == null)
                    best = btp;
                else if (best.capitalEnd < btp.capitalEnd)
                    best = btp;


                backTestRepository.add(btp);
                System.out.println("Test: " + btp.getCapitalEnd() + " - " + btp.getBuyProperty() + " : " + btp.getSellProperty());
            }
        }
        return best;
    }

    private BackTestPerformance doEma(StockSearch ss, StockAnalysis sAnalysis, ParametersFilter par, String stockName, BackTestPerformance best) {
        buyFilter.addFilter(EMA, ss, par);
        sellFilter.setSell(true);
        sellFilter.addFilter(EMA, ss, par);

        sAnalysis.setBuyFilter(buyFilter);
        sAnalysis.setSellFilter(sellFilter);
        for (int buy = 5; buy <= 250; buy += 5) {
            for (int sell = 5; sell <= 250; sell += 5) {
                buyFilter.getCharger(EMA).setParameter(buy);
                sellFilter.getCharger(EMA).setParameter(sell);

                buyFilter.prepareData();
                sellFilter.prepareData();

                BackTestPerformance btp = sAnalysis.analyzeData();
                btp.setSellProperty(sell);
                btp.setBuyProperty(buy);
                btp.setStockName(stockName);

                if (best == null)
                    best = btp;
                else if (best.capitalEnd < btp.capitalEnd)
                    best = btp;


                backTestRepository.add(btp);
                System.out.println("Test: " + btp.getCapitalEnd() + " - " + btp.getBuyProperty() + " : " + btp.getSellProperty());

            }
        }
        return best;
    }

    private void publishResult(BackTestPerformance best) {
        System.out.println("Best Performance");
        System.out.println("buy: " + best.getBuyProperty());
        System.out.println("sell: " + best.getSellProperty());
        System.out.println(best.capitalEnd);
    }

    private BackTestPerformance doEmaCrossover(StockSearch ss, StockAnalysis sAnalysis, ParametersFilter par, String stockName, BackTestPerformance best) {
        buyFilter.addFilter(EMA_CROSSOVER, ss, par);
        sellFilter.setSell(true);
        sellFilter.addFilter(EMA_CROSSOVER, ss, par);

        sAnalysis.setBuyFilter(buyFilter);
        sAnalysis.setSellFilter(sellFilter);


        for (int buy = 5; buy <= 50; buy += 2) {
            for (int sell = 51; sell <= 230; sell += 5) {

                buyFilter.getCharger(EMA_CROSSOVER).setParameter(buy);
                sellFilter.getCharger(EMA_CROSSOVER).setParameter(buy);
                buyFilter.getCharger(EMA_CROSSOVER).addExtraParameter("EmaSlow", new Float(sell));
                sellFilter.getCharger(EMA_CROSSOVER).addExtraParameter("EmaSlow", new Float(sell));
                buyFilter.prepareData();
                sellFilter.prepareData();

                BackTestPerformance btp = sAnalysis.analyzeData();
                btp.setSellProperty(sell);
                btp.setBuyProperty(buy);
                btp.setStockName(stockName);

                if (best == null)
                    best = btp;
                else if (best.capitalEnd < btp.capitalEnd)
                    best = btp;


                backTestRepository.add(btp);


                if (btp.getCapitalEnd() > 100 || btp.getWin() > btp.getLoss()) {
                    backTestList.add(btp);
                }
            }
        }
        return best;
    }
}
