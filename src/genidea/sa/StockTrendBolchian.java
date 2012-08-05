package genidea.sa;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 7, 2010
 * Time: 8:52:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockTrendBolchian  extends StockTrend{
    @Override
    public void calculate(StockDay actualStockDay, StockDay previousStockDay, MomentParameter mp, StockOperations operations) {
       this.actualStock = actualStockDay;
       this.previousStock = previousStockDay;
       this.operations = operations;

    }

    @Override
    protected void logTrend() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
