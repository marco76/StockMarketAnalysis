package genidea.sa.model;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 14, 2010
 * Time: 2:33:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackTestResult {

     private Float buyParameter;
     private Float sellParameter;
     private Integer win;
     private Integer losses;
     private Float cumulatedResult;
     private Float maxResult;
     private Float minResult;
     private BackTestResultKey btrKEY;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    private String stockName;

    public Float getBuyParameter() {
        return buyParameter;
    }

    public void setBuyParameter(Float buyParameter) {
        this.buyParameter = buyParameter;
    }

    public Float getSellParameter() {
        return sellParameter;
    }

    public void setSellParameter(Float sellParameter) {
        this.sellParameter = sellParameter;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Float getCumulatedResult() {
        return cumulatedResult;
    }

    public void setCumulatedResult(Float cumulatedResult) {
        this.cumulatedResult = cumulatedResult;
    }

    public Float getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Float maxResult) {
        this.maxResult = maxResult;
    }

    public Float getMinResult() {
        return minResult;
    }

    public void setMinResult(Float minResult) {
        this.minResult = minResult;
    }

    public BackTestResultKey getBtrKEY() {
        return btrKEY;
    }

    public void setBtrKEY(BackTestResultKey btrKEY) {
        this.btrKEY = btrKEY;
    }
}
