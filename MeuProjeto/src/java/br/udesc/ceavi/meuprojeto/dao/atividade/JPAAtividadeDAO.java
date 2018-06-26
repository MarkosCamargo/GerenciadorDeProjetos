package br.udesc.ceavi.meuprojeto.dao.atividade;

import br.udesc.ceavi.meuprojeto.dao.JPADAO;
import br.udesc.ceavi.meuprojeto.model.Atividade;
import br.udesc.ceavi.meuprojeto.model.Situacao;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPAAtividadeDAO extends JPADAO<Atividade> implements AtividadeDAO {

    @Override
    protected Class<Atividade> classEntity() {
        return Atividade.class;
    }

    @Override
    public List<Atividade> atividadesNaoIniciadas() {
        Map<String, Object> params = new HashMap<>();
        params.put("situacao", Situacao.NAO_INICIADA);

        return (List<Atividade>) listarConsultaNomeada("atividade.porSituacao", params);
    }

    @Override
    public List<Atividade> atividadesIniciadas() {
        Map<String, Object> params = new HashMap<>();
        params.put("situacao", Situacao.INICIADA);

        return (List<Atividade>) listarConsultaNomeada("atividade.porSituacao", params);
    }

    @Override
    public List<Atividade> atividadesConcluidas() {
        Map<String, Object> params = new HashMap<>();
        params.put("situacao", Situacao.CONCLUIDA);

        return (List<Atividade>) listarConsultaNomeada("atividade.porSituacao", params);
    }

    @Override
    public List<Atividade> atividadesAtrasadas() {
        Map<String, Object> params = new HashMap<>();
        params.put("dataAgora", new Date());
        params.put("situacao", Situacao.CONCLUIDA);
        return (List<Atividade>) listarConsultaNomeada("atividade.atrasada", params);
    }

    @Override
    public List<Atividade> atividadesConcluidasAntesPrazo() {
        Map<String, Object> params = new HashMap<>();
        params.put("situacao", Situacao.CONCLUIDA);
        return (List<Atividade>) listarConsultaNomeada("atividade.concluidaAntesPrazo", params);
    }

}
