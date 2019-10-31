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
        String pandaName = req.getPathInfo(); // either null or /name

        if (pandaName != null) {
            pandaName = pandaName.substring(1); // remove the / at the beginning
        }

        writeResponse(resp, pandaName);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setStatus(HttpStatus.OK_200);

        // check if there is a name in parameters (form or query string)
        String pandaName = req.getParameter("name");

        writeResponse(resp, pandaName);

    }

    private void writeResponse(HttpServletResponse resp, String pandaName) throws IOException {

        if (pandaName != null && !pandaName.equals("")) {
            resp.getWriter().println("Panda's name is " + pandaName);
        } else {
            resp.getWriter().println("Panda");
        }

    }
}

