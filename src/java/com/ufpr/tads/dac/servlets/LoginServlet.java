package com.ufpr.tads.dac.servlets;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.facade.FuncionarioFacade;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String senha = request.getParameter("inputPassword");
        try {
            MessageDigest md;
            String senhaMd5 = "";
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(senha.getBytes("UTF8"));
                byte s[] = md.digest();
                for (int i = 0; i < s.length; i++) {
                    senhaMd5 += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //confere Login
            FuncionarioBean funcionario = FuncionarioFacade.getFuncionarioByLogin(email, senhaMd5);
            if (funcionario != null) {
                System.err.println("funcionario valido");
                //USUARIO VALIDO
                HttpSession session= request.getSession();
                session.setAttribute("funcionario", funcionario);
                request.getRequestDispatcher("/HomeServlet").forward(request, response);
            }else{
                System.out.println("funcionario invalido");
                request.setAttribute("msg", "Usuario Invalido");
                request.getRequestDispatcher("index.jsp").forward(request, response);            
            }
        } catch (Exception e) {
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
