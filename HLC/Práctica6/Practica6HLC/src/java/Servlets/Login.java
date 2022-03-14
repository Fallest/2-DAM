package Servlets;

import Dao.ClientManager;
import Model.Clients;
import java.io.*;
import javax.servlet.http.*;

/**
 * Clase para controlar el inicio de sesión. Los datos se reciben por GET.
 * Tenemos que comprobar que el par usuario-contraseña existe entre los clientes
 * que hay en la BD.
 */
public class Login extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"./Styles/index.css\" media=\"screen\" /></head>");
            String usr = request.getParameter("userName");
            String pwd = request.getParameter("pwd");

            HttpSession session = request.getSession();
            Clients c = ClientManager.tryLogin(usr, pwd);
            if (c != null) {
                // Si se ha encontrado un cliente con esos credenciales
                out.print("Bienvenido a DaviiExpress, " + c.getNif());
                session.setAttribute("uname", usr);
                session.setAttribute("udata", c);
                session.setAttribute("iniciada", true);
                
                out.print("<br/><a href='profile.jsp'>Ir a mi perfil</a><br/>");

                // Salir
                out.print("<form action=\"destroySession\">");
                out.print("<input class=\"exit\" type=\"submit\" value=\"Salir\" />");
                out.print("</form>");
            } else {
                // Si no existen esos credenciales
                out.print("<error>Credenciales incorrectos.</error><br/>");
                out.print("Por favor, inténtelo de nuevo.</br>");
                out.print("<a href=\"index.jsp\">Volver</a>");
            }

            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
