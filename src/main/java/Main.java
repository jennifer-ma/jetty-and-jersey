
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(7070);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        //*** Add handler for /hello
        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/hello/*");
        // Tells the Jersey Servlet which REST service/class to load.
        servletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                HomepageResource.class.getCanonicalName());

        //*** Add handler for /panda
        ServletHolder pandaServletHolder = handler.addServlet(ServletContainer.class, "/panda/*");
        pandaServletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                PandaResource.class.getCanonicalName());

        //*** Add handler for /species, and use jersey
        ServletHolder speciesServletHolder = handler.addServlet(ServletContainer.class, "/species/*");
        speciesServletHolder.setInitOrder(1);
        speciesServletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                SpeciesResource.class.getCanonicalName());


        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }

    }
}
