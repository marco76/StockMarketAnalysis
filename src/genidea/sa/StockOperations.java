package genidea.sa;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 9, 2010
 * Time: 1:19:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockOperations {
    private LinkedList<Operation> operations = new LinkedList<Operation>();


    public boolean isLastOperationOpen(){
        if (operations.isEmpty())
          return false;

        return operations.getLast().isOpen();
    }


    public void addOperation (Operation operation){
        operations.add(operation);
    }

    public Operation getLastOperation(){
        if (operations.size() == 0)
        return null;
        return operations.getLast();
    }


    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(LinkedList<Operation> operations) {
        this.operations = operations;
    }
}
