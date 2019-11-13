import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PandaResourceTest {
    private Server server;

    @Before
    public void setUp() throws Exception {
        Server server = new Server(7071);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        //*** Add handler for /species, and use jersey
        ServletHolder speciesServletHolder = handler.addServlet(ServletContainer.class, "/species/*");

        speciesServletHolder.setInitOrder(1);
        speciesServletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                SpeciesResource.class.getCanonicalName());
        speciesServletHolder.setInitParameter(ServerProperties.PROVIDER_PACKAGES,
                "com.fasterxml.jackson.jaxrs.json;"
                        + "com.jennifer.study"  // my package(s)
        );

        server.start();
    }

    @After
    public void tearDown() {
        try {
            if (server != null) {
                server.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.destroy();
            }
        }
    }

    @Test
    public void testDeAndReserialization() throws IOException {

        URL url = new URL("http://localhost:7070/species");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        // enable content to be written to the connection output stream
        conn.setDoOutput(true);

        String jsonInputString = "{\"speciesName\":\"fox\",\"lifeSpan\":24,\"ecosystems\":[\"savannah\",\"polar\",\"tundra\"],\"taxonomy\":{\"klass\":\"Mammalia\",\"order\":\"Carnivora\",\"family\":\"Canidae\"}}";

        // write and send the request body
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // read the response
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine);
            }
            assert(response.toString().trim().equals(jsonInputString.trim()));
        }

    }
}
