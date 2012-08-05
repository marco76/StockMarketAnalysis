package genidea.sa;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 6, 2010
 * Time: 10:56:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class MomentParameter {
    float cumulatedPercentUpToBuy = 0;
    float cumulatedPercentDownToSell = 0;
    int daysDownToSell = 0;
    int daysUpToBuy = 0;
    private float emaSmooting = 0.9f;

    public float getEmaSmooting() {
        return emaSmooting;
    }

    public void setEmaSmooting(float emaSmooting) {
        this.emaSmooting = emaSmooting;
    }

    public float getEmaDays() {
        return emaDays;
    }

    public void setEmaDays(float emaDays) {
        this.emaDays = emaDays;
    }

    private float emaDays = 20;


    public float getCumulatedPercentUpToBuy() {
        return cumulatedPercentUpToBuy;
    }

    public void setCumulatedPercentUpToBuy(float cumulatedPercentUpToBuy) {
        this.cumulatedPercentUpToBuy = cumulatedPercentUpToBuy;
    }

    public float getCumulatedPercentDownToSell() {
        return cumulatedPercentDownToSell;
    }

    public void setCumulatedPercentDownToSell(float cumulatedPercentDownToSell) {
        this.cumulatedPercentDownToSell = cumulatedPercentDownToSell;
    }

    public int getDaysDownToSell() {
        return daysDownToSell;
    }

    public void setDaysDownToSell(int daysDownToSell) {
        this.daysDownToSell = daysDownToSell;
    }

    public int getDaysUpToBuy() {
        return daysUpToBuy;
    }

    public void setDaysUpToBuy(int daysUpToBuy) {
        this.daysUpToBuy = daysUpToBuy;
    }
}
