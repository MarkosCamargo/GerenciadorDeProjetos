package br.udesc.ceavi.meuprojeto.model;

import br.udesc.ceavi.meuprojeto.model.Atividade;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-25T19:55:43")
@StaticMetamodel(RecursoHumano.class)
public class RecursoHumano_ { 

    public static volatile ListAttribute<RecursoHumano, Atividade> atividades;
    public static volatile SingularAttribute<RecursoHumano, String> telefone;
    public static volatile SingularAttribute<RecursoHumano, Integer> IdRecursoHumano;
    public static volatile SingularAttribute<RecursoHumano, String> nome;
    public static volatile SingularAttribute<RecursoHumano, String> email;

}