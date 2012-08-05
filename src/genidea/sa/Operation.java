package genidea.sa;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 6, 2010
 * Time: 11:25:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Operation {
    private int buyDate = 0;
    private int sellDate = 0;
    private float buyPrice;
    private float sellPrice;
    private boolean winner;
    private float capitalBegin;
    private float stockBuy;
    private float capitalEnd;
    private float capitalResult;
    private float transactionCost;
    private int soldeDays;

    public boolean isOpen(){
        // stock bought and not sold yet
        return (sellDate == 0);


    }

    public float getCapitalBegin() {
        return capitalBegin;
    }

    public void setCapitalBegin(float capitalBegin) {
        this.capitalBegin = capitalBegin;
    }

    public float getStockBuy() {
        return stockBuy;
    }

    public void setStockBuy(float stockBuy) {
        this.stockBuy = stockBuy;
    }

    public float getCapitalEnd() {
        return capitalEnd;
    }

    private float result;

    public boolean getWinner(){
        return winner;
    }

    public float getResult(){
        return result;
    }

    public int getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(int buyDate) {
        this.buyDate = buyDate;
    }

    public int getSellDate() {
        return sellDate;
    }

    public void setSellDate(int sellDate) {
        this.sellDate = sellDate;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
        stockBuy = capitalBegin/buyPrice;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
        result = (sellPrice - buyPrice)/buyPrice*100;
        winner = result > 0;
        capitalEnd = sellPrice*stockBuy;
        transactionCost = capitalEnd/100*1;
        capitalEnd-=transactionCost;
        capitalResult = capitalEnd-capitalBegin;

    }

    public int getSoldeDays() {
        return soldeDays;
    }

    public void setSoldeDays(int soldeDays) {
        this.soldeDays = soldeDays;
    }
}
