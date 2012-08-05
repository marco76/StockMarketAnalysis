package genidea.sa.algo;

import genidea.sa.StockDay;
import genidea.sa.StockList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 7, 2010
 * Time: 8:47:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bolchian {

    public static float getUp (Float [] values)
    {
        float max = 0.0f;
        for (int i = 0 ; i < values.length; i++){
          max = Math.max(max, values[i]);
        }
     return max;
    }

    public static float getDown (Float [] values)
    {
        float min = 9999999.0f;
        for (int i = 0 ; i < values.length; i++){
          min = Math.min(min, values[i]);
        }
     return min;   
    }

    public static HashMap<Integer, Float> upperBand(StockList source, int period, boolean top){
          HashMap <Integer, Float> result = new HashMap<Integer,Float> ();
          LinkedList<Float> container = new LinkedList<Float> ();
          float tempMax = 0.0f;
          int dayCounter = 0;

          Iterator days =  source.getList().keySet().iterator();

          while (days.hasNext()){

              StockDay sd = source.getDay((Integer) days.next());

              if (dayCounter < period){
                  container.add(sd.getClose());
              }

              if (dayCounter >= period){
                  container.removeFirst();
                  container.add(sd.getClose());
                  Float[] data = container.toArray(new Float[0]);

                if (top)
                    tempMax = getUp(data);
                  else
                    tempMax = getDown(data);

                  result.put(sd.getDateNumeric(), tempMax);
                 //           System.out.println ("day: " + sd.getDateNumeric() + " , " + tempMax);




          }
             dayCounter++;
    }





          return result;

      }

}
