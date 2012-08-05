package genidea.sa;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 21, 2010
 * Time: 5:35:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParametersAnalyzer {

    HashMap <String, Object> parameters = new HashMap<String, Object>();

    public void addParameter(String name, Object value){
        parameters.put(name, value);
    }

    public Object getParameter (String name){
        return parameters.get(name);
    }

    public void setStartDate(int startDate){
        parameters.put("startDate", startDate);
    }

    public int getStartDate(){
      return Integer.parseInt(parameters.get("startDate").toString());
    }

     public void setEndDate(int endDate){
        parameters.put("endDate", endDate);
    }

    public int getEndDate(){
      return Integer.parseInt(parameters.get("endDate").toString());
    }

}