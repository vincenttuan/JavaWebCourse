package jrest;

import com.google.gson.Gson;
import dao.UserDAO;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/user")
public class UserRS {
    private UserDAO dao;
    
    {
        dao = new UserDAO();
    }
    
    @GET
    @Path("/{id}")
    public String get(@PathParam("id") int id) {
        return new Gson().toJson(dao.get(id));
    }
    
    @GET
    @Path("/all")
    public String queryAll() {
        return new Gson().toJson(dao.queryAll());
    }
    
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        dao.delete(id);
        return "Delete OK";
    }
    
    @POST
    public String create(@FormParam("username") String username) {
        dao.create(username);
        return "Create OK";
    }
    
    @PUT
    @Path("/{id}")
    public String update(@PathParam("id") int id, String args) {
        // args -> newName=bob
        dao.update(id, args.split("=")[1]);
        return "Update OK";
    }
    
    
}
