package genidea.sa;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 6, 2010
 * Time: 7:48:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImportDataFile {

    public StockList fillStockData(String filename){
        StockList sl = new StockList();
        int previousDate, nextDate = 0;
        try{
     // Open the file that is the first
    // command line parameter
    FileInputStream fstream = new FileInputStream(filename);

            // Get the object of DataInputStream
    DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

    String strLine;
    //Read File Line By Line
        br.readLine();
    while ((strLine = br.readLine()) != null)   {
      // Print the content on the console
    //  System.out.println (strLine);
      int dateLimit = strLine.indexOf(',');
      String date = strLine.substring(0, dateLimit);
        //date = date.replace("-","");
      StockDay sd = new StockDay();
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //please notice the capital M

      //  System.out.println(formatter.parse(date));
        //sd.setDate(formatter.parse(date));
        sd.setDateNumeric(Integer.parseInt(date.replace("-","")));
        if (sd.getDateNumeric() <= minDate)
           break;

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
        String fileText = filename.substring(filename.lastIndexOf("/")+1);
        int fileEnd = fileText.lastIndexOf('.');
        fileText = fileText.substring(0, fileEnd);

        title = fileText.split("_");
        
        sd.setStockName(title[0]);
        sl.addStockDay(sd);

    }
    //Close the input stream
    in.close();
    }catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
    }
     System.out.println ("Dates : " + sl.getList().size());
        return sl;
    }

    int minDate;

    public void setMinDate(int minDate) {
        this.minDate = minDate;
    }
}
