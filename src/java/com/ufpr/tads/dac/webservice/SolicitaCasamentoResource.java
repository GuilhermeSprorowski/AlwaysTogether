package com.ufpr.tads.dac.webservice;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import com.ufpr.tads.dac.facade.PedidoCasamentoFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/casamento")
public class SolicitaCasamentoResource {

    @Context
    private UriInfo context;

    public SolicitaCasamentoResource() {
    }

    @GET
    @Path("/pedidoOrcamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PedidoCasamentoBean getOrcamento(@PathParam("id") String id) {
        System.out.println("getOrçamento: " + id);
        try {
            return PedidoCasamentoFacade.getPedidoCasamentoById(Integer.parseInt(id));
        } catch (PedidoCasamentoException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @POST
    @Path("/orcamento")
    @Produces(MediaType.APPLICATION_JSON)
    public void setPedido(PedidoCasamentoBean pc) {
        System.out.println("setPedido");
        try {
            PedidoCasamentoFacade.setPedidoCasamento(pc);
        } catch (PedidoCasamentoException ex) {
            System.out.println(ex);
        }
    }

    @PUT
    @Path("/respostaOrcamento")
    @Produces(MediaType.APPLICATION_JSON)
    public void setRespostaPedido(PedidoCasamentoBean pc) {
        System.out.println("setPedido");
        try {
            System.out.println(" adasd "+pc.getDataConfirmacao());
            PedidoCasamentoFacade.updateRespostaOrcamento(pc);
        } catch (PedidoCasamentoException ex) {
            System.out.println(ex);
        }
    }
}
