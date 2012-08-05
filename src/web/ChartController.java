package web;

import dao.StockDayRepository;
import genidea.sa.Chart;
import genidea.sa.StockDay;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 24, 2010
 * Time: 1:34:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChartController  implements Controller {

    StockDayRepository stocks;

    public org.springframework.web.servlet.ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        resp.setContentType("image/png");

        
         Chart chart = new Chart();

         chart.setStockList(stocks.getStocksFromToDate(20090101, 20100122, "Clariant"));
       //  chart.setTestData(stockTrendEMA.getEmaRecord());

         ChartUtilities.writeChartAsPNG(resp.getOutputStream(), chart.getChart(null), 400, 400);
        resp.getOutputStream().close();
        return null;
    }

    public void setStocks(StockDayRepository stocks) {
        this.stocks = stocks;
    }
}
