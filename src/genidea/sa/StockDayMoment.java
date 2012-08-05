package genidea.sa;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 6, 2010
 * Time: 8:38:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockDayMoment {
    Date date;
    float deltaComparedCloseBefore = 0;
    float deltaComparedPreviousMaximum = 0;
    float deltaComparedPreviousMin = 0;
    float percentComparedPreviousMaximum = 0;
    float percentComparedPreviousMin = 0;
    float percentComparedCloseBefore = 0;
    float cumulatedPercentComparedClose = 0;

    StockDay actualStock = null;
    StockDay previousStock = null;
    StockDayMoment previousMoment = null;


    public float getPercentComparedCloseBefore() {
        return percentComparedCloseBefore;
    }

    public void setPercentComparedCloseBefore(float percentComparedCloseBefore) {
        this.percentComparedCloseBefore = percentComparedCloseBefore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getDeltaComparedCloseBefore() {
        return deltaComparedCloseBefore;
    }

    public void setDeltaComparedCloseBefore(float deltaComparedCloseBefore) {
        this.deltaComparedCloseBefore = deltaComparedCloseBefore;
    }

    public float getDeltaComparedPreviousMaximum() {
        return deltaComparedPreviousMaximum;
    }

    public void setDeltaComparedPreviousMaximum(float deltaComparedPreviousMaximum) {
        this.deltaComparedPreviousMaximum = deltaComparedPreviousMaximum;
    }

    public float getDeltaComparedPreviousMin() {
        return deltaComparedPreviousMin;
    }

    public void setDeltaComparedPreviousMin(float deltaComparedPreviousMin) {
        this.deltaComparedPreviousMin = deltaComparedPreviousMin;
    }

    public float getPercentComparedPreviousMaximum() {
        return percentComparedPreviousMaximum;
    }

    public void setPercentComparedPreviousMaximum(float percentComparedPreviousMaximum) {
        this.percentComparedPreviousMaximum = percentComparedPreviousMaximum;
    }

    public float getPercentComparedPreviousMin() {
        return percentComparedPreviousMin;
    }

    public void setPercentComparedPreviousMin(float percentComparedPreviousMin) {
        this.percentComparedPreviousMin = percentComparedPreviousMin;
    }


    private float calculatePercentCloseToClose(float actual, float previous){
            float difference = actual-previous;
            int sign = difference > 0 ? 1 : -1;
            float result = sign * (difference/previous) * 100;
        return result;
    }

    private float calculateDeltaComparedCloseBefore (float actual, float previous){
        return actual - previous;
    }

    public float getCumulatedPercentComparedClose() {
        return cumulatedPercentComparedClose;
    }

    public void setCumulatedPercentComparedClose(float cumulatedPercentComparedClose) {
        this.cumulatedPercentComparedClose = cumulatedPercentComparedClose;
    }

    private void continueDirection(StockDay actualStockDay, StockDay previousStockDay, StockDayMoment previousStockDayMoment){
        cumulatedPercentComparedClose = previousStockDayMoment.cumulatedPercentComparedClose + percentComparedCloseBefore;
    }
}
