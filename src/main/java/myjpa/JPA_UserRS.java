package myjpa;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/jpa_user_rs")
public class JPA_UserRS {
    private JPA_UserController controller = new JPA_UserController();
    @GET
    @Path("/{id}")
    public String get(@PathParam("id") long id) {
        return new Gson().toJson(controller.findById(id));
    }
    
    @GET
    @Path("/all")
    public String queryAll() {
        return new Gson().toJson(controller.queryAll());
    }
}
