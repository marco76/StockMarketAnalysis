package dao;


import genidea.sa.BackTestPerformance;
import genidea.sa.StockDay;
import genidea.sa.StockList;
import genidea.sa.model.BackTestResult;
import genidea.sa.model.BackTestResultKey;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Iterator;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 13, 2010
 * Time: 9:50:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibBackTestResultRepository extends HibernateDaoSupport implements BackTestResultRepository{

    public void truncateTable() {
   //   getSession().createSQLQuery("TRUNCATE TABLE BACKTESTRESULT").executeUpdate();
        String myTable = "BackTestResult";
        String hql = String.format("delete from %s",myTable);
        getHibernateTemplate().bulkUpdate(hql);
        // Query query = hiber.createQuery(hql)
    //return query.executeUpdate();
    }

    public void add(BackTestPerformance backTestPerformance) {
        BackTestResult btr = new BackTestResult();
        btr.setBuyParameter(backTestPerformance.getBuyProperty());
        btr.setSellParameter(backTestPerformance.getSellProperty());
        btr.setCumulatedResult(backTestPerformance.getCapitalEnd());
        btr.setLosses(backTestPerformance.getLoss());
        btr.setWin(backTestPerformance.getWin());



        BackTestResultKey btrk = new BackTestResultKey();
        btrk.setBuyProperty(btr.getBuyParameter());
        btrk.setSellProperty(btr.getSellParameter());
        btrk.setStockName(backTestPerformance.getStockName());
       
        btr.setBtrKEY(btrk);

               BackTestResult bt = getHibernateTemplate().get(BackTestResult.class, btrk);
               if (bt == null){
                  getHibernateTemplate().saveOrUpdate(btr);
               }


    }

    /**
        * Sets Hibernate session factory and creates a
        * <code>HibernateTemplate</code> from it.
        */
}