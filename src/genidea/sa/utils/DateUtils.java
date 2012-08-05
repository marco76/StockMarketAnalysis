package genidea.sa.utils;

import genidea.sa.StockDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 9, 2010
 * Time: 10:22:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    public static int convertStringToInt(String date){
       return Integer.parseInt(date);
    }


    public static int getNextDay(int date, int amount){
        int result = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cd = new GregorianCalendar();
        try{
             cd.setTime(df.parse(String.valueOf(date)));
           }
           catch(Exception e){}
           cd.add(Calendar.DATE, amount);
           result = Integer.parseInt(df.format(cd.getTime()));
        return result;
    }
}
