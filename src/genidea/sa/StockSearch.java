package genidea.sa;

import dao.StockDayRepository;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 8, 2010
 * Time: 12:02:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class StockSearch {
    int beginDate;
    int endDate;
    String stockName;
    StockList stockList;

    public int getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(int beginDate) {
        this.beginDate = beginDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public StockList getStocks(){
        if (stockList == null){
          StockDayRepository stocks;
          stocks = SwingBeanInitalizer.getStockDayRepository();

           stockList = stocks.getStocksFromToDate(beginDate, endDate, stockName);   
        }
        return stockList;
    }
}
