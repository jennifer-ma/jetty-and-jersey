import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PandaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setStatus(HttpStatus.OK_200);

        // check if there is a name in the url
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("")) {
            resp.getWriter().println("Panda's name is " + pathInfo.substring(1));
        } else {
            resp.getWriter().println("Panda");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setStatus(HttpStatus.OK_200);

        // check if there is a name in parameters (form or query string)
        String name = req.getParameter("name");
        if (name != null && !name.equals("")) {
            resp.getWriter().println("Panda's name is " + name);
        } else {
            resp.getWriter().println("Panda");
        }

    }
}

