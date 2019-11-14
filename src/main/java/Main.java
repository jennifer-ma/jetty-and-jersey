
import com.google.inject.Guice;
import com.google.inject.Injector;
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

public class Main {

    public static void main(String[] args) {

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(7070);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        final Injector injector = Guice.createInjector(new PersistenceModule());
//        PersistenceService s = injector.getInstance(PersistenceService.class);
//        s.persistSomething();

//        HomepageResource s = injector.getInstance(HomepageResource.class);
//        s.get();
//        s.persistSomething();


        //*** Add servlet for /hello
        // Initialize Jersey.
        ServletContainer container = new ServletContainer(injector.getInstance(ResourceConfig.class));
        ServletHolder holder = new ServletHolder(container);
        context.addServlet(holder, "/hello/*");

//        // Initialize Jersey.
//        ServletContainer container = new ServletContainer(injector.getInstance(ResourceConfig.class));
//        ServletHolder servletHolder = new ServletHolder(container);
////        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/hello/*");
//        // Tells the Jersey Servlet which REST service/class to load.
//        servletHolder.setInitParameter(
//                "jersey.config.server.provider.classnames",
//                HomepageResource.class.getCanonicalName());
//        context.addServlet(servletHolder, "/hello/*");

//        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/hello/*");
//        servletHolder.setInitParameter(
//                "jersey.config.server.provider.classnames",
//                HomepageResource.class.getCanonicalName());


        //*** Add servlet for /panda
        ServletHolder pandaServletHolder = context.addServlet(ServletContainer.class, "/panda/*");
        pandaServletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                PandaResource.class.getCanonicalName());

        //*** Add servlet for /species, and use jersey
        ServletHolder speciesServletHolder = context.addServlet(ServletContainer.class, "/species/*");
        speciesServletHolder.setInitOrder(1);
        speciesServletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                SpeciesResource.class.getCanonicalName());


        // enable https support
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
