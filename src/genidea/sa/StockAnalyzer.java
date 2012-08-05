package genidea.sa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 9, 2010
 * Time: 1:03:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class StockAnalyzer {
    private List<Operation> operations = new ArrayList<Operation>();
    private float initialCapital;
    private float finalCapital;
    private float initialStockValue;
    private float finalStockValue;
    private int initialDate;
    private int finalDate;


    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public float getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(float initialCapital) {
        this.initialCapital = initialCapital;
    }

    public float getFinalCapital() {
        return finalCapital;
    }

    public void setFinalCapital(float finalCapital) {
        this.finalCapital = finalCapital;
    }

    public float getInitialStockValue() {
        return initialStockValue;
    }

    public void setInitialStockValue(float initialStockValue) {
        this.initialStockValue = initialStockValue;
    }

    public float getFinalStockValue() {
        return finalStockValue;
    }

    public void setFinalStockValue(float finalStockValue) {
        this.finalStockValue = finalStockValue;
    }

    public int getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(int initialDate) {
        this.initialDate = initialDate;
    }

    public int getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(int finalDate) {
        this.finalDate = finalDate;
    }
}
