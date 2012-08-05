package dao;

import genidea.sa.BackTestPerformance;
import genidea.sa.StockDay;
import genidea.sa.StockList;
import genidea.sa.model.BackTestResult;
import org.hibernate.SessionFactory;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 13, 2010
 * Time: 9:00:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BackTestResultRepository {
    public void truncateTable();
    public void setSessionFactory(SessionFactory sessionFactory);
    public void add(BackTestPerformance backTestPerformance);

}