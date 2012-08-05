package genidea.sa;

import dao.StockDayRepository;
import dao.StockDetailsRepository;
import genidea.sa.model.StockDetails;
import genidea.sa.utils.ReadSecurityListFromFile;
import genidea.sa.utils.SecurityToLoad;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 13, 2010
 * Time: 9:53:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadDataBatch {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-config.xml");
        StockDayRepository stc = (StockDayRepository) context.getBean("stockDayRepository");
        StockDetailsRepository sDetails = (StockDetailsRepository) context.getBean("stockDetailsRepository");
        StockDay sd = null;

        System.out.println ("record in the table:" + stc.countTableRecords());
        
        ImportDataFile idf = new ImportDataFile();
        List<SecurityToLoad> names = ReadSecurityListFromFile.getList();


        Iterator iterator = names.iterator();
        while (iterator.hasNext()){
            SecurityToLoad security = (SecurityToLoad)iterator.next();

            StockDetails stockDetails =  sDetails.getStockDetailsByName(security.getName());

                    if (stockDetails == null){
                        stockDetails = new StockDetails();
                        stockDetails.setStockName(security.getName());
                        stockDetails = sDetails.addStockDetails(stockDetails);
                    }



                    Integer lastImportedDate =  stc.getLastDate(security.getName());
                    if (lastImportedDate == null)
                    lastImportedDate = new Integer(0);
                    idf.setMinDate(lastImportedDate);
                    //StockList sl = idf.fillStockData("/Users/marcoMolteni/Desktop/clariant.csv");
                    StockList sl = LoadDataFromPage.getListFromPage(security.getWebName(), lastImportedDate);

                    Iterator iteratorSL = sl.getList().keySet().iterator();
                    while (iteratorSL.hasNext()){
                        sd = (StockDay) sl.getList().get(iteratorSL.next());
                           sd.setStockDetail(stockDetails);
                           stockDetails.getStocks().add(sd);

                        }
                    sDetails.update(stockDetails);

        }

    
      System.out.println ("record in the table:" + stc.countTableRecords());
  }
}
