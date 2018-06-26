package br.udesc.ceavi.meuprojeto.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class JPADAO<T extends Object> extends JPADAOFactory implements DefaultDAO<T> {

    protected abstract Class<T> classEntity();

    @Override
    public void incluir(T t) throws Exception {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void alterar(T t) throws Exception {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Integer id) throws Exception {
        EntityManager em = getEm();
        try {
            T tt = em.find(classEntity(), id);
            em.getTransaction().begin();
            em.remove(tt);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> listarTodos() {
        EntityManager em = getEm();
        List<T> lista = em.createQuery("SELECT t FROM "
                + classEntity().getCanonicalName().substring(classEntity()
                        .getCanonicalName().lastIndexOf(".") + 1, classEntity()
                        .getCanonicalName().length()) + " t").getResultList();
        em.close();
        return lista;
    }

    @Override
    public T buscarPorId(int id) {
        return getEm().find(classEntity(), id);
    }

    @Override
    public List listarConsultaNomeada(String nome, Map<String, Object> parametros) {
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
            return  result;
        } else {
            return null;
        }
    }

    @Override
    public Object consultaNomeada(String nome, Map<String, Object> parametros) {
        EntityManager em = getEm();
        Query consulta = em.createNamedQuery(nome);
        consulta.setMaxResults(1);

        if (parametros != null) {
            for (String chave : parametros.keySet()) {
                consulta.setParameter(chave, parametros.get(chave));
            }
        }

        Object result = null;

        try {
            result = consulta.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        } finally {
            em.close();
        }

        if (result != null) {
            return  result;
        } else {
            return null;
        }
    }

}
