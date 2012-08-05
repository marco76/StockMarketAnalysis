package genidea.sa.algo;

import genidea.sa.Operation;
import genidea.sa.StockSearch;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 14, 2010
 * Time: 11:35:02 AM
 *
 * To change this template use File | Settings | File Templates.
 */
public class DaysTrendAnalyzer extends Analyzer{
    HashMap<Integer, Float> dayTrend;

    public DaysTrendAnalyzer(StockSearch stockSearch) {
        super(stockSearch);
    }

    @Override
    public void prepareData(StockSearch stockSearch) {
        if (list == null)
              list = stocks.getStocksFromToDate(stockSearch.getBeginDate(), stockSearch.getEndDate(), stockSearch.getStockName());
        dayTrend = DayTrend.dayTrend(list, (int) charger.getParameter());

    }

    @Override
    public Operation processDay(int dayNumeric) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSell(Integer date) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isBuy(Integer date) {
        if (dayTrend.get(date) > (charger.getParameter()/2))
             return true;
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

}