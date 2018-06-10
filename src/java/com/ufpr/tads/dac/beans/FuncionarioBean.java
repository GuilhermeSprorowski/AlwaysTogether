package com.ufpr.tads.dac.beans;

public class FuncionarioBean {
    
    private int funcionarioId;
    private String email;
    private String nome;
    private boolean adm;
    private int codigo;

    public FuncionarioBean() {
    }

    public FuncionarioBean(int funcionarioId, String email, String nome, boolean adm, int codigo) {
        this.funcionarioId = funcionarioId;
        this.email = email;
        this.nome = nome;
        this.adm = adm;
        this.codigo = codigo;
    }

    public boolean isAdm() {
        return adm;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
