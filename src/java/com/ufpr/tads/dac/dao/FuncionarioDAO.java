package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.util.ArrayList;


public interface FuncionarioDAO {
    public FuncionarioBean getFuncionarioLogin(String login, String senha) throws FuncionarioException;    
    public ArrayList<FuncionarioBean> getAllFuncioanrios() throws FuncionarioException;
    public void setSenha(String login, String senhaAntiga, String novaSenha) throws FuncionarioException;
    public void deleteUser(String login, String senha) throws FuncionarioException;
    public boolean isEmailDisponivel(String email) throws FuncionarioException;
    
    
}
