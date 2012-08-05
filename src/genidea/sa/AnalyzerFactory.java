package genidea.sa;

import genidea.sa.algo.*;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 14, 2010
 * Time: 10:38:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnalyzerFactory {
    // TODO use static enum
    public static Analyzer getAnalyzer(String name, StockSearch stockSearch){
        if (name.equals("Bolchian"))
          return new BolchianAnalyzer(stockSearch);

        if (name.equals("EMA"))
           return new EmaAnalyzer(stockSearch);

        if (name.equals("Percent"))
           return new PercentAnalyzer(stockSearch);
        if (name.equals("PercentFromTop"))
           return new PercentFromTopAnalyzer(stockSearch);

        if (name.equals("DayTrend"))
            return new  DaysTrendAnalyzer(stockSearch);
        if (name.equals("EmaCrossover"))
            return new EmaCrossoverAnalyzer(stockSearch);
     return null;
    }
}
