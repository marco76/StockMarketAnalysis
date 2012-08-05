package genidea.sa;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 13, 2010
 * Time: 6:23:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackTestPerformance {
    int win, loss = 0;
    float capitalBegin, capitalEnd;
    float maxCapital, minCapital;
    String stockName;


    AnalyzerCharger buyAnalyzer;
    AnalyzerCharger sellAnalyzer;

    float buyProperty, sellProperty;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public float getCapitalBegin() {
        return capitalBegin;
    }

    public void setCapitalBegin(float capitalBegin) {
        this.capitalBegin = capitalBegin;
    }

    public float getCapitalEnd() {
        return capitalEnd;
    }

    public void setCapitalEnd(float capitalEnd) {
        this.capitalEnd = capitalEnd;
    }

    public float getMaxCapital() {
        return maxCapital;
    }

    public void setMaxCapital(float maxCapital) {
        this.maxCapital = maxCapital;
    }

    public float getMinCapital() {
        return minCapital;
    }

    public void setMinCapital(float minCapital) {
        this.minCapital = minCapital;
    }

    public AnalyzerCharger getBuyAnalyzer() {
        return buyAnalyzer;
    }

    public void setBuyAnalyzer(AnalyzerCharger buyAnalyzer) {
        this.buyAnalyzer = buyAnalyzer;
    }

    public AnalyzerCharger getSellAnalyzer() {
        return sellAnalyzer;
    }

    public void setSellAnalyzer(AnalyzerCharger sellAnalyzer) {
        this.sellAnalyzer = sellAnalyzer;
    }

    public float getBuyProperty() {
        return buyProperty;
    }

    public void setBuyProperty(float buyProperty) {
        this.buyProperty = buyProperty;
    }

    public float getSellProperty() {
        return sellProperty;
    }

    public void setSellProperty(float sellProperty) {
        this.sellProperty = sellProperty;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
