package br.udesc.ceavi.meuprojeto.model;

import br.udesc.ceavi.meuprojeto.model.Esforco;
import br.udesc.ceavi.meuprojeto.model.RecursoHumano;
import br.udesc.ceavi.meuprojeto.model.Situacao;
import br.udesc.ceavi.meuprojeto.model.Tipo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-29T15:43:11")
@StaticMetamodel(Atividade.class)
public class Atividade_ { 

    public static volatile ListAttribute<Atividade, RecursoHumano> pessoas;
    public static volatile SingularAttribute<Atividade, Tipo> tipo;
    public static volatile SingularAttribute<Atividade, String> observacao;
    public static volatile SingularAttribute<Atividade, Esforco> esforco;
    public static volatile SingularAttribute<Atividade, Integer> horasGastas;
    public static volatile SingularAttribute<Atividade, Date> dataTermino;
    public static volatile SingularAttribute<Atividade, String> titulo;
    public static volatile SingularAttribute<Atividade, Date> dataInicio;
    public static volatile SingularAttribute<Atividade, Date> dataPrevista;
    public static volatile SingularAttribute<Atividade, Integer> IdAtividade;
    public static volatile SingularAttribute<Atividade, Situacao> status;
    public static volatile SingularAttribute<Atividade, String> descricao;

}