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
public class OperationsSubDataSource implements JRDataSource {
    HashMap <String, Object> listValues;
    int counter = 0;
    StockOperations operations;

    public boolean next() throws JRException {
        if(counter < operations.getOperations().size()-1){
           counter+=1;
            return true;
        }
        return false;
    }

    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        if (fieldName.equals("buyDate"))
          return
                operations.getOperations().get(counter).getBuyDate();
        if (fieldName.equals("sellDate"))
            return operations.getOperations().get(counter).getSellDate();
        if (fieldName.equals("buyPrice"))
            return operations.getOperations().get(counter).getBuyPrice();;
        if (fieldName.equals("sellPrice"))
            return operations.getOperations().get(counter).getSellPrice();
        if (fieldName.equals("capitalBegin"))
          return operations.getOperations().get(counter).getCapitalBegin();
        if (fieldName.equals("capitalEnd"))
          return operations.getOperations().get(counter).getCapitalEnd();
        if (fieldName.equals("result"))
          return operations.getOperations().get(counter).getResult();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private ArrayList<StockTrendGen> stockTrendGen;

    public void setOperations(StockOperations stockOperations) {
        this.operations = stockOperations;
        listValues = new HashMap<String, Object>();


    }
}