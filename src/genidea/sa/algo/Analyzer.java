package genidea.sa.algo;

import dao.StockDayRepository;
import genidea.sa.*;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 7, 2010
 * Time: 9:48:34 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Analyzer {

    StockList list;
    int beginDate, endDate;
    StockSearch stockSearch;
    AnalyzerCharger charger;
    StockDayRepository stocks;
    StockOperations operations;

    public abstract void prepareData(StockSearch stockSearch);

    public abstract Operation processDay(int dayNumeric);

    public void setList(StockList list) {
        this.list = list;
    }

    protected void getDataFromDatabase(){
    }

    protected Analyzer(StockSearch stockSearch) {
        
        this.stockSearch = stockSearch;
        this.stocks = SwingBeanInitalizer.getStockDayRepository();
    }

    public void setStockSearch(StockSearch stockSearch) {
        this.stockSearch = stockSearch;
    }

    public void setStocks(StockDayRepository stocks) {
        this.stocks = stocks;
    }

    public StockDay getStockDay(Integer date){
        return list.getDay(date);
    }

    public abstract boolean isSell(Integer date);
    public abstract boolean isBuy(Integer date);

    public void setCharger(AnalyzerCharger charger) {
        this.charger = charger;
    }

    public void setOperations(StockOperations operations) {
        this.operations = operations;
    }

    public AnalyzerCharger getCharger(){
        return charger;
    }
}
