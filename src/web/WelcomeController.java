package web;

import dao.StockDayRepository;
import dao.StockDetailsRepository;
import genidea.sa.Chart;
import genidea.sa.StockAnalysis;
import genidea.sa.StockAnalyzer;
import genidea.sa.model.StockDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartUtilities;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 23, 2010
 * Time: 8:53:07 PM
 * To change this template use File | Settings | File Templates.
 */

public class WelcomeController extends MultiActionController {

     StockDayRepository stc;
     StockDetailsRepository sDetails;
     List<StockDetails> stocks;


    StockDayRepository stockData;

    Log logger = LogFactory.getLog(getClass());
    public ModelAndView welcome(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Date today = new Date();

        logger.info("Return welcome");

         stocks =  sDetails.getListOfAvailableStocks();

         return new ModelAndView("welcome", "stocks", stocks);  //To change body of implemented methods use File | Settings | File Templates.
    }
    public org.springframework.web.servlet.ModelAndView chart(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        resp.setContentType("image/png");

         Chart chart = new Chart();

         chart.setStockList(stockData.getStocksFromToDate(20090101, 20100122, "CSGN"));
       //  chart.setTestData(stockTrendEMA.getEmaRecord());

         ChartUtilities.writeChartAsPNG(resp.getOutputStream(), chart.getChart(null), 400, 400);
        resp.getOutputStream().close();
        return null;
    }
  
    public void setStc(StockDayRepository stc) {
        this.stc = stc;
    }

    public void setsDetails(StockDetailsRepository sDetails) {
        this.sDetails = sDetails;
    }
    public void setStockData(StockDayRepository stockData) {
          this.stockData = stockData;
      }
    
}
