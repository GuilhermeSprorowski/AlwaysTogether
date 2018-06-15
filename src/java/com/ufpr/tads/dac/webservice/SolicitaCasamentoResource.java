package com.ufpr.tads.dac.webservice;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import com.ufpr.tads.dac.facade.PedidoCasamentoFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/casamento")
public class SolicitaCasamentoResource {

    @Context
    private UriInfo context;

    public SolicitaCasamentoResource() {
    }

    @GET
    @Path("/orcamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrcamento(@PathParam("id") String id) {
        System.out.println("getOrçamento: " + id);
        try {
            PedidoCasamentoBean pc = PedidoCasamentoFacade.getPedidoCasamentoById(Integer.parseInt(id));
            return Response.ok(pc).build();
        } catch (PedidoCasamentoException ex) {
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/orcamentoByClienteId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrcamentoByCliente(@PathParam("id") String id) {
        System.out.println("getOrçamento: " + id);
        try {
            GenericEntity<List<PedidoCasamentoBean>> pc = new GenericEntity<List<PedidoCasamentoBean>>(PedidoCasamentoFacade.getAllPedidoOrcamentoByClienteId(Integer.parseInt(id))){};
            System.out.println("Chegou!");
            return Response.ok(pc).build();
        } catch (PedidoCasamentoException ex) {
            return Response.serverError().build();
        }
    }
    
    @POST
    @Path("/orcamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setPedido(PedidoCasamentoBean pc) {
        System.out.println("setPedido");
        try {
            PedidoCasamentoFacade.setPedidoCasamento(pc);
            return Response.ok(true).build();
        } catch (PedidoCasamentoException ex) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/respostaOrcamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setRespostaPedido(PedidoCasamentoBean pc) {
        System.out.println("setPedido");
        try {
            PedidoCasamentoFacade.updateRespostaOrcamento(pc);
            return Response.ok(true).build();
        } catch (PedidoCasamentoException ex) {
            return Response.serverError().build();
        }
    }
}
