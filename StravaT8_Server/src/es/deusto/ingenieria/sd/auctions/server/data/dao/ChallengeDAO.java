package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;

public class ChallengeDAO extends DataAccessObject implements IDataAccessObject<Challenge> {

private static ChallengeDAO instance;	
	
	private ChallengeDAO() { }
	
	public static ChallengeDAO getInstance() {
		if (instance == null) {
			instance = new ChallengeDAO();
		}		
		
		return instance;
	}	
	
	@Override
	public void save(Challenge object) {
		Challenge storedObject = instance.find(object.getName());

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
			System.out.println("  $ Error storing challenge: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@Override
	public void delete(Challenge object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Challenge storedObject = (Challenge) em.find(Challenge.class, object.getName());
			em.remove(storedObject);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error removing challenge: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Challenge> getAll() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		List<Challenge> challenges = new ArrayList<>();

		try {
			tx.begin();

			Query q = em.createQuery("SELECT c FROM Challenge c");
			challenges = (List<Challenge>) q.getResultList();
						
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving challenges: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return challenges;
	}

	@Override
	public Challenge find(String param) {		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Challenge result = null; 

		try {
			tx.begin();

			result = (Challenge) em.find(Challenge.class, param);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying challenge by name: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return result;
	}
}
