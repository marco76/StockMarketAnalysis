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
public class StockTrendGen extends StockTrend{
    
    private int direction = 0;
    private float trendMin = 0;
    private int positiveDays = 0;
    private int negativeDays = 0;



    private boolean switchDirection;
    private float percentComparedTrendMin = 0;
    private int winner, loser = 0;
    private boolean log = false;
    private int trendDirection = 0;
    private float capital, initialCapital;
    private int threeMovement = 0;
    private MomentParameter momentParameter = null;
    private List<SimpleData> listAlfa = new ArrayList<SimpleData>();

    private float cumulatedPercentComparedClose = 0;
    LinkedList <Integer> last3 = new LinkedList<Integer>();
    private float percentComparedCloseBefore = 0;
    private int trendDays = 0;
    LinkedList <Integer> trend = new LinkedList<Integer>();

    private int trendLimit = 49;

    public void calculate (StockDay actualStockDay, StockDay previousStockDay, MomentParameter mp, StockOperations operations){
        //find movement
        this.actualStock = actualStockDay;
        this.previousStock = previousStockDay;
        this.momentParameter = mp;
        this.operations = operations;
        switchDirection = false;
        //lastOperation.setCapitalEnd(lastOperation.getStockBuy()*actualStock.getClose());
        // trend direction


        // going UP
        if (actualStockDay.getClose() >= previousStockDay.getClose()){
            trend.add(1);
                      trendDays = calculateTrend();


            last3.add(1);
            if (last3.size()>3)
                    last3.remove(0);

            // if before it was going down
         if (direction <= 0)
         {
            // direction changed UP
            direction = 1;
            switchDirection = true;
            trendMin = previousStock.getMin();
         }
             // if is not going up it goes down
             else direction = direction + 1;

         }

          // going DOWN
          // today close value is less than previous close value
        if (actualStockDay.getClose() < previousStockDay.getClose())
      {
          trend.add(-1);
                    trendDays = calculateTrend();

          // difference close value
         percentComparedCloseBefore  = Math.abs(calculateDeltaComparedCloseBefore(actualStock.getClose(), previousStock.getClose()));




          last3.add(-1);
            if (last3.size()>3)
            last3.remove(0);

          // direction changed DOWN
          if (direction > 0)
          {
             direction = -1;
             switchDirection = true;
          }
          else
          direction = direction - 1;
      }
        // threeMovement = calculateLast3();

        // Trend
        /*  if (direction >= 1){
            // if goes up the min is the same
            // trendMin = Math.min(actualStock.getMin(), trendMin);
            // trendMax = Math.min(actualStock.getMax(), trendMax);
            if (actualStock.getMin() > trendMin){
                // the trend changed direction
                trendDirection = 1;
            }
        }
        if (direction < 0){
            // if goes down, check if the min il lower than the reference min
            // if it's the case trend switch direction else set new min
            if (actualStock.getMin() < trendMin){
                // the trend changed direction
                trendDirection = -1;
          //      trendMin = previousStock.getMin();
            }
          //  else
            // set a new pivot trend reference
           //     trendMin = previousStock.getMin();
        }

        */
        if (switchDirection)
            switchDirection();
        else
        continueDirection();



        if (trendMin == 0)
          trendMin = previousStock.getMin();

        percentComparedTrendMin = ((actualStock.getClose() - trendMin)/trendMin)*100;
        listAlfa.add(new SimpleData (actualStock.getDateNumeric(), (actualStock.getClose() - (actualStock.getClose () * (double)(percentComparedCloseBefore)/100))));

        if (evaluateIfBuy(mp)){
             Operation lastOperation = new Operation();
            lastOperation.setCapitalBegin(capital);
            lastOperation.setBuyDate(actualStockDay.getDateNumeric());
            lastOperation.setBuyPrice(actualStockDay.getClose());
            operations.addOperation(lastOperation);
            lastOperation.setSoldeDays(trendDays);
        }

        if (evaluateIfSell(mp)){
            Operation lastOperation = operations.getLastOperation();
            lastOperation.setSellDate(actualStockDay.getDateNumeric());

            // sell price
            lastOperation.setSellPrice((actualStockDay.getMax() + actualStockDay.getMin())/2);
            if (lastOperation.getWinner())
                winner += 1;
            else loser += 1;
            capital = lastOperation.getCapitalEnd();
            if (true)
                logOperation(lastOperation);
        }
        if(log)
          logTrend();

    }
    protected void logTrend(){
     StringBuilder sb = new StringBuilder();
        sb.append("Date: " + actualStock.getDateNumeric() + ", close :" + actualStock.getClose());
        sb.append("direction:" + direction + ", trend direction " + trendDirection);
        sb.append("3d: "+ threeMovement);

        sb.append("trend min:" + trendMin + ", percent " + percentComparedTrendMin);
       System.out.println(sb.toString());
    };
    private float calculatePercentCloseToClose(float actual, float previous){
            float difference = actual-previous;
            int sign = difference > 0 ? 1 : -1;
            float result = sign * (difference/previous) * 100;
        return result;
    }

    private float calculateDeltaComparedCloseBefore (float actual, float previous){
        return actual - previous;
    }

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

    private void switchDirection(){
        switchDirection = true;
          trendMin = previousStock.getMin();
         ;
        cumulatedPercentComparedClose = Math.abs(percentComparedCloseBefore);
        if (direction > 0){
          trendMin = previousStock.getMin();
            percentComparedTrendMin = ((actualStock.getClose() - trendMin)/trendMin)*100;
                   
        }
        else trendMin = 0;

    }

    private boolean evaluateIfBuy(MomentParameter mp){
        boolean result = false;
        // open buy operation

        if (operations.isLastOperationOpen())
        return result;

        // if the direction is down don't buy
        if (direction < 0)
        return result;

        // if
       // if (threeMovement < 0)
       // return result;


        // if the percent parameter of growing is less than trend
        if (trendLimit != 0){
            if (trendDays < 0 )
        return result;}
        ;
        if (mp.cumulatedPercentUpToBuy < percentComparedTrendMin){
          result = true;

        // check if evaluate the continus number
        if (mp.daysUpToBuy != 0){
          if (!(mp.daysUpToBuy < direction))
            result = false;

          }
          // System.out.println("BUY");
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

       // if (trendDirection > 0 )
        //        return result;
       // if (threeMovement > 0)
       //         return result;

        // if the percent parameter of lowering is bigger than trend
       // percentComparedTrendMax = ((trendMax - actualStock.getClose())/actualStock.getClose())*100;

           // if (mp.cumulatedPercentDownToSell < percentComparedTrendMin){
        // DONT CHANGE!!!
       // System.out.println("CPCC: " + cumulatedPercentComparedClose);
       // System.out.println(actualStock.getClose()-(actualStock.getClose()*(mp.cumulatedPercentDownToSell-cumulatedPercentComparedClose)/100));
        if (mp.cumulatedPercentDownToSell < cumulatedPercentComparedClose){
        // DONT CHANGE!!!
              result = true;

        if (mp.daysDownToSell != 0){
              if (!(mp.daysDownToSell < (direction*-1)))
                  result = false;

          }
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

       public void setMomentParameter(MomentParameter momentParameter) {
           this.momentParameter = momentParameter;
       }

    public float getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(float initialCapital) {
        this.initialCapital = initialCapital;
    }
    private int calculateLast3(){
        int result = 0;
        for (Integer value : last3)
        {
            result = result + value;
        }
        return result;
    }
    private void continueDirection(){
        cumulatedPercentComparedClose = cumulatedPercentComparedClose + percentComparedCloseBefore;
    }

    public int getWinner() {
        return winner;
    }

    public int getLoser() {
        return loser;
    }

    public int getPositiveDays() {
        return positiveDays;
    }

    public int getNegativeDays() {
        return negativeDays;
    }

     private int calculateTrend(){
        int result = 0;
          if (trend.size() > trendLimit)
          trend.remove(0);
        for (int i = 0 ; i< trend.size(); i++){
            result += trend.get(i);
        }
        return result;
    }
    public float getCumulatedPercentComparedClose() {
           return cumulatedPercentComparedClose;
       }

    public List<SimpleData> getListAlfa() {
        return listAlfa;
    }
}
