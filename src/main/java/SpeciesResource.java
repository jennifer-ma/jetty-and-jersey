import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class SpeciesResource {

    @GET
    public String getPanda() {
        return "Panda";
    }

//    @GET
//    @Path("/{name}")
//    public String getPanda(@PathParam("name") String name) {
//        return "Panda's name is " + name;
//    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postSpecies(Species species) {
        return species.speciesName;
//        return "Panda's name is " + name;

    }


    @POST
    @Path("test")
    @Consumes(MediaType.APPLICATION_JSON)
    public String postSpecies(String body) {
        System.out.println("calling postSpecies");
        return body;
//        return "Panda's name is " + name;

    }

}
