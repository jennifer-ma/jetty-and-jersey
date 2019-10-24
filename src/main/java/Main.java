import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(MyServlet.class, "/hello");
        handler.addServlet(PandaServlet.class, "/panda/*");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
