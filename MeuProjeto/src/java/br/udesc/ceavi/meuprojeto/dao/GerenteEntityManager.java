package br.udesc.ceavi.meuprojeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton para evitar que o entity manager seja criado diversas vezes
 */
public class GerenteEntityManager {

    private static GerenteEntityManager gem = null;

    private EntityManagerFactory emf = null;

    private GerenteEntityManager() {
        init();
    }

    public void init() {
        emf = Persistence.createEntityManagerFactory("MeuProjetoPU");
    }

    public static synchronized GerenteEntityManager getGerenteEntityManager() {
        if (gem == null) {
            gem = new GerenteEntityManager();
        }
        return gem;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
