package genidea.sa.model;

import genidea.sa.StockDay;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 19, 2010
 * Time: 7:43:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockDetails {
    private String stockName;
    private String stockCode;
    private String market;
    private Integer lastImportDay;
    private int id;
    private Set<StockDay> stocks =  new HashSet();

    public Set<StockDay> getStocks() {
        return stocks;
    }

    public void setStocks(Set<StockDay> stocks) {
        this.stocks = stocks;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getLastImportDay() {
        return lastImportDay;
    }

    public void setLastImportDay(Integer lastImportDay) {
        this.lastImportDay = lastImportDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
