package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.impl.FuncionarioDAOimpl;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.util.ArrayList;


public class FuncionarioFacade {
    static FuncionarioDAOimpl FuncionarioDAO = new FuncionarioDAOimpl();
    
    public static FuncionarioBean getFuncionarioByLogin(String email, String senha) throws FuncionarioException{
        
        return FuncionarioDAO.getFuncionarioLogin(email, senha);  
    }
    public static ArrayList<FuncionarioBean> getAllFuncionarios() throws FuncionarioException{
        return FuncionarioDAO.getAllFuncionarios();
    }
        
    public static void novoFuncionario(FuncionarioBean f) throws FuncionarioException {
        FuncionarioDAO.setFuncionario(f);
    }
    
    public static void deleteFuncionario(int fId) throws FuncionarioException {
        FuncionarioDAO.deleteFuncionario(fId);
    }
    
    public static void updateFuncionario(FuncionarioBean f) throws FuncionarioException {
        FuncionarioDAO.updateFuncionario(f);
    }

    public static FuncionarioBean getFuncionarioById(int funcId) throws FuncionarioException {
        return FuncionarioDAO.getFuncionarioById(funcId);
    }
}
