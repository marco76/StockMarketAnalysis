package genidea.sa.algo;

import dao.StockDayRepository;
import genidea.sa.*;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 7, 2010
 * Time: 11:59:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmaAnalyzer extends Analyzer{

    HashMap<Integer, Float> dailyEma;
    StockDayRepository sdRepository;

    public EmaAnalyzer(StockSearch stockSearch) {
        super(stockSearch);
    }


    @Override
    public void prepareData(StockSearch stockSearch) {
     if (list == null)
        list = stocks.getStocksFromToDate(stockSearch.getBeginDate(), stockSearch.getEndDate(), stockSearch.getStockName());
        dailyEma = MovingAverage.emaList(list, (int) charger.getParameter());
  }

    @Override
    public Operation processDay(int dayNumeric) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setSdRepository(StockDayRepository sdRepository) {
        this.sdRepository = sdRepository;
    }

    public boolean isBuy(Integer date){
        if (dailyEma.get(date)!=null)
        if (list.getDay(date).getClose() > dailyEma.get(date))
            return true;
        return false;
    }

    public boolean isSell(Integer date){
        if (dailyEma.get(date)!=null)        
      if (list.getDay(date).getClose() < dailyEma.get(date))
        return true;
      return false;

    }
}
