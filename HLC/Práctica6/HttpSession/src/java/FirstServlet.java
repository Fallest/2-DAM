
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String n = request.getParameter("userName");
            out.print("Welcome " + n + " ");

            //public HttpSession getSession():Returns the current session associated with this request, or if the request does not have a session, creates one.
            HttpSession session = request.getSession();
            session.setAttribute("uname", n);
            session.setAttribute("iniciada", true);

            out.print("<a href='servlet2'>visit</a>");

            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
