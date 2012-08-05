package genidea.sa;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 6, 2010
 * Time: 8:47:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockAnalysis {

    int beginDate = 0;
    int endDate = 0;
    int currentDate = 0;
    FiltersBuilder buyFilter = new FiltersBuilder();
    FiltersBuilder sellFilter = new FiltersBuilder();
    ParametersFilter parametersFilter;


    StockSearch stockSearch = null;
    StockList stockList = null;
    int winner, loser;

    boolean isBuying = false;

    float capital = 100;


    boolean log = true;
    StockList list;
    private String stockName;
    private double globalPerformance;

    StockTrendGen stockTrendGen;
    StockTrendEMA stockTrendEMA;
    ArrayList<StockTrendGen> trendGens = new ArrayList<StockTrendGen>();

    public int getNextDay(int date, int amount){
        int result = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cd = new GregorianCalendar();
        try{
             cd.setTime(df.parse(String.valueOf(date)));
           }
           catch(Exception e){}
           cd.add(Calendar.DATE, amount);
           result = Integer.parseInt(df.format(cd.getTime()));
        return result;
    }


    public BackTestPerformance analyzeData()
    {
        stockList = stockSearch.getStocks();

        sellFilter.setSell(true);
        isBuying = false;
        StockOperations stockOperations = new StockOperations();

        winner = 0;
        loser = 0;
        

        capital = 100;
        BackTestPerformance btp = new BackTestPerformance();

        Calendar cd = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        // for each date
        // check if buy
        // check if sold
        int beginDate = parametersFilter.getStartDate();
        int endDate = parametersFilter.getEndDate();


        StockDay firstDay = stockList.getDay(beginDate);
        while (firstDay == null){
          firstDay = stockList.getDay(getNextDay(beginDate, 1));
          beginDate += 1;
        }
        StockDay lastDay = stockList.getDay(endDate);
        while (lastDay == null){
          lastDay = stockList.getDay(getNextDay(endDate, 1));
            endDate -= 1;
        }

         // stockTrendEMA.setIndexPerformance((stockTrendEMA.getCapital()/firstDay.getClose())*lastDay.getClose());
        currentDate = beginDate;

        StockDay sd = firstDay;

        currentDate = getNextDay(currentDate, 1);
        int tempDate = currentDate;

        while (currentDate < endDate) {
          StockDay actualSd = stockList.getDay(currentDate);
          if (actualSd == null)
          {
            cd.add(Calendar.DATE, 1);
            currentDate = Integer.parseInt(df.format(cd.getTime()));
            continue;
           }
            //           stockTrendEMA.calculate(actualSd, sd, mp, stockOperations);

           if(!isBuying){
           //if (filterAnalyzer!=null){
           //if (filterAnalyzer.isBuy(currentDate))

           if (buyFilter.isAction(currentDate))
           {
              Operation lastOperation = new Operation();
              lastOperation.setCapitalBegin(capital);

              lastOperation.setBuyDate(actualSd.getDateNumeric());
              lastOperation.setBuyPrice(actualSd.getClose());
              stockOperations.addOperation(lastOperation);
              //lastOperation.setSoldeDays(trendDays);
              isBuying = true;
        }

           }

            if (isBuying)
            if (sellFilter.isAction(currentDate)){

                isBuying = false;

                Operation lastOperation = stockOperations.getLastOperation();
                lastOperation.setSellDate(actualSd.getDateNumeric());
               // sell price
               lastOperation.setSellPrice((actualSd.getClose()));
               if (lastOperation.getWinner())
                           winner += 1;
                       else loser += 1;
                       capital = lastOperation.getCapitalEnd();
                       if (true){
                           System.out.println ("Operation:");
                           System.out.println ("Buy: " + lastOperation.getBuyDate() + ", " + lastOperation.getBuyPrice() );
                           System.out.println ("Sell: " + lastOperation.getSellDate() + ", " + lastOperation.getSellPrice() );
                           System.out.println(lastOperation.getCapitalEnd());
                       }
            }

             try{
                 cd.setTime(df.parse(String.valueOf(currentDate)));
           }catch(Exception e){}
           cd.add(Calendar.DATE, 1);
           currentDate = Integer.parseInt(df.format(cd.getTime()));
            }
        if (isBuying){

            Operation lastOperation = stockOperations.getLastOperation();
            lastOperation.setSellDate(lastDay.getDateNumeric());
           // sell price
           lastOperation.setSellPrice((lastDay.getClose()));
           if (lastOperation.getWinner())
                       winner += 1;
                   else loser += 1;
                   capital = lastOperation.getCapitalEnd();
                   if (true){
                       System.out.println ("Operation still opened:");
                       System.out.println ("Buy: " + lastOperation.getBuyDate() + ", " + lastOperation.getBuyPrice() );
                       System.out.println ("Sell: " + lastOperation.getSellDate() + ", " + lastOperation.getSellPrice() );
                       System.out.println(lastOperation.getCapitalEnd());
             isBuying = false;
                   }



        }
           //  publishResultEMA(stockTrendEMA);
           //   Chart chart = new Chart();
           //   chart.setStockList(list);
           //   chart.setTestData(stockTrendEMA.getEmaRecord());
             // return chart.getChartPanel();
              //           chart.showChart();
        //
        if (stockOperations.getOperations().size() > 0)
        btp.setCapitalEnd(Math.round(stockOperations.getLastOperation().getCapitalEnd()));
                btp.setWin(winner);
                btp.setLoss(loser);


        return btp;
    }


    public void analyze(){
        // for each date
            // check if buy
            // check if sold


        MomentParameter mp;
        Calendar cd = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        // get all data



        StockDay firstDay = list.getList().get(Integer.valueOf(beginDate));
        while (firstDay == null){

            firstDay = list.getList().get(getNextDay(beginDate, 1));
            beginDate += 1;
        }
        StockDay lastDay = list.getList().get(Integer.valueOf(endDate));
        while (lastDay == null){

                   lastDay = list.getList().get(getNextDay(endDate, 1));
                   endDate -= 1;
               }

        //
        for (int percBuy = 2 ; percBuy <= 9 ; percBuy ++)
                 for (int percSell = 2 ; percSell < 9 ; percSell++){

            mp = new MomentParameter();
            mp.setCumulatedPercentUpToBuy(percBuy);
            mp.setCumulatedPercentDownToSell(percSell);

            StockOperations stockOperations = new StockOperations();
            stockTrendGen = new StockTrendGen();
            stockTrendGen.setCapital(10000);

            stockTrendGen.setIndexPerformance((stockTrendGen.getCapital()/firstDay.getClose())*lastDay.getClose());

            System.out.println("Index performance: " + stockTrendGen.getIndexPerformance());
            currentDate = beginDate;

            StockDay sd = null;
            int tempDate = currentDate;
              sd = list.getList().get(currentDate);

           try{
             cd.setTime(df.parse(String.valueOf(tempDate)));
           }
           catch(Exception e){}
            if (sd == null){
           while (sd == null){
             cd.add(Calendar.DATE, +1);
             tempDate = Integer.parseInt(df.format(cd.getTime()));
             sd = list.getList().get(tempDate);
            }}
            // find next day
               // StockDayMoment sdmPrevious = new StockDayMoment();


            mp.setCumulatedPercentDownToSell(percSell);
            mp.setCumulatedPercentUpToBuy(percBuy);
            mp.daysUpToBuy=0;

            while (currentDate < endDate) {

                StockDay actualSd = list.getList().get(currentDate);
                while (actualSd == null || actualSd == sd)
                {
                    cd.add(Calendar.DATE, 1);
                    currentDate = Integer.parseInt(df.format(cd.getTime()));
                     actualSd = list.getList().get(currentDate);

                }
                    //StockDayMoment sdmActual = new StockDayMoment();
                  stockTrendGen.calculate(actualSd, sd, mp, stockOperations);
                 sd = actualSd;
               try{
                cd.setTime(df.parse(String.valueOf(currentDate)));
               }catch(Exception e){}
                   cd.add(Calendar.DATE, 1);
                currentDate = Integer.parseInt(df.format(cd.getTime()));

              }
        if (log)
            publishResult(stockTrendGen);
         trendGens.add(stockTrendGen);

        }
             ReportDataSource rds = new ReportDataSource();
            OperationsSubDataSource rdsDetail = new OperationsSubDataSource();
            rdsDetail.setOperations(stockTrendGen.getOperations());
            rds.setStockTrend(trendGens);
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("beginDate", beginDate);
            parameters.put("endDate", endDate);
            parameters.put("stockName", stockName);

           // AnalysisReporting ar = new AnalysisReporting(parameters);
           // ar.runReport(rds,rdsDetail);
                Chart chart = new Chart();
                chart.setStockList(list);
                chart.setTestData(stockTrendGen.getListAlfa());
                      chart.showChart(null);


     }

    private void publishResult(StockTrendGen stockTrendGen){
      StringBuilder sb = new StringBuilder();
        sb.append("------");
        sb.append("\n");
        sb.append("Strategy: buy -> ").append(stockTrendGen.getMomentParameter().getCumulatedPercentUpToBuy());
        globalPerformance += stockTrendGen.getCloseCapital();
        sb.append(" Sell: ").append(stockTrendGen.getMomentParameter().getCumulatedPercentDownToSell());
        sb.append("Initial capital: ").append(stockTrendGen.getInitialCapital());
        sb.append("\n");
        sb.append("Final capital: ").append(stockTrendGen.getCloseCapital());
        sb.append("\n");
        System.out.println(sb.toString());
        StockOperations operations = stockTrendGen.getOperations();
        if (true){
        for (int i= 0; i < operations.getOperations().size(); i++){
            Operation op = operations.getOperations().get(i);
            if (op.isOpen())
                System.out.println("Operation: buy ("+ op.getBuyDate()+")" + op.getBuyPrice());
            else
            System.out.println("Operation: buy ("+ op.getBuyDate()+")" + op.getBuyPrice() + " : sell ("+op.getSellDate()+")" + op.getSellPrice() + ", end capital : " + op.getCapitalEnd()+ " solde trend : " + op.getSoldeDays());
        }
            System.out.println("Global performance: " + globalPerformance);
            System.out.println("percent to sell:" + stockTrendGen.getCumulatedPercentComparedClose());
        }
    }

 
    public void setBeginDate(int beginDate) {
           this.beginDate = beginDate;
       }

    public void setEndDate(int endDate) {
           this.endDate = endDate;
       }

    public void setList(StockList list) {
        this.list = list;
    }

    public void setStockName(String stockName){
       this.stockName = stockName;
    };

    public FiltersBuilder getBuyFilter() {
        return buyFilter;
    }

    public void setBuyFilter(FiltersBuilder buyFilter) {
        this.buyFilter = buyFilter;
    }

    public FiltersBuilder getSellFilter() {
        return sellFilter;
    }

    public void setSellFilter(FiltersBuilder sellFilter) {
        this.sellFilter = sellFilter;
    }

    public ParametersFilter getParametersFilter() {
        return parametersFilter;
    }

    public void setParametersFilter(ParametersFilter parametersFilter) {
        this.parametersFilter = parametersFilter;
    }

   
    public void setStockSearch(StockSearch stockSearch) {
        this.stockSearch = stockSearch;
    }
}
