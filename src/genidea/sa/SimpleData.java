package genidea.sa;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Jan 12, 2010
 * Time: 7:42:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleData {
    Double value;
    Integer dateNumeric;

    public SimpleData(Integer dateNumeric, Double value) {
        this.value = value;
        this.dateNumeric = dateNumeric;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getDateNumeric() {
        return dateNumeric;
    }

    public void setDateNumeric(Integer dateNumeric) {
        this.dateNumeric = dateNumeric;
    }
}
