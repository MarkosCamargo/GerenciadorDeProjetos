package br.udesc.ceavi.meuprojeto.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JPAUtil<T extends Object> extends JPADAOFactory {

    public T consultaNomeada(String nome, Map<String, Object> parametros) {
        EntityManager em = getEm();
        Query consulta = em.createNamedQuery(nome);

        if (parametros != null) {
            for (String chave : parametros.keySet()) {
                consulta.setParameter(chave, parametros.get(chave));
            }
        }

        Object result = null;

        try {
            result = consulta.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro: "+e.getMessage());
            result = null;
        } finally {
            em.close();
        }

        if (result != null) {
            return (T) result;
        } else {
            return null;
        }
    }

    public <T> List<T> listarConsultaNomeada(String nome, Map<String, Object> parametros) {
        EntityManager em = getEm();
        Query consulta = em.createNamedQuery(nome);

        if (parametros != null) {
            for (String chave : parametros.keySet()) {
                consulta.setParameter(chave, parametros.get(chave));
            }
        }

        List result = null;

        try {
            result = consulta.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        if (result != null) {
            return (List<T>) result;
        } else {
            return null;
        }
    }

    public <T> List<T> listarConsultaNomeada(String nome, long registroInicial, long registroFinal, Map<String, Object> parametros) {
        EntityManager em = getEm();
        Query consulta = em.createNamedQuery(nome);

        if ((parametros != null) && (parametros.size() > 0)) {
            Set<String> nomesParametros = parametros.keySet();

            for (String nomeParametro : nomesParametros) {
                try {
                    consulta.setParameter(nomeParametro, parametros.get(nomeParametro));
                } catch (Exception e) {
                    System.out.println("Parametro '" + nomeParametro + "' nao existe!");
                }
            }
        }

        if ((registroInicial >= 0) && (registroFinal > registroInicial)) {
            consulta.setFirstResult((int) registroInicial);
            consulta.setMaxResults((int) (registroFinal - registroInicial));
        }

        return consulta.getResultList();
    }

    public List<Object[]> listarNativeSQL(String sql) {
        EntityManager em = getEm();
        Query q = em.createNativeQuery(sql);
        return q.getResultList();
    }
}
