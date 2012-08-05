package genidea.sa;

import java.util.*;

import genidea.sa.StockDay;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 6, 2010
 * Time: 8:01:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockList {
    public HashMap<Integer, StockDay> getList() {
        return list;
    }

    public void setList(LinkedHashMap<Integer, StockDay> list) {
        this.list = list;
    }

    private LinkedHashMap<Integer, StockDay> list = new LinkedHashMap<Integer, StockDay>();

    public void addStockDay(StockDay stockDay){
        list.put(stockDay.getDateNumeric(), stockDay);
    }

    public StockDay getDay(int date){

        return list.get(date);
    }


}
