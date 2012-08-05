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
public class DayTrend {


    public static HashMap<Integer, Float> dayTrend(StockList source, int period){
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
                  int positiveDays = countPositiveDays(container);

                  result.put(sd.getDateNumeric(), (float)positiveDays);
                 //           System.out.println ("day: " + sd.getDateNumeric() + " , " + tempMax);
          }
             dayCounter++;
    }
        return result;
    }

         static private int countPositiveDays(LinkedList<Float> container){
             int result = 0;
             Iterator <Float> iterator = container.iterator();
              float actual = 0.0f;
              float previous = 0.0f;

              while (iterator.hasNext()){
                  actual = iterator.next();
                  if (actual > previous)
                    result++;
                   previous = actual;
              }
        return result;
    }





}