package dao;

import genidea.sa.StockDay;
import genidea.sa.StockList;
import genidea.sa.model.StockDetails;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 13, 2010
 * Time: 9:50:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibStockDayRepository  extends HibernateDaoSupport implements StockDayRepository
{
    
    public void add(StockDay stockDay) {
      //template.save(stockDay);
         getHibernateTemplate().saveOrUpdate(stockDay);
     // System.out.println("record added");
   //   StockDay sd = (StockDay)template.get(StockDay.class, 1);
    //  System.out.println(sd.getId()+":"+  sd.getStockName() );
    }

       /**
        * Sets Hibernate session factory and creates a
        * <code>HibernateTemplate</code> from it.
        */


     public int countTableRecords(){
       return DataAccessUtils.intResult( getHibernateTemplate().find("Select count(*) from StockDay"));
    }


    public StockList getStocksFromToDate(int beginDate, int endDate, String stockName){
        
       // DetachedCriteria criName = DetachedCriteria.forClass(StockDetails.class);
      //  criName.add(Restrictions.eq("stockName", stockName));
      //  DetachedCriteria criStockDay = criName.createCriteria("stocks");
      //  criStockDay.add(Restrictions.between("dateNumeric", beginDate, endDate)).addOrder(Order.asc("dateNumeric"));

     //   StockDetails detail = (StockDetails)getHibernateTemplate().find("from StockDetails where stockName = '" + stockName+"'").get(0);
        String hsql = "Select sd from StockDay as sd left join sd.stockDetail as sde where  sde.stockName = '" + stockName + "'  and sd.dateNumeric >= " + beginDate + " and sd.dateNumeric <= " + endDate + " order by sd.dateNumeric asc";
        // String hsql ="from StockDay";
         org.hibernate.Query query = getSession().createQuery(hsql);

        List<StockDay> result = query.list();

        StockList days = new StockList();
        Iterator<StockDay> iterator = result.iterator();
        while (iterator.hasNext())
          days.addStockDay(iterator.next());
        return days;
    }

     public Float getAbsoluteMaxFromTodate(int beginDate, int endDate, String stockName){    

         SQLQuery query = getSession().createSQLQuery("Select max(sd.max_d) from stockday sd, stockdetails sdet where sdet.NAME = '" +stockName+ "' AND sdet.id = sd.stockDetails_id\n" +
                 "and sd.datenumeric >=" + beginDate + " and datenumeric <="+ endDate);
         //String hsql = "Select max(sd.max) from StockDay as sd left join sd.stockDetail as sde where  sde.stockName = '" + stockName + "'  and sd.dateNumeric >= " + beginDate + " and sd.dateNumeric <= " + endDate;
        // String hsql ="from StockDay";
         //org.hibernate.Query query = getSession().createQuery(hsql);

        Float result = (Float) query.uniqueResult();



        return result;
    }


   
    public Integer getLastDate(String stockName){
        SQLQuery query = getSession().createSQLQuery("Select max (DATENUMERIC) FROM STOCKDAY sd, STOCKDETAILS sde WHERE sde.Name like '" + stockName + "' and sde.id = sd.STOCKDETAILS_ID");
               return (Integer)query.uniqueResult();

    }

}
