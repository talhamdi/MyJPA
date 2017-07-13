/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editattendancetime;

import editattendancetime.exceptions.NonexistentEntityException;
import editattendancetime.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author t.aljehani
 */
public class Test3JpaController implements Serializable {

    public Test3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Test3 test3) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(test3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTest3(test3.getId()) != null) {
                throw new PreexistingEntityException("Test3 " + test3 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Test3 test3) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            test3 = em.merge(test3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = test3.getId();
                if (findTest3(id) == null) {
                    throw new NonexistentEntityException("The test3 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Test3 test3;
            try {
                test3 = em.getReference(Test3.class, id);
                test3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The test3 with id " + id + " no longer exists.", enfe);
            }
            em.remove(test3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Test3> findTest3Entities() {
        return findTest3Entities(true, -1, -1);
    }

    public List<Test3> findTest3Entities(int maxResults, int firstResult) {
        return findTest3Entities(false, maxResults, firstResult);
    }

    private List<Test3> findTest3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Test3.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Test3 findTest3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Test3.class, id);
        } finally {
            em.close();
        }
    }

    public int getTest3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Test3> rt = cq.from(Test3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
