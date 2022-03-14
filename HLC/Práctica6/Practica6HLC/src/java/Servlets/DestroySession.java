package Servlets;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DestroySession extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"./Styles/index.css\" media=\"screen\" /></head>");
            HttpSession session = request.getSession();
            session.setAttribute("iniciada", false);

            out.print("<p>Sesión terminada.</p><br/>");
            out.print("<p><a href=\"index.jsp\">Iniciar otra sesión</a></p>");
            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
