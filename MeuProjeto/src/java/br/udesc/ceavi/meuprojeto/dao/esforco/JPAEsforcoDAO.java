package br.udesc.ceavi.meuprojeto.dao.esforco;

import br.udesc.ceavi.meuprojeto.dao.JPADAO;
import br.udesc.ceavi.meuprojeto.model.Esforco;

public class JPAEsforcoDAO extends JPADAO<Esforco> implements EsforcoDAO {

    @Override
    protected Class<Esforco> classEntity() {
        return Esforco.class;
    }

    


}
