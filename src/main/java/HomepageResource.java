

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HomepageResource {

    @GET
//    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "helloo world!";
    }
}
