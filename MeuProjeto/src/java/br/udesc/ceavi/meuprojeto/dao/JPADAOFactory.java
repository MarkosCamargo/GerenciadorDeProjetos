package br.udesc.ceavi.meuprojeto.dao;

import br.udesc.ceavi.meuprojeto.dao.atividade.AtividadeDAO;
import br.udesc.ceavi.meuprojeto.dao.atividade.JPAAtividadeDAO;
import br.udesc.ceavi.meuprojeto.dao.esforco.EsforcoDAO;
import br.udesc.ceavi.meuprojeto.dao.esforco.JPAEsforcoDAO;
import br.udesc.ceavi.meuprojeto.dao.usuario.JPAUsuarioDAO;
import br.udesc.ceavi.meuprojeto.dao.usuario.UsuarioDAO;
import javax.persistence.EntityManager;


public class JPADAOFactory extends DAOFactory {

    public EntityManager getEm() {
        GerenteEntityManager gem = GerenteEntityManager.getGerenteEntityManager();
        return gem.getEntityManager();
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new JPAUsuarioDAO();
    }

    @Override
    public EsforcoDAO getEsforcoDAO() {
       return new JPAEsforcoDAO(); }

    @Override
    public AtividadeDAO getAtividadeDAO() {
       return new JPAAtividadeDAO();}

}
