package genidea.sa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: extMolteniM
 * Date: 11 janv. 2010
 * Time: 16:40:23
 * To change this template use File | Settings | File Templates.
 */
public class MovingAverage {


    /**
     * Calculate the Exponential Moving Average (EMA) value. The Exponential Moving
     * Average is a weighted moving average where the most recent values are
     * weighted higher than the previous values.
     * <p/>
     * The formula for the EMA is as follows:</pre>
     * <p/>
     * EMA(current) = EMA(previous) + k * (day close - EMA(previous))
     * <p/>
     * </pre>Where EMA(current) is the current EMA value you are calculating,
     * EMA(previous) is the previous value and <code>k</code> is a smoothing
     * constant.
     *
     * @param source            the source of quotes to average
     * @param period            the number of days to analyse
     * @param smoothingConstant a smoothing constant
     * @return the exponential moving average
     */
    static public double ema(List<Double> source, int period, double smoothingConstant)
            throws Exception {

        double EMA = 0.0D;
        double previousEMA = 0.0D;
        int actualPeriod = 0;

        for (int i = 0; i < period; i++) {
            double value = source.get(i);

            if (!Double.isNaN(value)) {
                if (actualPeriod >= 1)
                    EMA = previousEMA + smoothingConstant * (value - previousEMA);

                else
                    EMA = value;

                previousEMA = EMA;
                actualPeriod++;
            }
        }
        return EMA;
    }

    public static HashMap<Integer, Float> emaList(StockList source, int period){
        HashMap <Integer, Float> emaResult = new HashMap<Integer,Float> ();
        LinkedList <Float> emaContainer = new LinkedList<Float> ();

        float smoothingConstant = 2/(1+period);
        int dayCounter = 0;
        // prepare the ema
        Iterator days =  source.getList().keySet().iterator();
        float tempEma = 0.0f;
        float previousEma = 0.0f;

        while (days.hasNext()){
            StockDay sd = source.getDay((Integer) days.next());

            if (dayCounter < period){
              emaContainer.add(sd.getClose());
            }

            if (dayCounter >= period){
                emaContainer.removeFirst();
                emaContainer.add(sd.getClose());

            for (int i = 0; i < period; i++) {
            float value = emaContainer.get(i);

            if (!Float.isNaN(value)) {
            if (i >= 1)
              tempEma = previousEma + smoothingConstant * (value - previousEma);
            else
              tempEma = value;

              previousEma = tempEma;
             }
           }
            emaResult.put(sd.getDateNumeric(), tempEma);
            //   System.out.println ("day: " + sd.getDateNumeric() + " , " + tempEma);

         }
            dayCounter++;


      }

        return emaResult;

    }


}