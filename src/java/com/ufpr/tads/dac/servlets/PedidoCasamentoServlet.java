
package com.ufpr.tads.dac.servlets;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import com.ufpr.tads.dac.facade.PedidoCasamentoFacade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "PedidoCasamentoServlet", urlPatterns = {"/PedidoCasamentoServlet"})
public class PedidoCasamentoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        FuncionarioBean login = (FuncionarioBean) session.getAttribute("funcionario");
        if (login == null) {
            //envia para fazer login
            request.setAttribute("msg", "É necessario esta logado para acessar essa pagina");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            try {                
                PedidoCasamentoFacade.updatePedidoCasamentoBean(new PedidoCasamentoBean(request.getParameter("idPedido") == null?0:Integer.parseInt(request.getParameter("idPedido")),
                   request.getParameter("vlrPedido") == null?0:Float.parseFloat(request.getParameter("vlrPedido")), request.getParameter("itensOrcamento")), login.getFuncionarioId());
                request.getRequestDispatcher("/HomeServlet").forward(request, response);
            } catch (PedidoCasamentoException ex) {
                request.setAttribute("msg", ex);
                request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
            }

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