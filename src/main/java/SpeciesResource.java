import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response postSpecies(Species species) {
        if (species == null || species.checkMissingMembers()) {
            return Response.status(400).entity(species).build();

        }

        System.out.println("species.speciesName = " + species.speciesName);
        System.out.println("species.lifeSpan = " + species.lifeSpan);
        System.out.println("species.taxonomy = " + species.taxonomy);
        System.out.println("species.checkMissingMembers() = " + species.checkMissingMembers());
        System.out.println("species.taxonomy.klass = " + species.taxonomy.klass);

        return Response.status(200).entity(species).build();
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
