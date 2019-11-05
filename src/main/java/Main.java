
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(7070);

//        ServletContextHandler handler = new ServletContextHandler(server, "/");
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        server.setHandler(handler);


        //*** Add handler for /hello
        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/hello/*");
//        servletHolder.setInitOrder(0);

//        servletHolder.setInitParameter("jersey.config.server.provider.packages", "");

//        System.out.println("this.getClass().getPackage().getName() = " + this.getClass().getPackage().getName());
//        System.out.println("this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
//        System.out.println("Main.class.getPackageName() = " + Main.class.getPackage().getName());

        // Tells the Jersey Servlet which REST service/class to load.
        servletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                HomepageResource.class.getCanonicalName());
//

        //*** Add handler for /panda
        ServletHolder pandaServletHolder = handler.addServlet(ServletContainer.class, "/panda/*");
        pandaServletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                PandaResource.class.getCanonicalName());


        //*** Add handler for /species, and use jersey

//        ServletHolder speciesServletHolder = handler.addServlet(ServletContainer.class, "/species/*");
////         set the location to search for paths
//        speciesServletHolder.setInitParameter(
//                "jersey.config.server.provider.classnames",
//                SpeciesResource.class.getCanonicalName());

        // Create JAX-RS application.
        // ResourceConfig is a jersey class
//        final ResourceConfig application = new ResourceConfig()
//                .packages("jersey.jetty.embedded")
//                .register(JacksonFeature.class); // for json-to-object conversion

//        // use a jersey servlet container and put the jax-rs application in there
//        ServletHolder speciesServletHolder = new ServletHolder(new
//                org.glassfish.jersey.servlet.ServletContainer(application));

        // handler is a jetty thing
        // add the servlet and set the path...
        ServletHolder speciesServletHolder = handler.addServlet(ServletContainer.class, "/species/*");
//        handler.addServlet(speciesServletHolder, "/species/*");
        speciesServletHolder.setInitOrder(1);
        speciesServletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                SpeciesResource.class.getCanonicalName());
        speciesServletHolder.setInitParameter(ServerProperties.PROVIDER_PACKAGES,
                "com.fasterxml.jackson.jaxrs.json;"
                        + "jersey.jetty.embedded"  // my package(s)
        );


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
