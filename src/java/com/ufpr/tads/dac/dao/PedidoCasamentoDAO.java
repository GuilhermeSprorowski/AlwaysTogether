package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import java.util.ArrayList;

public interface PedidoCasamentoDAO {

    public void setNovoPedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException;
    public void updatePedidoCasamento(PedidoCasamentoBean pc, int funcId) throws PedidoCasamentoException;
    public void updateResposta(PedidoCasamentoBean pc) throws PedidoCasamentoException;
    public ArrayList<PedidoCasamentoBean> getAllPedidos() throws PedidoCasamentoException;  
    public ArrayList<PedidoCasamentoBean> getAllPedidosNaoOrcados() throws PedidoCasamentoException;  
    public ArrayList<PedidoCasamentoBean> getAllPedidoOrcamentoByClienteId(int id) throws PedidoCasamentoException;      
    public PedidoCasamentoBean getPedidoCasamentoByOrcamentoId(int pedidoOrcamentoId) throws PedidoCasamentoException;
    public ArrayList<PedidoCasamentoBean> getPedidoCasamentoByPedidoId(int pedidoCasamentoId) throws PedidoCasamentoException;
}
