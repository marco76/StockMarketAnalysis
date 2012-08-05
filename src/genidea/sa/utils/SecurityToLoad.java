package genidea.sa.utils;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: Feb 15, 2010
 * Time: 11:20:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityToLoad {
    private String name;
    private String webName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public SecurityToLoad(String name, String webName) {
        this.name = name;
        this.webName = webName;
    }
}
