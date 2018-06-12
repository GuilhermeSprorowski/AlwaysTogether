package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.dao.impl.PedidoCasamentoDAOimpl;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import java.util.ArrayList;


public class PedidoCasamentoFacade {
    static PedidoCasamentoDAOimpl PedidoCasamentoDAO = new PedidoCasamentoDAOimpl();
    public static void setPedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException{
        PedidoCasamentoDAO.setNovoPedidoCasamento(pc);
    }
    public static PedidoCasamentoBean getPedidoCasamentoById(int pedidoId) throws PedidoCasamentoException{
        return PedidoCasamentoDAO.getPedidoCasamentoById(pedidoId);
    }
    public static void updateRespostaOrcamento(PedidoCasamentoBean pc) throws PedidoCasamentoException{
        PedidoCasamentoDAO.updateResposta(pc);
    }
    public static void updatePedidoCasamentoBean(PedidoCasamentoBean pc, int idFunc) throws PedidoCasamentoException{
        PedidoCasamentoDAO.updatePedidoCasamento(pc, idFunc);
    }

    public static ArrayList<PedidoCasamentoBean> getAllPedidoOrcamento() throws PedidoCasamentoException{
        return PedidoCasamentoDAO.getAllPedidosNaoOrcados();
    }
    
}
