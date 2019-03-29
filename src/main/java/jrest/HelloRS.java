package jrest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloRS {
    
    @GET
    public String sayHello() {
        return "Hello !";
    }
    
    @GET
    @Path("/lotto")
    public String getLotto() {
        Set<Integer> set = new HashSet<>();
        while(set.size() < 6) {
            set.add(new Random().nextInt(46) + 1);
        }
        return set.toString();
    }
    
    
}
