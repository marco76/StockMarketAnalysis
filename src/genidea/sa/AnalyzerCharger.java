package genidea.sa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 13, 2010
 * Time: 1:30:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnalyzerCharger {
    String method = null;
    float parameter = 0.0f;
    float sellParameter = 0.0f;
    int startDate;
    int endDate;
    HashMap<String, Float> extraParameters = new HashMap<String,Float>();

    public AnalyzerCharger(String method, String sellMethod, float parameter, float sellParameter) {
        this.method = method;
        this.parameter = parameter;
        this.sellParameter = sellParameter;
        
    }

    public AnalyzerCharger(){
        
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

   
    public float getParameter() {
        return parameter;
    }

    public void setParameter(float parameter) {
        this.parameter = parameter;
    }

    public float getSellParameter() {
        return sellParameter;
    }

    public void setSellParameter(float sellParameter) {
        this.sellParameter = sellParameter;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public void addExtraParameter(String name, Float parameter){
        this.extraParameters.put(name, parameter);
    }

    public Float getExtraParameter(String name){
        return this.extraParameters.get(name);
    }
}
