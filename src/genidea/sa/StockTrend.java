package genidea.sa;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 13, 2010
 * Time: 7:44:24 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class StockTrend {
    protected StockOperations operations;
    protected float indexPerformance;
    protected StockDay actualStock = null;
    protected StockDay previousStock = null;
    protected int winner, loser = 0;

    public abstract void calculate (StockDay actualStockDay, StockDay previousStockDay, MomentParameter mp, StockOperations operations);
    protected abstract void logTrend();


    public StockOperations getOperations() {
           return operations;
       }

    public float getIndexPerformance() {
        return indexPerformance;
    }

    public void setIndexPerformance(float indexPerformance) {
        this.indexPerformance = indexPerformance;
    }


}
