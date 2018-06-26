/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.service;

import br.udesc.ceavi.meuprojeto.dao.DAOFactory;
import br.udesc.ceavi.meuprojeto.dao.Factory;
import br.udesc.ceavi.meuprojeto.dao.esforco.EsforcoDAO;
import br.udesc.ceavi.meuprojeto.model.Esforco;
import java.io.Serializable;
import java.util.List;

public class EsforcoService implements Serializable {

    EsforcoDAO dao = DAOFactory.getDAOFactory(Factory.FABRICA).getEsforcoDAO();

    public void insert(Esforco esforco) throws Exception {
        dao.incluir(esforco);
    }

    public void edit(Esforco esforco) throws Exception {
        dao.alterar(esforco);
    }

    public List<Esforco> listar() {
        return dao.listarTodos();
    }

    public void remove(int id) throws Exception {
        dao.excluir(id);
    }

}
