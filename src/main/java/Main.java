
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/");

        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
        server.setHandler(handler);
//        servletHolder.setInitOrder(0);

//        servletHolder.setInitParameter("jersey.config.server.provider.packages", "");

        // Tells the Jersey Servlet which REST service/class to load.
        servletHolder.setInitParameter(
                "jersey.config.server.provider.classnames",
                HomepageResource.class.getCanonicalName());

//        handler.addServlet(MyServlet.class, "/hello");
//        handler.addServlet(PandaServlet.class, "/panda/*");
        

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
