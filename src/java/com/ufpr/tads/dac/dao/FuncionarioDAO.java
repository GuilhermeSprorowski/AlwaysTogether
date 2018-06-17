package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.util.ArrayList;


public interface FuncionarioDAO {
    public FuncionarioBean getFuncionarioLogin(String login, String senha) throws FuncionarioException;    
    public ArrayList<FuncionarioBean> getAllFuncionarios() throws FuncionarioException;
    public void setSenha(String login, String senhaAntiga, String novaSenha) throws FuncionarioException;
    public void deleteUser(String login, String senha) throws FuncionarioException;
    public boolean isEmailDisponivel(String email) throws FuncionarioException;
    public void setFuncionario(FuncionarioBean f) throws FuncionarioException;
    public void updateFuncionario(FuncionarioBean f) throws FuncionarioException;
    public void deleteFuncionario(int idFuncionario) throws FuncionarioException;
    public FuncionarioBean getFuncionarioById(int idFuncionario) throws FuncionarioException;
}
