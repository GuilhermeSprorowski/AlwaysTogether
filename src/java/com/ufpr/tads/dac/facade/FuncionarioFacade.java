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
        return FuncionarioDAO.getAllFuncioanrios();
    }
}
