package genidea.sa;

import genidea.sa.importer.StockDayFromLine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 15, 2010
 * Time: 8:45:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadDataFromPage {
   public static List<String> readPage(String dataName){
       List<String> result = new ArrayList<String>();

       try{

            URL page = new URL("http://ichart.yahoo.com/table.csv?s="+dataName);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(page.openStream()));
            String inputLine;
            in.readLine();
            while ((inputLine = in.readLine()) != null) {
            result.add(inputLine);     // Process each line.
             }
            in.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    return result;
   }

    public static StockList getListFromPage (String name, Integer lastDate){
        StockList sl = new StockList();
        List<String> data = readPage(name);
        Iterator<String> iterator = data.iterator();
         StockDay sd = null;
        while (iterator.hasNext()){
            sd = StockDayFromLine.getStockDayFromLine(iterator.next());
            if (sd.getDateNumeric() <= lastDate)
              break;
            sl.addStockDay(sd);
            sd = null;
        }
        
        return sl;
    }
}


