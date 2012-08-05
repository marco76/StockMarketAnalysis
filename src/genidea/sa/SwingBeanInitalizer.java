package genidea.sa;

import dao.BackTestResultRepository;
import dao.StockDayRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 13, 2010
 * Time: 3:20:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class SwingBeanInitalizer {
    static StockDayRepository stockDayRepository;
    static BackTestResultRepository backTestRepository;

    public static void initialize() {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-config.xml");
        stockDayRepository = (StockDayRepository) context.getBean("stockDayRepository");
        backTestRepository = (BackTestResultRepository) context.getBean("backTestResultRepository");
    }

    public static StockDayRepository getStockDayRepository() {
        return stockDayRepository;
    }

    public static BackTestResultRepository getBackTestRepository() {
        return backTestRepository;
    }
}
