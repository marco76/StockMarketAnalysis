package genidea.sa.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 15, 2010
 * Time: 11:10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReadSecurityListFromFile {


    public static List<SecurityToLoad> getList(){

    File file = new File("/Users/marcomolteni/Desktop/dataToLoad.txt");
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
     List<SecurityToLoad> result = new ArrayList<SecurityToLoad>();
    try {
      fis = new FileInputStream(file);

      // Here BufferedInputStream is added for fast reading.
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);

      // dis.available() returns 0 if the file does not have more lines.
      while (dis.available() != 0) {

      // this statement reads the line from the file and print it to
        // the console.
        String line = dis.readLine();
          StringTokenizer st = new StringTokenizer(line, ",");
          String name = st.nextToken();
          String fileName = st.nextToken();
          result.add(new SecurityToLoad (name, fileName));

      }

      // dispose all the resources after using them.
      fis.close();
      bis.close();
      dis.close();

    } catch (
    FileNotFoundException e) {
      e.printStackTrace();
    } catch (
    IOException e) {
      e.printStackTrace();
    }
    return result;
    }

}
