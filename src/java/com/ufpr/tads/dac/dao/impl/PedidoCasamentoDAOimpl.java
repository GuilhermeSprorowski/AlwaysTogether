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
import java.util.Date;

public class PedidoCasamentoDAOimpl implements PedidoCasamentoDAO {

    private Connection con;

    @Override
    public void setNovoPedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        System.out.println("setNovoPedidoCasamento Always");
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("INSERT INTO alwaystogether.pedido(codPedido4ever,codCliente, codConjuge, numConvidados, padre, padrinho1, padrinho2, madrinha1,madrinha2, igreja, dataCasamento, locallua, Premium, nomeCliente, nomeConjuge)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            pst.setInt(1, pc.getIdPedido());
            pst.setInt(2, pc.getSolicitante());
            pst.setInt(3, pc.getConjuge());
            pst.setInt(4, pc.getnConvidados());
            pst.setString(5, pc.getPadre());
            pst.setString(6, pc.getPadrinho1());
            pst.setString(7, pc.getPadrinho2());
            pst.setString(8, pc.getMadrinha1());
            pst.setString(9, pc.getMadrinha2());
            pst.setString(10, pc.getIgreja());
            pst.setDate(11, new java.sql.Date(pc.getDataCasamento().getTime()));
            pst.setString(12, pc.getLocalLua());
            pst.setBoolean(13, true);
            pst.setString(14, pc.getNomeCliente());
            pst.setString(15, pc.getNomeConjuge());
            
            int resp = pst.executeUpdate();
            pst.setBoolean(13, false);
            resp = pst.executeUpdate();
            if (resp == 0) {
                throw new PedidoCasamentoException("Erro Pedido casamento: Problema ao Salvar pedido");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PedidoCasamentoException("Erro Pedido casamento: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void updateResposta(PedidoCasamentoBean pc) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE alwaystogether.pedido SET dataResposta = ?, aceito = ? WHERE id = ?;");
            pst.setDate(1, new java.sql.Date(pc.getDataConfirmacao().getTime()));
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
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public ArrayList<PedidoCasamentoBean> getAllPedidosNaoOrcados() throws PedidoCasamentoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<PedidoCasamentoBean> al = new ArrayList<PedidoCasamentoBean>();
        try {
            con = new ConnectionFactory().getConnection();

            pst = con.prepareStatement("SELECT * FROM alwaystogether.pedido WHERE itensOrcamento IS NULL");
            rs = pst.executeQuery();
            while (rs.next()) {
                al.add(new PedidoCasamentoBean(rs.getInt("id"), rs.getInt("codConjuge"), rs.getString("nomeConjuge"), rs.getString("nomeCliente"), rs.getInt("codCliente"), rs.getInt("numConvidados"),
                        rs.getString("padre"), rs.getString("igreja"), rs.getString("locallua"), rs.getString("padrinho1"), rs.getString("padrinho2"),
                        rs.getString("madrinha1"), rs.getString("madrinha2"), rs.getString("itensOrcamento"), rs.getBoolean("Premium"), rs.getFloat("valortotal")));
            }
            if (al.isEmpty()) {
                throw new PedidoCasamentoException("Erro pedido casamento: Falha ao procurar os pedidos");
            }
            return al;
        } catch (SQLException e) {
            System.out.println(e);
            throw new PedidoCasamentoException("Erro pedido casamento: Comando SQL invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: Falha ao tentar fechar conexão!");
                }
            }
        }
    }

    @Override
    public PedidoCasamentoBean getPedidoCasamentoByOrcamentoId(int pedidoOrcamentoId) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT * FROM alwaystogether.pedido WHERE id = ?;");
            pst.setInt(1, pedidoOrcamentoId);
            System.out.println(pst);
            rs = pst.executeQuery();
            PedidoCasamentoBean u = null;
            while (rs.next()) {
                u = new PedidoCasamentoBean(rs.getInt("id"), rs.getInt("codConjuge"), rs.getString("nomeConjuge"), rs.getString("nomeCliente"), rs.getInt("codCliente"), rs.getInt("numConvidados"),
                        rs.getString("padre"), rs.getString("igreja"), rs.getString("locallua"), rs.getString("padrinho1"), rs.getString("padrinho2"),
                        rs.getString("madrinha1"), rs.getString("madrinha2"), rs.getString("itensOrcamento"), rs.getBoolean("Premium"), rs.getFloat("valortotal"));
            }
            return u;
        } catch (SQLException e) {
            throw new PedidoCasamentoException("Erro Usuario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro user: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void updatePedidoCasamento(PedidoCasamentoBean pc, int funcId) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE alwaystogether.pedido SET codFuncionario = ?, itensOrcamento = ?, valortotal = ? WHERE id = ?");
            pst.setInt(1, funcId);
            pst.setString(2, pc.getItensOrcamento());
            pst.setFloat(3, pc.getVlrTotal());
            pst.setInt(4, pc.getIdOrcamento());
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
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public ArrayList<PedidoCasamentoBean> getAllPedidoOrcamentoByClienteId(int id) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<PedidoCasamentoBean> al = new ArrayList<PedidoCasamentoBean>();
        try {
            con = new ConnectionFactory().getConnection();

            pst = con.prepareStatement("SELECT * FROM alwaystogether.pedido WHERE codCliente = ?");
            pst.setInt(1, id);
            System.out.println(pst);
            rs = pst.executeQuery();
            while (rs.next()) {
                al.add(new PedidoCasamentoBean(rs.getInt("id"), rs.getInt("codConjuge"), rs.getString("nomeConjuge"), rs.getString("nomeCliente"), rs.getInt("codCliente"), rs.getInt("numConvidados"),
                        rs.getString("padre"), rs.getString("igreja"), rs.getString("locallua"), rs.getString("padrinho1"), rs.getString("padrinho2"),
                        rs.getString("madrinha1"), rs.getString("madrinha2"), rs.getString("itensOrcamento"), rs.getBoolean("Premium"), rs.getFloat("valortotal"), rs.getBoolean("aceito")));
            }
            
            return al;
        } catch (SQLException e) {
            System.out.println(e);
            throw new PedidoCasamentoException("Erro pedido casamento: Comando SQL invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: Falha ao tentar fechar conexão!");
                }
            }
        }
    }

    @Override
    public ArrayList<PedidoCasamentoBean> getAllPedidos() throws PedidoCasamentoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<PedidoCasamentoBean> al = new ArrayList<PedidoCasamentoBean>();
        try {
            con = new ConnectionFactory().getConnection();

            pst = con.prepareStatement("SELECT s1.*\n" +
                "FROM pedido s1\n" +
                "LEFT JOIN pedido s2 ON s1.aceito = s2.aceito\n" +
                "GROUP BY codPedido4ever\n" +
                "ORDER BY itensOrcamento desc");
            rs = pst.executeQuery();
            while (rs.next()) {
                PedidoCasamentoBean ped = new PedidoCasamentoBean(rs.getInt("codPedido4ever"), rs.getInt("id"), rs.getInt("codConjuge"), rs.getString("nomeConjuge"), rs.getString("nomeCliente"), rs.getInt("codCliente"), rs.getInt("numConvidados"),
                        rs.getString("padre"), rs.getString("igreja"), rs.getString("locallua"), rs.getString("padrinho1"), rs.getString("padrinho2"),
                        rs.getString("madrinha1"), rs.getString("madrinha2"), rs.getString("itensOrcamento"), rs.getBoolean("Premium"), rs.getFloat("valortotal"));

                ped.setDataSolicitacao(rs.getDate("dataSolicitacao") == null ? null : rs.getDate("dataSolicitacao"));
                ped.setDataConfirmacao(rs.getDate("dataResposta") == null ? null : rs.getDate("dataResposta"));
                ped.setDataCasamento(rs.getDate("dataCasamento") == null ? null : rs.getDate("dataCasamento"));
                ped.setAceito(rs.getBoolean("aceito"));
                al.add(ped);
            }
            return al;
        } catch (SQLException e) {
            System.out.println(e);
            throw new PedidoCasamentoException("Erro pedido casamento: Comando SQL invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro pedido casamento: Falha ao tentar fechar conexão!");
                }
            }
        }
    }

    @Override
    public ArrayList<PedidoCasamentoBean> getPedidoCasamentoByPedidoId(int pedidoCasamentoId) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT * FROM alwaystogether.pedido WHERE codPedido4ever = ?;");
            pst.setInt(1, pedidoCasamentoId);
            System.out.println(pst);
            rs = pst.executeQuery();
            ArrayList<PedidoCasamentoBean> u = new ArrayList<>();
            while (rs.next()) {
                PedidoCasamentoBean p = new PedidoCasamentoBean(rs.getInt("codPedido4ever"), rs.getInt("id"), rs.getInt("codConjuge"), rs.getString("nomeConjuge"), rs.getString("nomeCliente"), rs.getInt("codCliente"), rs.getInt("numConvidados"),
                        rs.getString("padre"), rs.getString("igreja"), rs.getString("locallua"), rs.getString("padrinho1"), rs.getString("padrinho2"),
                        rs.getString("madrinha1"), rs.getString("madrinha2"), rs.getString("itensOrcamento"), rs.getBoolean("Premium"), rs.getFloat("valortotal"));
                        
                p.setDataSolicitacao(rs.getDate("dataSolicitacao") == null ? null : rs.getDate("dataSolicitacao"));
                p.setDataConfirmacao(rs.getDate("dataResposta") == null ? null : rs.getDate("dataResposta"));
                p.setDataCasamento(rs.getDate("dataCasamento") == null ? null : rs.getDate("dataCasamento"));
                p.setAceito(rs.getBoolean("aceito"));
                u.add(p);
            }
            return u;
        } catch (SQLException e) {
            throw new PedidoCasamentoException("Erro Usuario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro user: erro ao fechar conecxão");
                }
            }
        }
    }

}
