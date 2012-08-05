package genidea.sa.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 14, 2010
 * Time: 2:54:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackTestResultKey implements Serializable{
    private Float buyProperty;
    private Float sellProperty;
    private String stockName;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Float getBuyProperty() {
        return buyProperty;
    }

    public void setBuyProperty(Float buyProperty) {
        this.buyProperty = buyProperty;
    }

    public Float getSellProperty() {
        return sellProperty;
    }

    public void setSellProperty(Float sellProperty) {
        this.sellProperty = sellProperty;
    }
}
