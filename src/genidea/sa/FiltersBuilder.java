package genidea.sa;

import genidea.sa.algo.Analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 17, 2010
 * Time: 8:38:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class FiltersBuilder {
    boolean isSell = false;
    List<Analyzer> list = new ArrayList<Analyzer>();

    StockList stockList;
    StockSearch stockSearch;

    HashMap <String, Analyzer> analyzerMap = new HashMap<String, Analyzer>();

    public void clearList(){
        list = null;
    }

    public void addFilter(String nameAlgo, StockSearch stockSearch, ParametersFilter parametersFilter){
      AnalyzerCharger ac = new AnalyzerCharger();
        ac.setMethod(nameAlgo);
        ac.setStartDate(parametersFilter.getStartDate());
        ac.setEndDate(parametersFilter.getEndDate());
        this.stockSearch = stockSearch;
        Analyzer analyzer = AnalyzerFactory.getAnalyzer(ac.getMethod(),stockSearch);
        analyzer.setCharger(ac);
        analyzerMap.put(nameAlgo, analyzer);
        list.add(analyzer);
        
    }

    public void prepareData(){
        Iterator <Analyzer> iterator =  list.iterator();
        while (iterator.hasNext()){
            Analyzer ana = iterator.next();
            ana.prepareData(stockSearch);
        }

    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

    public List<Analyzer> getList() {
        return list;
    }

    public void setList(List<Analyzer> list) {
        this.list = list;
    }

    boolean isAction(int date){
        boolean result = true;
        Iterator <Analyzer> iterator = list.iterator();
        while (iterator.hasNext()){
            Analyzer analyzer = iterator.next();
            if (!isSell){
                if (analyzer.isBuy(date) == false){
                    result = false;
                    break;
                }
            }
            else
            {
             if (analyzer.isSell(date) == false){
                    result = false;
                    break;
                }
            }
            }
        return result;
    }

    public AnalyzerCharger getCharger(String analyzerName){
        return analyzerMap.get(analyzerName).getCharger();
    }

}