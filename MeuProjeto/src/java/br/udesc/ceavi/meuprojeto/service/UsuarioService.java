/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.service;

import br.udesc.ceavi.meuprojeto.dao.DAOFactory;
import br.udesc.ceavi.meuprojeto.dao.Factory;
import br.udesc.ceavi.meuprojeto.dao.usuario.UsuarioDAO;
import br.udesc.ceavi.meuprojeto.model.Usuario;
import java.io.Serializable;
import java.util.List;

public class UsuarioService implements Serializable {

    UsuarioDAO daoU = DAOFactory.getDAOFactory(Factory.FABRICA).getUsuarioDAO();

    public void insert(Usuario usuario) throws Exception {
        daoU.incluir(usuario);
    }

    public void edit(Usuario usuario) throws Exception {
        daoU.alterar(usuario);
    }

    public Usuario login(String email, String senha) {
        return daoU.fazerLogin(email, senha);
    }

    public List<Usuario> listar() {
        return daoU.listarTodos();
    }

//    public void beforeInsert(Usuario usuario) {
//        if (!usuario.hasModel()) {
////            throw new Exception("Car model cannot be empty");
//        }
//        if (!usuario.hasName()) {
////            throw new BusinessException("Car name cannot be empty");
//        }
//
////        if (allCars.stream().filter(c -> c.getName().equalsIgnoreCase(car.getName())
////                && c.getId() != c.getId()).count() > 0) {
////            throw new BusinessException("Car name must be unique");
////        }
//    }
    public void remove(int id) throws Exception {

        daoU.excluir(id);
    }

}
