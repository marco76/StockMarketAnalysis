package genidea.sa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 8, 2010
 * Time: 10:49:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockTrendEMA extends StockTrend {

    private int direction = 0;
    private float trendMin = 0;

    private boolean switchDirection;
    private float percentComparedTrendMin = 0;
    private boolean log = false;
    private float capital, initialCapital;
    private MomentParameter momentParameter = null;
    LinkedList <Double> stockValues = new LinkedList<Double>();
    LinkedList <Integer> trend = new LinkedList<Integer>();
    List<SimpleData> emaRecord= new ArrayList<SimpleData>();
     private double currentEma = 0.0;
    private int positiveDays = 0;
    private int negativeDays = 0;
    private int trendDays = 0;
    private int minTrendPositiveDays = 0;

    private int emaDays = 50;

    public void calculate (StockDay actualStockDay, StockDay previousStockDay, MomentParameter mp, StockOperations operations){
        //find movement
        this.actualStock = actualStockDay;
        this.previousStock = previousStockDay;
        this.momentParameter = mp;
        this.operations = operations;
        switchDirection = false;
        //lastOperation.setCapitalEnd(lastOperation.getStockBuy()*actualStock.getClose());
        // trend direction
        stockValues.add(new Double(actualStockDay.getClose()));

        if (stockValues.size()>emaDays)
           stockValues.remove(0);
        if (stockValues.size() >(emaDays-1))
            try {
                currentEma = MovingAverage.ema(stockValues, emaDays , 0.00001);
                emaRecord.add(new SimpleData(actualStock.getDateNumeric(), currentEma));
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        else
          return;

        // going UP
        if (actualStockDay.getClose() >= previousStockDay.getClose()){
            trend.add(1);
            trendDays = calculateTrend();
            positiveDays += 1;

            if (evaluateIfBuy(mp)){
                        Operation lastOperation = new Operation();
                        lastOperation.setCapitalBegin(capital);
                        lastOperation.setBuyDate(actualStockDay.getDateNumeric());
                        lastOperation.setBuyPrice(actualStockDay.getClose());
                        operations.addOperation(lastOperation);
                        lastOperation.setSoldeDays(trendDays);
                if (log)
                            System.out.println("BUY");

        }
        }
        if (actualStockDay.getClose() < previousStockDay.getClose())
        {
            trend.add(-1);
            trendDays = calculateTrend();

            negativeDays += 1;
            if (evaluateIfSell(mp)){
                        Operation lastOperation = operations.getLastOperation();
                        lastOperation.setSellDate(actualStockDay.getDateNumeric());
                        // lastOperation.setSoldeDays(positiveDays-negativeDays);
                        // sell price
                        lastOperation.setSellPrice((actualStockDay.getMax() + actualStockDay.getMin())/2);
                        if (lastOperation.getWinner())
                            winner += 1;
                        else loser += 1;
                        capital = lastOperation.getCapitalEnd();
                        if (true)
                            logOperation(lastOperation);
                    }
        }

    }
    protected void logTrend(){
     StringBuilder sb = new StringBuilder();
        sb.append("Date: " + actualStock.getDateNumeric() + ", close :" + actualStock.getClose());
        sb.append("direction:" + direction);


        sb.append("trend min:" + trendMin + ", percent " + percentComparedTrendMin);
       System.out.println(sb.toString());
    };

    public float getCloseCapital(){
        float result = 0;
        if (operations.getOperations().isEmpty()) return capital;
        if (! operations.getLastOperation().isOpen())
            result = capital;
        else {
            float closeCapital = 0;
            Operation lastOperation = operations.getLastOperation();
            closeCapital = actualStock.getClose() * lastOperation.getStockBuy();
            result = closeCapital;
        }
        return result;
    }

    private boolean evaluateIfBuy(MomentParameter mp){
        boolean result = false;
        // open buy operation
        if (trendDays < 0)
            return result;
        if (operations.isLastOperationOpen())
        return result;

        // if the direction is down don't buy
        //if (direction < 0)
        //return result;

        // if
       // if (threeMovement < 0)
       // return result;

        //if (trendDays < 0)
        // if the percent parameter of growing is less than trend
        if (currentEma < actualStock.getClose()){
          result = true;
       }
        return result;
    }
    private boolean evaluateIfSell(MomentParameter mp){
        boolean result = false;
        // didn't buy yet
        if (!operations.isLastOperationOpen())
            return result;

        // the direction is up, don't sell
        if (direction > 0)
            return result;

        // if the percent parameter of growing is less than trend
               if (currentEma > actualStock.getClose()){
                 result = true;
         }
        return result;
    }

    public float getCapital() {
        return capital;
    }

    public void setCapital(float capital) {
        this.capital = capital;
    }

    public void logOperation(Operation lastOperation){
        if (!lastOperation.isOpen()){
       // System.out.println("Operation completed: buy ("+ lastOperation.getBuyDate()+")" + lastOperation.getBuyPrice() + " : sell ("+lastOperation.getSellDate()+")" + lastOperation.getSellPrice() + ", end capital : " + lastOperation.getCapitalEnd());
    }
    }
    public MomentParameter getMomentParameter() {
           return momentParameter;
       }


    public float getInitialCapital() {
        return initialCapital;
    }


    public int getPositiveDays() {
        return positiveDays;
    }


    public int getNegativeDays() {
        return negativeDays;
    }
    private int calculateTrend(){
        int result = 0;
          if (trend.size() > emaDays)
          trend.remove(0);
        for (int i = 0 ; i< trend.size(); i++){
            result += trend.get(i);
        }
        return result;
    }

    public double getCurrentEma() {
        return currentEma;
    }

    public List<SimpleData> getEmaRecord() {
        return emaRecord;
    }

    public void setEmaDays(int emaDays) {
        this.emaDays = emaDays;
    }

    public int getMinTrendPositiveDays() {
        return minTrendPositiveDays;
    }

    public void setMinTrendPositiveDays(int minTrendPositiveDays) {
        this.minTrendPositiveDays = minTrendPositiveDays;
    }
}