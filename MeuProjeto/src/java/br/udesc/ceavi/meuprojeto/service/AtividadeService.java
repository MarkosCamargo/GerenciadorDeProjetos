/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.service;

import br.udesc.ceavi.meuprojeto.dao.DAOFactory;
import br.udesc.ceavi.meuprojeto.dao.Factory;
import br.udesc.ceavi.meuprojeto.dao.atividade.AtividadeDAO;
import br.udesc.ceavi.meuprojeto.model.Atividade;
import java.io.Serializable;
import java.util.List;

public class AtividadeService implements Serializable {

    AtividadeDAO dao = DAOFactory.getDAOFactory(Factory.FABRICA).getAtividadeDAO();

    public void insert(Atividade atividade) throws Exception {
        dao.incluir(atividade);
    }

    public void edit(Atividade atividade) throws Exception {
        dao.alterar(atividade);
    }

    public List<Atividade> listar() {
        return dao.listarTodos();
    }

    public List<Atividade> listarIniciadas() {
        return dao.atividadesIniciadas();
    }

    public List<Atividade> listarNaoIniciadas() {
        return dao.atividadesNaoIniciadas();
    }

    public List<Atividade> listarConcluidas() {
        return dao.atividadesConcluidas();
    }

    public List<Atividade> listarAtrasadas() {
        return dao.atividadesAtrasadas();
    }
    
    public List<Atividade> listarConcluidasAntesPrazo() {
        return dao.atividadesConcluidasAntesPrazo();
    }

    public void remove(int id) throws Exception {
        dao.excluir(id);
    }

}
