package Servlets;

import Dao.DeliveryManager;
import Dao.TransactionManager;
import Model.*;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;

public class Transaction extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Recuperamos el localizador del pedido de la petición
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"./Styles/transactions.css\" media=\"screen\" /></head>");
            String orderLoc = request.getParameter("orderLoc");

            // Con ese localizador obtenemos todas las transacciones en las que
            // aparece
            /* 
            Como mi tabla C no tiene imágenes, lo que voy a hacer es mostrar
            la imagen de la empresa del repartidor que esté a cargo de la 
            transacción.
             */
            HttpSession session = request.getSession();
            ArrayList<Shops> t = TransactionManager.getTransactionsOfOrder(orderLoc);
            if (t != null) {
                out.print("<h1>Transacciones de la orden " + orderLoc + "</h1>");
                out.print("<table border=\"solid black 3px\">"
                        + "<tr>"
                        + "<th>ID</th>"
                        + "<th>Nombre de Tienda</th>"
                        + "<th>Gastos de Envío</th>"
                        + "<th>Empresa del Repartidor</th>"
                        + "</tr>");
                for (Shops s : t) {
                    out.print("<tr>");
                    out.print("<td>" + s.getId() + "</td>");
                    out.print("<td>" + s.getShopName() + "</td>");
                    out.print("<td>" + s.getDelCosts() + "</td>");
                    out.print("<td><img src=\"Images/" + DeliveryManager.getPicOf(s.getId().getDelCod()) + ".png\"></td>");
                    out.print("</tr>");
                }
                out.print("</table>");
            }

            out.print("<br/><a href='profile.jsp'>Ir a mi perfil</a><br/>");

            // Salir
            out.print("<form action=\"destroySession\">");
            out.print("<input class=\"exit\" type=\"submit\" value=\"Salir\" />");
            out.print("</form>");

            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
