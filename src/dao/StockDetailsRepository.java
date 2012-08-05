package dao;

import genidea.sa.model.StockDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 19, 2010
 * Time: 11:06:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StockDetailsRepository {
    StockDetails getStockDetailsByName(String name);
    public void update(StockDetails sd);
    StockDetails addStockDetails(StockDetails sd);
    public List<StockDetails> getListOfAvailableStocks();
       
}
