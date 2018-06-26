package br.udesc.ceavi.meuprojeto.dao.atividade;

import br.udesc.ceavi.meuprojeto.dao.DefaultDAO;
import br.udesc.ceavi.meuprojeto.model.Atividade;
import java.util.List;

public interface AtividadeDAO extends DefaultDAO<Atividade> {

     public abstract List<Atividade> atividadesNaoIniciadas();
     public abstract List<Atividade> atividadesIniciadas();
     public abstract List<Atividade> atividadesConcluidas();
     public abstract List<Atividade> atividadesAtrasadas();
     public abstract List<Atividade> atividadesConcluidasAntesPrazo();

}
