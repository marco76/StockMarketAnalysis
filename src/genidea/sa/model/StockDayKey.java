package genidea.sa.model;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 14, 2010
 * Time: 11:29:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockDayKey {
    private String stockName;
    private int dateNumeric;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getDateNumeric() {
        return dateNumeric;
    }

    public void setDateNumeric(int dateNumeric) {
        this.dateNumeric = dateNumeric;
    }
}
