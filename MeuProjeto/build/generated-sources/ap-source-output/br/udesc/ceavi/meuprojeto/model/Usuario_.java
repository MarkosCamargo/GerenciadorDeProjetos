package br.udesc.ceavi.meuprojeto.model;

import br.udesc.ceavi.meuprojeto.model.RecursoHumano;
import br.udesc.ceavi.meuprojeto.model.TipoUsuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-29T15:43:11")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, RecursoHumano> pessoa;
    public static volatile SingularAttribute<Usuario, Integer> IdUsuario;
    public static volatile SingularAttribute<Usuario, TipoUsuario> tipoUsuario;

}