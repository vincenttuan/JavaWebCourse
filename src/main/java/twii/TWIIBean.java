package twii;

import com.google.gson.Gson;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class TWIIBean{
    public String query(double yield, double pe, double pb) {
        return new Gson().toJson(new TWIIService().query(yield, pe, pb));
    }
}
