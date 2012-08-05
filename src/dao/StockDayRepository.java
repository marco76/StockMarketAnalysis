package dao;

import genidea.sa.StockDay;
import genidea.sa.StockList;
import org.hibernate.SessionFactory;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 13, 2010
 * Time: 9:00:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StockDayRepository {
    void add(StockDay stockDay);
    int countTableRecords();
    StockList getStocksFromToDate(int beginDate, int endDate, String stockName);
    public Integer getLastDate(String stockName);
    public void setSessionFactory(SessionFactory sessionFactory);    
    public Float getAbsoluteMaxFromTodate(int beginDate, int endDate, String stockName);

    
}
