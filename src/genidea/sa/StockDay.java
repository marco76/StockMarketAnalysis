package genidea.sa;

import genidea.sa.model.StockDetails;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 6, 2010
 * Time: 7:43:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockDay {
    private int id;
    private String stockName;
    private int dateNumeric;
    private float open;
    private float max;
    private float min;
    private float close;
    private int volume;
    private StockDetails stockDetail;


    public int getStockNameID() {
        return stockNameID;
    }

    public void setStockNameID(int stockNameID) {
        this.stockNameID = stockNameID;
    }

    private int stockNameID;


    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getDateNumeric() {
        return dateNumeric;
    }

    
    public void setDateNumeric(int date) {
        this.dateNumeric = date;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public StockDetails getStockDetail() {
          return stockDetail;
      }

      public void setStockDetail(StockDetails stockDetail) {
          this.stockDetail = stockDetail;
      }
    
}
