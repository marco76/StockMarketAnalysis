package genidea.sa.algo;

import genidea.sa.Operation;
import genidea.sa.StockSearch;

import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 13, 2010
 * Time: 11:23:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class BolchianAnalyzer extends Analyzer{

    HashMap<Integer, Float> upperBand;
    HashMap<Integer, Float> bottomBand;

    public BolchianAnalyzer(StockSearch stockSearch) {
        super(stockSearch);
    }

    @Override
    public void prepareData(StockSearch stockSearch) {
        if (list == null)
          list = stocks.getStocksFromToDate(stockSearch.getBeginDate(), stockSearch.getEndDate(), stockSearch.getStockName());
        upperBand = Bolchian.upperBand(list, (int) charger.getParameter(), true);
        bottomBand = Bolchian.upperBand(list, (int) charger.getParameter(), false);
    }

    @Override
    public Operation processDay(int dayNumeric) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSell(Integer date) {
   if (upperBand.get(date)!=null)
      if (list.getDay(date).getClose() <= bottomBand.get(date))
        return true;
      return false; }

    @Override
    public boolean isBuy(Integer date) {
      if (upperBand.get(date)!=null)
      if (list.getDay(date).getClose() >= upperBand.get(date))
        return true;
      return false;
   }

    public HashMap<Integer, Float> getChart(){
        return upperBand;
    }
}
