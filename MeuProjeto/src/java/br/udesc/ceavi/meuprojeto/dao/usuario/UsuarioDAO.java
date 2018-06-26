package br.udesc.ceavi.meuprojeto.dao.usuario;

import br.udesc.ceavi.meuprojeto.dao.DefaultDAO;
import br.udesc.ceavi.meuprojeto.model.Usuario;

public interface UsuarioDAO extends DefaultDAO<Usuario> {
    
    public abstract Usuario fazerLogin(String email, String senha);

}
