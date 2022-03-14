<%@page import="Dao.OrderManager"%>
<%@page import="Model.Orders"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Clients"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DaviiExpress: Perfil de Cliente</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        11
        <link rel="stylesheet" type="text/css" href="./Styles/profile.css" media="screen" />
    </head>
    <body>
        <!-- Título -->
        <div>
            <h1>Perfil</h1>
        </div>
        <!-- Mostrar los datos del cliente (tabla A)-->
        <%
            HttpSession s = request.getSession(false);
            Clients cli = (Clients) s.getAttribute("udata");
        %>
        <article>
            <img src="${pageContext.request.contextPath}/Images/<%=cli.getPic()%>.jpg"> <br/>
            <h4>NIF:                <%=cli.getNif()%></h4>
            <p>Fecha de Registro:   <%=cli.getRegDate()%></p>
            <p>Dirección:           <%=cli.getDir()%></p>
        </article>

        <!-- Datos de los pedidos del cliente (tabla B) -->
        <div>
            <h2>Pedidos</h2>
            <%
                ArrayList<Orders> pedidos = OrderManager.getOrdersOf(cli);
                out.print("<table>"
                        + "<tr>"
                        + "<th>Localizador</th>"
                        + "<th>Cliente</th>"
                        + "<th>Precio</th>"
                        + "</tr>");
                for (Orders o : pedidos) {

                    out.print("<tr>");
                    out.print("<td>" + o.getLoc() + "</td>");
                    out.print("<td>" + o.getNifCli() + "</td>");
                    out.print("<td>" + o.getPrice() + "</td>");
                    out.print("</tr>");
                }
                out.print("</table>");

                // Formulario para ver las transacciones
                out.print("<form action=\"transaction\">");
                out.print("Ver transacciones (Usar localizador de pedido): <input type=\"text\" name=\"orderLoc\"/>");
                out.print("<input type=\"submit\" value=\"Ver\"");
                out.print("</form>");
            %>
        </div>

        <!-- Salir -->
        <form action="destroySession">
            <input class="exit" type="submit" value="Salir" />
        </form>
    </body>
</html>

