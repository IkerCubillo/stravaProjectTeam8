package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;

public class TrainingSessionDAO extends DataAccessObject implements IDataAccessObject<TrainingSession>{

private static TrainingSessionDAO instance;	
	
	private TrainingSessionDAO() { }
	
	public static TrainingSessionDAO getInstance() {
		if (instance == null) {
			instance = new TrainingSessionDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(TrainingSession object) {
		TrainingSession storedObject = instance.find(object.getTitle());

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			if (storedObject != null) {
				em.merge(object);
			} else {
				em.persist(object);			
			}
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error storing session: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@Override
	public void delete(TrainingSession object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			TrainingSession storedObject = (TrainingSession) em.find(TrainingSession.class, object.getTitle());
			em.remove(storedObject);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error removing session: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingSession> getAll() {				
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		List<TrainingSession> sessions = new ArrayList<>();
		
		try {
			tx.begin();
			
			Query q = em.createQuery("SELECT t FROM TrainingSession t");
			sessions = (List<TrainingSession>) q.getResultList();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving sessions: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return sessions;
	}

	@Override
	public TrainingSession find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		TrainingSession result = null; 

		try {
			tx.begin();
						
			result = (TrainingSession) em.find(TrainingSession.class, param);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying session by title: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return result;
	}
}