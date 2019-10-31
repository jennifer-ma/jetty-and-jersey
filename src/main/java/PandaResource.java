import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class PandaResource {

    @GET
    public String getPanda() {
        return "Panda";
    }

    @GET
    @Path("/{name}")
    public String getPanda(@PathParam("name") String name) {
        return "Panda's name is " + name;
    }


    @POST
    @Consumes("application/x-www-form-urlencoded")
    public String postPanda(@FormParam("name") String name) {
        return "Panda's name is " + name;

    }
}
