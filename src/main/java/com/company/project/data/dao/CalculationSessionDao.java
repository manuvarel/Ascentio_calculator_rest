package com.company.project.data.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.data.dto.CalculationSessionDto;
import com.company.project.exception.CalculationSessionNotFoundException;

/**
 * DAO for CalculationSession object
 * 
 * @author Manuel Varela
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class CalculationSessionDao {

	@PersistenceContext
	private EntityManager entityManager;

	public CalculationSessionDao() {
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Persist the Calculation Session in the Database
	 * 
	 * @param calcSession, Calculation Session to persist.
	 */
	public void saveCalculationSession(CalculationSessionDto calcSession) {
		entityManager.persist(calcSession);
	}

	/**
	 * Return as a List all the records in CalculationSession table. @return,
	 * list of all the records in CalculationSession table.
	 */
	public List<CalculationSessionDto> getCalculationSessions() {
		Query query = entityManager.createQuery("SELECT cs FROM CalculationSession cs");
		return query.getResultList();
	}

	/**
	 * Return an unique Calculation Session that correspond with parameter name.
	 * @param name, name of the Calculation Session to retrieve. 
	 * @return, Calculation Session that correspond with parameter name.
	 */
	public CalculationSessionDto getCalculationSession(String name) throws CalculationSessionNotFoundException {
		CalculationSessionDto calcSession;
		try {
			Query query = entityManager.createQuery("FROM CalculationSession WHERE name = :name");
			query.setParameter("name", name);
			calcSession = (CalculationSessionDto) query.getSingleResult();
		} catch (NoResultException e) {
			throw new CalculationSessionNotFoundException("Session '" + name + "' no encontrada");
		}
		return calcSession;
	}

	/**
	 * Return all the Calculation Sessions that are stored in the Database.
	 * @return, all the Calculation Sessions stored.
	 */
	public List<CalculationSessionDto> listCalculationSession() throws CalculationSessionNotFoundException {
		List<CalculationSessionDto> list;
		try {
			Query query = entityManager.createQuery("FROM CalculationSession");
			list = (List<CalculationSessionDto>) query.getResultList();
		} catch (NoResultException e) {
			throw new CalculationSessionNotFoundException("Session no encontrada");
		}
		return list;
	}

}
