/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tester;

import java.sql.SQLException;
import java.util.List;
import java.util.zip.DataFormatException;

import viper.comms.dao.conn.*;
import viper.comms.dao.exception.EntityNotFoundException;
import viper.comms.dao.exception.NonexistentEntityException;
import viper.comms.dao.exception.PreexistingEntityException;

/**
 *
 * @author alva
 */
public class PegawaixJpaController {

    public PegawaixJpaController() {
        emf = Persistence.createEntityManagerFactory("JavaApplication11PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pegawaix pegawaix) throws PreexistingEntityException, Exception {
        if (pegawaix.getPegawaixPK() == null) {
            pegawaix.setPegawaixPK(new PegawaixPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pegawaix);
            em.getTransaction().commit();
        } catch (Exception ex) {
//            if (findPegawaix(pegawaix.getPegawaixPK()) != null) {
//                throw new PreexistingEntityException("Pegawaix " + pegawaix + " already exists.", ex);
//            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pegawaix pegawaix) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
             em.merge(pegawaix);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PegawaixPK id = pegawaix.getPegawaixPK();
                if (findPegawaix(id) == null) {
                    throw new NonexistentEntityException("The pegawaix with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

//    public void destroy(PegawaixPK id) throws NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Pegawaix pegawaix;
//            try {
//                 em.getReference(Pegawaix.class, id);
//                pegawaix.getPegawaixPK();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The pegawaix with id " + id + " no longer exists.", enfe);
//            }
//            em.remove(pegawaix);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }

    public List<Pegawaix> findPegawaixEntities() throws SQLException {
        return findPegawaixEntities(true, -1, -1);
    }

    public List<Pegawaix> findPegawaixEntities(int maxResults, int firstResult) throws SQLException {
        return findPegawaixEntities(false, maxResults, firstResult);
    }

    private List<Pegawaix> findPegawaixEntities(boolean all, int maxResults, int firstResult) throws SQLException {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Pegawaix as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pegawaix findPegawaix(PegawaixPK id) throws DataFormatException, EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            return (Pegawaix) em.find(id,Pegawaix.class);
 

        } finally {
            em.close();
        }
    }

    public int getPegawaixCount() throws SQLException {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Pegawaix as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
