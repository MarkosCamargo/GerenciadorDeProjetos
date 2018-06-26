package br.udesc.ceavi.meuprojeto.dao;

//import br.udesc.ceavi.custovida.dao.usuario.UsuarioDAO;

import br.udesc.ceavi.meuprojeto.dao.esforco.EsforcoDAO;
import br.udesc.ceavi.meuprojeto.dao.usuario.UsuarioDAO;
import br.udesc.ceavi.meuprojeto.dao.atividade.AtividadeDAO;


public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(int factory) {
        switch (factory) {
            case 1:
                return  new JPADAOFactory();
            default:
                return null;
        }
    }
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract EsforcoDAO getEsforcoDAO();
    public abstract AtividadeDAO getAtividadeDAO();

    
}
