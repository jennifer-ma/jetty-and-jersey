
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;

public class Main {

    public static void main(String[] args) {

        Server server = new Server();
//        Server server = new Server(7070);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(7070);

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


        HttpConfiguration https = new HttpConfiguration();

        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();

        sslContextFactory.setKeyStorePath(Main.class.getResource("/keystore.jks").toExternalForm());

        sslContextFactory.setKeyStorePassword("123456");

        sslContextFactory.setKeyManagerPassword("123456");

        ServerConnector sslConnector = new ServerConnector(server,

                new SslConnectionFactory(sslContextFactory, "http/1.1"),

                new HttpConnectionFactory(https));

        sslConnector.setPort(8080);

        server.setConnectors(new Connector[] { connector, sslConnector });




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
