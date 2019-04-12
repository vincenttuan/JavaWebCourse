package myjpa;

import com.google.gson.Gson;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    
    @POST
    public String create(@FormParam("name") String name, @FormParam("age") int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        controller.save(user);
        return "Create OK";
    }
    
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") long id) {
        User user = controller.findById(id);
        if(user != null) {
            controller.delete(user);
            return "delete OK";
        } else {
            return "No data";
        }
    }
    
    @PUT
    @Path("/{id}")
    public String update(@PathParam("id") long id, @FormParam("name") String name, @FormParam("age") int age) {
        User user = controller.findById(id);
        if(user != null) {
            user.setName(name);
            user.setAge(age);
            controller.update(user);
            return "Update OK";
        } else {
            return "No data";
        }
    }
}
