package genidea.sa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 8, 2010
 * Time: 10:49:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EMATrend {
    LinkedList <Double> stockValues = new LinkedList<Double>();
    List<SimpleData> emaRecord= new ArrayList<SimpleData>();
    private double currentEma = 0.0;

    private int emaDays = 50;

    public  List<SimpleData> calculate (StockList list){

        StockDay actualStock;

        Iterator iterator = list.getList().keySet().iterator();
        while (iterator.hasNext()){
            actualStock = (StockDay) list.getList().get(iterator.next());
            stockValues.add(new Double(actualStock.getClose()));
            if (stockValues.size()>emaDays)
                        stockValues.remove(0);
                     if (stockValues.size() >(emaDays-1))
                         try {
                             currentEma = MovingAverage.ema(stockValues, emaDays , 0.00001);
                             emaRecord.add(new SimpleData(actualStock.getDateNumeric(), currentEma));
                         } catch (Exception e) {
                             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                         }
        }

        return emaRecord;
        }

    public double getCurrentEma() {
        return currentEma;
    }

    public List<SimpleData> getEmaRecord() {
        return emaRecord;
    }

    public void setEmaDays(int emaDays) {
        this.emaDays = emaDays;
    }

    }