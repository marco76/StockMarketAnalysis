package genidea.sa;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 10, 2010
 * Time: 12:10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReportDataSource implements JRDataSource {
    HashMap <String, Object> listValues;
    int counter = 0;
    private OperationsSubDataSource ods;
     private ArrayList<StockTrendGen> stockTrendGen;

    public ReportDataSource() {

    }

    public boolean next() throws JRException {
        if(counter < stockTrendGen.size()-1){
            ods = new OperationsSubDataSource();
            ods.setOperations(stockTrendGen.get(counter).getOperations());
           counter+=1;
            return true;
        }
        return false;
    }

    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        if (fieldName.equals("id"))
               return counter;
       
        if (fieldName.equals("closeCapital"))
          return
                 stockTrendGen.get(counter).getCloseCapital();
        if (fieldName.equals("percentToBuy"))
            return stockTrendGen.get(counter).getMomentParameter().cumulatedPercentUpToBuy;
        if (fieldName.equals("percentToSell"))
            return stockTrendGen.get(counter).getMomentParameter().cumulatedPercentDownToSell;
        if (fieldName.equals("indexPerformance"))
            return stockTrendGen.get(counter).getIndexPerformance();
        if (fieldName.equals("win"))
            return stockTrendGen.get(counter).getWinner();
            if (fieldName.equals("lose"))
                       return stockTrendGen.get(counter).getLoser();
        if(fieldName.equals("subreport"))
           return ods;



        

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    public void setStockTrend(ArrayList<StockTrendGen> stockTrendGen) {
        this.stockTrendGen = stockTrendGen;
        listValues = new HashMap<String, Object>();


    }
}
