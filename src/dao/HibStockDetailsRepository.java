package dao;

import genidea.sa.StockDay;
import genidea.sa.model.StockDetails;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 19, 2010
 * Time: 10:58:38 PM
 * To change this template use File | Settings | File Templates.
 */

public class HibStockDetailsRepository extends HibernateDaoSupport implements StockDetailsRepository {

  @Transactional
  public StockDetails getStockDetailsByName(String name){
      StockDetails result = null;
      DetachedCriteria dc = DetachedCriteria.forClass(StockDetails.class).
                add(Restrictions.eq("stockName", name));
      List<StockDetails> resultList = getHibernateTemplate().findByCriteria(dc);
      if (resultList.size() > 0)
       result = (StockDetails) resultList.get(0);
      return result;
  }
     @Transactional
    public StockDetails addStockDetails(StockDetails sd){
      return (StockDetails) getHibernateTemplate().merge(sd);
  }

  @Transactional
    public void update(StockDetails sd){
        getHibernateTemplate().saveOrUpdate(sd);
    }
    @Transactional(readOnly = true)
    public List<StockDetails> getListOfAvailableStocks(){
        return getHibernateTemplate().find("from StockDetails");
    }
    
}
