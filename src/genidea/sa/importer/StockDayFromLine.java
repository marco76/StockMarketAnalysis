package genidea.sa.importer;

import genidea.sa.StockDay;

import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 15, 2010
 * Time: 11:28:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockDayFromLine {

    public static StockDay getStockDayFromLine(String strLine){
           int dateLimit = strLine.indexOf(',');
           String date = strLine.substring(0, dateLimit);
             //date = date.replace("-","");
           StockDay sd = new StockDay();
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //please notice the capital M

           sd.setDateNumeric(Integer.parseInt(date.replace("-","")));
   //          if (sd.getDateNumeric() <= minDate)
   //             break;

             int endOpenLimit = strLine.indexOf(',',dateLimit+1);
           float open = Float.parseFloat(strLine.substring(dateLimit+1, endOpenLimit));

             int endmaxLimit = strLine.indexOf(',',endOpenLimit+1);
                   float max = Float.parseFloat(strLine.substring(endOpenLimit+1, endmaxLimit));
             // load minimum
             int endminLimit = strLine.indexOf(',',endmaxLimit+1);
              float min = Float.parseFloat(strLine.substring(endmaxLimit+1, endminLimit));
             // load close
                    int endcloseLimit = strLine.indexOf(',',endminLimit+1);
                     float close = Float.parseFloat(strLine.substring(endminLimit+1, endcloseLimit));
             sd.setClose(close);
             sd.setOpen(open);
             sd.setMax(max);
             sd.setMin(min);
             String [] title;
             // @TODO windows compatibility
     //        String fileText = filename.substring(filename.lastIndexOf("/")+1);
     //        int fileEnd = fileText.lastIndexOf('.');
      //       fileText = fileText.substring(0, fileEnd);

      //       title = fileText.split("_");

      //       sd.setStockName(title[0]);
             return (sd);


    }
}
