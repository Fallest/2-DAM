/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pqServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bosque
 */
public class ServletDatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletDatos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletDatos at " + request.getContextPath () + "</h1>");
            out.print("<p>Servidor: "+ request.getServerName()+":");
            out.println(request.getServerPort()+"</p>");
            out.print("<p>cliente: "+ request.getRemoteHost()+" ");
            out.println(request.getRemoteAddr()+"</p>");
            out.println("<p>Protocolo: "+ request.getProtocol()+"</p>");
            out.println("<p>Request URI: "+ request.getRequestURI()+"</p>");
            out.println("<p>Request URL: "+ request.getRequestURL()+"</p>");
            out.println("<p>Request Method: "+ request.getMethod()+"</p>");
            // Enlace al index.html. Para meter la dobles comillas en una cadesa se precede del caracter \
            out.println("<p>Pulsa <a href=\"index.html\"> aquí</a> para volver</p>");
            out.println("</body>");
            out.println("</html>");         
            out.close(); 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
