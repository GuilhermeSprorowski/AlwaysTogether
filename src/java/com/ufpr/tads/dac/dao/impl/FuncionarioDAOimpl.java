
package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.FuncionarioDAO;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        }finally {if (pst != null) {try { 
                    rs.close();
                    pst.close();
                    con.close();} catch (SQLException ex) {throw new FuncionarioException("Erro user: erro ao fechar conecxão");}}
        }  
    }
    
    @Override
    public void setFuncionario(FuncionarioBean f) throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int resp = 0;
        try {
            pst = con.prepareStatement("INSERT INTO alwaystogether.funcionario (nome, cpf, senha, codigo, dataNasc, adm, email) VALUES(?,?,?,?,?,?,?);");
            pst.setString(1, f.getNome());
            pst.setString(2, f.getCpf());

            MessageDigest md;
            String senhaMd5 = "";
            try {
                md = MessageDigest.getInstance("MD5");
                try {
                    md.update(f.getSenha().getBytes("UTF8"));
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(FuncionarioDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte s[] = md.digest();
                for (int i = 0; i < s.length; i++) {
                    senhaMd5 += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
                }
            } catch (NoSuchAlgorithmException ex) {
                throw new FuncionarioException("Erro funcionario: erro md5");
            }
            pst.setString(3, senhaMd5);
            pst.setInt(4, f.getCodigo());               
            pst.setTimestamp(5, new Timestamp(f.getDataNasc().getTime()));   
            System.out.println(f.isAdm());
            pst.setBoolean(6, f.isAdm());
            pst.setString(7, f.getEmail());
            resp = pst.executeUpdate();
            if (resp == 0) {
                throw new FuncionarioException("Erro funcionario: não foi possivel criar esse funcionario");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new FuncionarioException("Erro funcionario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new FuncionarioException("Erro funcionario: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void updateFuncionario(FuncionarioBean f) throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE alwaystogether.funcionario SET nome = ?, cpf = ?, adm = ?, codigo = ?,\n"
                        + "dataNasc = ? WHERE id = ?");
            pst.setString(1, f.getNome());
            pst.setString(2, f.getCpf());
            pst.setBoolean(3, f.isAdm());          
            pst.setInt(4, f.getCodigo());  
            pst.setDate(5, new Date(f.getDataNasc().getTime()));   
            pst.setInt(6, f.getFuncionarioId());
            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new FuncionarioException("Erro funcionario: não foi possivel criar esse funcionario");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new FuncionarioException("Erro funcionario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new FuncionarioException("Erro funcionario: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void deleteFuncionario(int idFuncionario) throws FuncionarioException {
        PreparedStatement pst = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE alwaystogether.funcionario u SET dataExcluido = ? WHERE id = ?");
            pst.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            pst.setInt(2, idFuncionario);
            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new FuncionarioException("Erro funcionario: erro na tentativa de exclusão do funcionario");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new FuncionarioException("Erro funcionario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new FuncionarioException("Erro funcionario: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public FuncionarioBean getFuncionarioById(int idFuncionario) throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT funcionario.id, funcionario.nome, cpf, dataNasc, adm, codigo, email FROM alwaystogether.funcionario\n"
                    + "WHERE dataDemissao IS NULL AND dataExcluido IS NULL AND funcionario.id = ?");
            pst.setInt(1, idFuncionario);
            rs = pst.executeQuery();
            while (rs.next()) {
                return new FuncionarioBean(rs.getInt("id"), rs.getString("nome"),
                        rs.getDate("dataNasc"), rs.getBoolean("adm"), rs.getString("cpf"), rs.getInt("codigo"), rs.getString("email"));
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            throw new FuncionarioException("Erro Funcionario: Comando SQL invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new FuncionarioException("Erro Funcionario: Falha ao tentar fechar conexão!");
                }
            }
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

    @Override
    public ArrayList<FuncionarioBean> getAllFuncionarios() throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<FuncionarioBean> al = new ArrayList<FuncionarioBean>();
        try {
            con = new ConnectionFactory().getConnection();
            
            pst = con.prepareStatement("SELECT * FROM alwaystogether.funcionario WHERE dataDemissao IS NULL AND dataExcluido IS NULL");
            rs = pst.executeQuery();            
            while(rs.next()) {
                al.add(new FuncionarioBean(rs.getInt("id"),rs.getString("email"),rs.getString("nome"),rs.getString("cpf"), rs.getDate("dataNasc"),rs.getInt("codigo")));
            }            
            if (al.isEmpty()) {
                throw new FuncionarioException("Erro Funcionario: Falha ao procurar estes funcionarios");
            }
            return al;
        } catch (SQLException e) {
            System.out.println(e);
           throw new FuncionarioException("Erro Funcionario: Comando SQL invalido");
        } finally {
            if (pst!= null) {try {
                    rs.close();
                    pst.close();
                    con.close(); } catch (SQLException ex) {throw new FuncionarioException("Erro Funcionario: Falha ao tentar fechar conexão!");}}
        }
    }
    
}
