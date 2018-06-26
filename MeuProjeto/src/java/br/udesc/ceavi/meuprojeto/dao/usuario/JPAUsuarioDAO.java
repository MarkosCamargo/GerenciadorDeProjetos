package br.udesc.ceavi.meuprojeto.dao.usuario;

import br.udesc.ceavi.meuprojeto.dao.JPADAO;
import br.udesc.ceavi.meuprojeto.model.Usuario;
import java.util.HashMap;
import java.util.Map;

public class JPAUsuarioDAO extends JPADAO<Usuario> implements UsuarioDAO {

    @Override
    protected Class<Usuario> classEntity() {
        return Usuario.class;
    }

    @Override
    public Usuario fazerLogin(String email, String senha) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("senha", senha);

        return (Usuario) consultaNomeada("usuario.login", params);
    }


}
