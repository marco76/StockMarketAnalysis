package genidea.sa;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 8, 2010
 * Time: 10:15:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnalysisReporting {
    int beginDate;
    int endDate;
    String stockName;
    HashMap <String,Object> parameters;

    public AnalysisReporting(HashMap <String,Object> parameters) {
        this.parameters = parameters;
    }

    public void publishReport(){
    try{
        JasperCompileManager.compileReportToFile("/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark.jrxml",
                "/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/CompiledBenchMark.jasper");
    }
    catch (Exception e){
        e.printStackTrace();
    }
    }

    public void runReport(JRDataSource ds, JRDataSource dsDetail){
       try{
           JasperDesign jasperDesign2 = JRXmlLoader.load("/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark_subreport1.jrxml");

           JasperDesign jasperDesign = JRXmlLoader.load("/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark.jrxml");
          // JasperReport jasperReport2 = JasperCompileManager.compileReport(jasperDesign2);//, "/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark.jasper");
          JasperCompileManager.compileReportToFile(jasperDesign2, "/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark_subreport1.jasper");

           //JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, parameters,  dsDetail);
                 
           JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);//, "/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark.jasper");
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,  ds);
           // JasperExportManager.exportReportToPdfFile(jasperPrint2, "/Users/marcomolteni/Documents/ChartReportsub.pdf");

           JasperExportManager.exportReportToPdfFile(jasperPrint, "/Users/marcomolteni/Documents/ChartReport.pdf");
           JasperViewer.viewReport(jasperPrint);
     }catch(Exception ex) {
           String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
           System.out.println(connectMsg);
     }

    }
    
    
}
