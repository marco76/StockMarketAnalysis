package genidea.sa.algo;

import dao.StockDayRepository;
import genidea.sa.MovingAverage;
import genidea.sa.Operation;
import genidea.sa.StockSearch;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 7, 2010
 * Time: 11:59:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmaCrossoverAnalyzer extends Analyzer{

    HashMap<Integer, Float> dailyEmaFast;
    HashMap<Integer, Float> dailyEmaSlow;

    StockDayRepository sdRepository;

    public EmaCrossoverAnalyzer(StockSearch stockSearch) {
        super(stockSearch);
    }


    @Override
    public void prepareData(StockSearch stockSearch) {
     if (list == null)
        list = stocks.getStocksFromToDate(stockSearch.getBeginDate(), stockSearch.getEndDate(), stockSearch.getStockName());
        dailyEmaFast = MovingAverage.emaList(list, (int) charger.getParameter());
        dailyEmaSlow = MovingAverage.emaList(list, Math.round(charger.getExtraParameter("EmaSlow")));
  }

    @Override
    public Operation processDay(int dayNumeric) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setSdRepository(StockDayRepository sdRepository) {
        this.sdRepository = sdRepository;
    }

    public boolean isBuy(Integer date){
        if (dailyEmaFast.get(date)!=null){
        if (dailyEmaFast.get(date) > dailyEmaSlow.get(date))
            return true;
        }
         return false;
    }

    public boolean isSell(Integer date){
        if (dailyEmaFast.get(date)!=null)        
            if (dailyEmaFast.get(date) < dailyEmaSlow.get(date))
               return true;
      return false;

    }
}