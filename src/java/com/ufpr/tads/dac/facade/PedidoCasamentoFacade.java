package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.dao.impl.PedidoCasamentoDAOimpl;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;


public class PedidoCasamentoFacade {
    static PedidoCasamentoDAOimpl PedidoCasamentoDAO = new PedidoCasamentoDAOimpl();
    public static void setPedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException{
        PedidoCasamentoDAO.setNovoPedidoCasamento(pc);
    }
    public static PedidoCasamentoBean getPedidoCasamentoById(int pedidoId) throws PedidoCasamentoException{
        return PedidoCasamentoDAO.getPedidoCasamentoById(pedidoId);
    }
    public static void updateRespostaOrcamento(PedidoCasamentoBean pc) throws PedidoCasamentoException{
        PedidoCasamentoDAO.updatePedidoCasamento(pc);
    }
    
}
