package genidea.sa.algo;

import genidea.sa.Operation;
import genidea.sa.StockSearch;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 14, 2010
 * Time: 11:35:02 AM
 *
 * To change this template use File | Settings | File Templates.
 */
public class PercentFromTopAnalyzer extends Analyzer{


    public PercentFromTopAnalyzer(StockSearch stockSearch) {
        super(stockSearch);
    }

    @Override
    public void prepareData(StockSearch stockSearch) {
        if (list == null)
              list = stocks.getStocksFromToDate(stockSearch.getBeginDate(), stockSearch.getEndDate(), stockSearch.getStockName());

    }

    @Override
    public Operation processDay(int dayNumeric) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSell(Integer date) {
        if (operations.getLastOperation().isOpen()){
        

         float maxPrice = stocks.getAbsoluteMaxFromTodate(operations.getLastOperation().getBuyDate(), date, stockSearch.getStockName());
         if (list.getDay(date).getClose() < (maxPrice/100*(100 - (int) charger.getSellParameter())))
             return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isBuy(Integer date) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

}