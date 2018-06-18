/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.servlets;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author MURILO
 */
@WebServlet(name = "GeradorRelatorio", urlPatterns = {"/GeradorRelatorio"})
public class GeradorRelatorio extends HttpServlet {

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

            Connection con = null;
            int  acao = 1;
            String jasper = "";
            HashMap params = new HashMap();
            String host = "http://" + request.getServerName()
                    + ":" + request.getServerPort();
            if(request.getParameter("acao")!= null)
            acao = Integer.parseInt(request.getParameter("acao"));
            if (acao == 1) {
                //url do report
                jasper = request.getContextPath() + "/jasper/RelatorioSinteticoOrcamento.jasper";
                // url subreport
                String subreport = request.getContextPath() + "/jasper/qntdorcada.jasper";
                params.put("qntdorcada", host + subreport); 
                subreport = request.getContextPath() + "/jasper/qntdpedido.jasper";
                params.put("qntdpedido", host + subreport);  
            } else {
                //url do report
                jasper = request.getContextPath() + "/jasper/RelatorioFinanceiroOrcamento.jasper";

            } 
            try {
                con = new ConnectionFactory().getConnection();
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

                if (bytes != null) {
// A página será mostrada em PDF
                    response.setContentType("application/pdf");
// Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (SQLException e) {
                System.out.println(e);
                request.setAttribute("mensagem", "Erro de conexão ou query: "
                        + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (JRException e) {
                System.out.println(e);
                request.setAttribute("mensagem", "Erro no Jasper : "
                        + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }

            }
        } // Fechamento do processRequest

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
