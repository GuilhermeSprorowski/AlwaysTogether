package com.ufpr.tads.dac.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoCasamentoBean implements Serializable {

    private int idPedido;
    private int idOrcamento;
    private Date dataConfirmacao;
    private boolean aceito;
    private Date dataSolicitacao;
    private Date dataCasamento;
    private int conjuge;
    private float vlrTotal;
    private String nomeConjuge;
    private String nomeCliente;
    private int solicitante;
    private int nConvidados;
    private String padre;
    private String igreja;
    private String localLua;
    private String padrinho1;
    private String padrinho2;
    private String madrinha1;
    private String madrinha2;
    private String itensOrcamento;
    private String nomeFunc;
    private boolean premium;

    public PedidoCasamentoBean(int idPedido, int conjuge, String nomeConjuge, String nomeCliente, int solicitante, int nConvidados, String padre, String igreja, String localLua, String padrinho1, String padrinho2, String madrinha1, String madrinha2, String itensOrcamento, boolean premium, float vlrTotal) {
        this.idPedido = idPedido;
        this.conjuge = conjuge;
        this.nomeConjuge = nomeConjuge;
        this.nomeCliente = nomeCliente;
        this.solicitante = solicitante;
        this.nConvidados = nConvidados;
        this.padre = padre;
        this.igreja = igreja;
        this.localLua = localLua;
        this.padrinho1 = padrinho1;
        this.padrinho2 = padrinho2;
        this.madrinha1 = madrinha1;
        this.madrinha2 = madrinha2;
        this.itensOrcamento = itensOrcamento;
        this.premium = premium;
        this.vlrTotal = vlrTotal;
    }

    public PedidoCasamentoBean(int idPedido, int idOrcamento, int conjuge, String nomeConjuge, String nomeCliente, int solicitante, int nConvidados, String padre, String igreja, String localLua, String padrinho1, String padrinho2, String madrinha1, String madrinha2, String itensOrcamento, boolean premium, float vlrTotal) {
        this.idPedido = idPedido;
        this.idOrcamento = idOrcamento;
        this.conjuge = conjuge;
        this.nomeConjuge = nomeConjuge;
        this.nomeCliente = nomeCliente;
        this.solicitante = solicitante;
        this.nConvidados = nConvidados;
        this.padre = padre;
        this.igreja = igreja;
        this.localLua = localLua;
        this.padrinho1 = padrinho1;
        this.padrinho2 = padrinho2;
        this.madrinha1 = madrinha1;
        this.madrinha2 = madrinha2;
        this.itensOrcamento = itensOrcamento;
        this.premium = premium;
        this.vlrTotal = vlrTotal;
    }

    public PedidoCasamentoBean(int idPedido, float vlrTotal, String itensOrcamento) {
        this.idPedido = idPedido;
        this.vlrTotal = vlrTotal;
        this.itensOrcamento = itensOrcamento;
    }

    public PedidoCasamentoBean() {
    }

    public boolean isAceito() {
        return aceito;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public boolean ispremium() {
        return premium;
    }

    public void setpremium(boolean premium) {
        this.premium = premium;
    }
    
        public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public float getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(float vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getItensOrcamento() {
        return itensOrcamento;
    }

    public void setItensOrcamento(String itensOrcamento) {
        this.itensOrcamento = itensOrcamento;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setDataConfirmacao(Date dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void setDataCasamento(Date dataCasamento) {
        this.dataCasamento = dataCasamento;
    }

    public int getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(int solicitante) {
        this.solicitante = solicitante;
    }

    public void setDataConfirmacao(String dataConfirmacao) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataConfirmacao);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataConfirmacao = data;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataSolicitacao);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataSolicitacao = data;
    }

    public void setDataCasamento(String dataCasamento) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataCasamento);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataCasamento = data;
    }

    public void setConjuge(int conjuge) {
        this.conjuge = conjuge;
    }

    public void setnConvidados(int nConvidados) {
        this.nConvidados = nConvidados;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public void setIgreja(String igreja) {
        this.igreja = igreja;
    }

    public void setLocalLua(String localLua) {
        this.localLua = localLua;
    }

    public void setPadrinho1(String padrinho1) {
        this.padrinho1 = padrinho1;
    }

    public void setPadrinho2(String padrinho2) {
        this.padrinho2 = padrinho2;
    }

    public void setMadrinha1(String madrinha1) {
        this.madrinha1 = madrinha1;
    }

    public void setMadrinha2(String madrinha2) {
        this.madrinha2 = madrinha2;
    }

    public Date getDataConfirmacao() {
        return dataConfirmacao;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public Date getDataCasamento() {
        return dataCasamento;
    }

    public String getDataConfirmacaoS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(dataConfirmacao);
    }

    public String getDataSolicitacaoS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(dataSolicitacao);
    }

    public String getDataCasamentoS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy yyyy-MM-dd HH:mm:ss");
        return fmt.format(dataCasamento);
    }

    public int getConjuge() {
        return conjuge;
    }

    public int getnConvidados() {
        return nConvidados;
    }

    public String getPadre() {
        return padre;
    }

    public String getIgreja() {
        return igreja;
    }

    public String getLocalLua() {
        return localLua;
    }

    public String getPadrinho1() {
        return padrinho1;
    }

    public String getPadrinho2() {
        return padrinho2;
    }

    public String getMadrinha1() {
        return madrinha1;
    }

    public String getMadrinha2() {
        return madrinha2;
    }

}
