/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.util;

import br.udesc.ceavi.meuprojeto.dao.DAOFactory;
import br.udesc.ceavi.meuprojeto.dao.Factory;
import br.udesc.ceavi.meuprojeto.dao.usuario.UsuarioDAO;
import br.udesc.ceavi.meuprojeto.model.RecursoHumano;
import br.udesc.ceavi.meuprojeto.model.TipoUsuario;
import br.udesc.ceavi.meuprojeto.model.Usuario;

/**
 *
 * @author Marcos Camargo
 */
public class GerarBanco {

    public static void main(String[] args) throws Exception {
        UsuarioDAO daoU = DAOFactory.getDAOFactory(Factory.FABRICA).getUsuarioDAO();
        Usuario u = new Usuario();
        RecursoHumano p = new RecursoHumano();
        u.setIdUsuario(1);
        p.setIdRecursoHumano(1);
        p.setEmail("teste");
        p.setNome("administrador");
        p.setTelefone("(49) 9 8822-2321");
        u.setPessoa(p);
        u.setSenha(GerarMD5.criptografar("teste"));
        u.setTipoUsuario(TipoUsuario.ADM);
        daoU.incluir(u);
        
        Usuario u2 = new Usuario();
        RecursoHumano p2 = new RecursoHumano();
        u2.setIdUsuario(2);
        p2.setIdRecursoHumano(2);
        p2.setEmail("marcos");
        p2.setNome("Marcos Rufino de Camargo");
        p2.setTelefone("(49) 9 8844-1999");
        u2.setPessoa(p2);
        u2.setSenha(GerarMD5.criptografar("marcos"));
        u2.setTipoUsuario(TipoUsuario.VISUALIZAR);
        daoU.incluir(u2);
        

    }
}
