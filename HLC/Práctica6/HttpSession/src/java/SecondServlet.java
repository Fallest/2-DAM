
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SecondServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            //public HttpSession getSession(boolean create):Returns the current HttpSession associated with this request or, if there is no current session and create is true, returns a new session.
            HttpSession session = request.getSession(false);
            if (session == null) {
                out.print("Error en la sesión ");
            }

            String n = (String) session.getAttribute("uname");
            Boolean ini = (Boolean) session.getAttribute("iniciada");
            if (ini) {
                out.print("Hello " + n);
            } else {
                out.print("Error sesión no iniciada " + n);
            }

            //public String getId():Returns a string containing the unique identifier value.
            out.print("<p>El <b>ID</b> de la sesion es " + session.getId() + "</p>");
            //public long getCreationTime():Returns the time when this session was created, measured in milliseconds since midnight January 1, 1970 GMT.
            out.print("<p>The <b>time</b> of session: " + session.getCreationTime() + "</p>");
            //public long getLastAccessedTime():Returns the last time the client sent a request associated with this session, as the number of milliseconds since midnight January 1, 1970 GMT.
            out.print("<p>The <b>last time</b> of session (refresh): " + session.getLastAccessedTime() + "</p>");

            //public void invalidate():Invalidates this session then unbinds any objects bound to it.
////                Descomentar para probar
//                out.print("<p>Sessión invalidada. Refresca</p>");
//                session.invalidate();
            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
