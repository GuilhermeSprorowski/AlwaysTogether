package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.PedidoCasamentoDAO;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoCasamentoDAOimpl implements PedidoCasamentoDAO {

    private Connection con;

    @Override
    public void setNovoPedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        System.out.println("setNovoPedidoCasamento Always");
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("INSERT INTO alwaystogether.pedido(codCliente, codConjuge, numConvidados, padre, padrinho1, padrinho2, madrinha1,madrinha2, igreja, dataCasamento, locallua)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            pst.setInt(1, pc.getSolicitante());
            pst.setInt(2, pc.getConjuge());
            pst.setInt(3, pc.getnConvidados());
            pst.setString(4, pc.getPadre());
            pst.setString(5, pc.getPadrinho1());
            pst.setString(6, pc.getPadrinho2());
            pst.setString(7, pc.getMadrinha1());
            pst.setString(8, pc.getMadrinha2());
            pst.setString(9, pc.getIgreja());   
            pst.setDate(10, new java.sql.Date(pc.getDataCasamento().getTime()));
            pst.setString(11, pc.getLocalLua());              
            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new PedidoCasamentoException("Erro Pedido casamento: Problema ao Salvar pedido");
            }
        } catch (SQLException e) {
            throw new PedidoCasamentoException("Erro Pedido casamento: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void updatePedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE alwaystogether.pedido SET dataResposta = ?, aceito = ? WHERE id = ?;");
            pst.setDate(1,  new java.sql.Date(pc.getDataConfirmacao().getTime()));
            pst.setBoolean(2, pc.isAceito());   
             pst.setInt(3, pc.getIdPedido());
             System.out.println(pst);
            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new PedidoCasamentoException("Erro Pedido casamento: Problema ao Salvar reposta");
            }
        } catch (SQLException e) {
            throw new PedidoCasamentoException("Erro Pedido casamento: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public ArrayList<PedidoCasamentoBean> getAllPedidosNaoOrcados() throws PedidoCasamentoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PedidoCasamentoBean getPedidoCasamentoById(int pedidoCasamentoId) throws PedidoCasamentoException {
         PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT * FROM alwaystogether.pedido WHERE id = ?;");      
            pst.setInt(1, pedidoCasamentoId);
            System.out.println(pst);
            rs = pst.executeQuery();
            PedidoCasamentoBean u = null;
            while (rs.next()) {
                u = new PedidoCasamentoBean(rs.getInt("id"),rs.getInt("codConjuge"),rs.getInt("codCliente"), rs.getInt("numConvidados"),
                rs.getString("padre"),rs.getString("igreja"),rs.getString("locallua"),rs.getString("padrinho1"),rs.getString("padrinho2"),
                rs.getString("madrinha1"), rs.getString("madrinha2"),rs.getString("itensOrcamento"));                
            }
            System.out.println("Terminou");
            return u;
        } catch (SQLException e) {
            throw new PedidoCasamentoException("Erro Usuario: comando sql invalido");
        }finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new PedidoCasamentoException("Erro user: erro ao fechar conecxão");}}
        } 
    }

}