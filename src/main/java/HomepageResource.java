

import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HomepageResource {

    private PersistenceService storage;

    @Inject
    public HomepageResource(PersistenceService storage) {
        this.storage = storage; //new InMemoryPersistence();
    }

    @GET
    public String get() {
        // test
        storage.persistSomething();

        return "helloo world!";
    }
}
