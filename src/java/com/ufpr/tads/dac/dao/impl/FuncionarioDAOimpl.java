
package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.FuncionarioDAO;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FuncionarioDAOimpl implements FuncionarioDAO{

    private Connection con;
    @Override
    public FuncionarioBean getFuncionarioLogin(String login, String senha) throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT id, email, nome, adm, codigo FROM alwaystogether.funcionario\n" +
                        "WHERE (email = ?) AND (senha = ?);");      
            pst.setString(1, login);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            FuncionarioBean u = null;
            while (rs.next()) {
                u = new FuncionarioBean(rs.getInt("id"),rs.getString("email"),rs.getString("nome"),rs.getBoolean("adm"),rs.getInt("codigo"));
            }
            return u;
        } catch (SQLException e) {
            throw new FuncionarioException("Erro Usuario: comando sql invalido");
        }finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new FuncionarioException("Erro user: erro ao fechar conecxão");}}
        }  
    }

    @Override
    public void setSenha(String login, String senhaAntiga, String novaSenha) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(String login, String senha) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmailDisponivel(String email) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
