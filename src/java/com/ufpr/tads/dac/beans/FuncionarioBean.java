package com.ufpr.tads.dac.beans;

import java.util.Date;

public class FuncionarioBean {
    
    private int funcionarioId;
    private String email;
    private String nome;
    private String cpf;
    private Date dataNasc;
    private boolean adm;
    private String senha;
    private int codigo;

    public FuncionarioBean() {
    }

    public FuncionarioBean(int funcionarioId, String email, String nome, String cpf, Date dataNasc, int codigo) {
        this.funcionarioId = funcionarioId;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.codigo = codigo;
    }
    
    public FuncionarioBean(int funcionarioId, String nome, Date dataNasc, boolean adm, String cpf, int codigo, String email) {
        this.funcionarioId = funcionarioId;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.adm = adm;
        this.codigo = codigo;
        this.email = email;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String s) {
        this.senha = s;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
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
