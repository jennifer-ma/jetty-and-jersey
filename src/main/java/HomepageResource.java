

import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HomepageResource {

    private PersistenceService storage;

    @Inject
    public HomepageResource(PersistenceService storage) {
        this.storage = storage;
    }

    @GET
    public String get() {
        // test
        storage.persistSomething();

        return "helloo world!";
    }
}
