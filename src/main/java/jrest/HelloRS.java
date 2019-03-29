package jrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class HelloRS {
    @GET
    public String sayHelloWorld() {
        return "Hello world";
    }
}
