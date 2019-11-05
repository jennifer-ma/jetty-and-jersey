import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class SpeciesResource {

    @GET
    public String getPanda() {
        return "Panda";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String postSpecies(Species species) {
//        return species.speciesName;
        System.out.println("species.speciesName = " + species.speciesName);
        System.out.println("species.lifeSpan = " + species.lifeSpan);
        System.out.println("species.taxonomy = " + species.taxonomy);
        System.out.println("species.taxonomy.klass = " + species.taxonomy.klass);
        return "test";

//        String output = species.toString();

//        return Response.status(200).entity(output).build();
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
