package com.company.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.data.dao.CalculationSessionDao;
import com.company.project.data.dto.CalculationSessionDto;
import com.company.project.data.dto.FunctionDto;
import com.company.project.exception.CalculationSessionNotFoundException;
import com.company.project.services.calculator.ArithmeticCalculator;

/**
 * Responsible of all operations available in a Calculation Session
 * 
 * @author Manuel Varela
 *
 */
@Transactional
@Service
public class CalculationSessionService {

	private CalculationSessionDto session;
	private final CalculationSessionDao sessionDao;

	@Autowired
	public CalculationSessionService(CalculationSessionDao sessionDao) {
		this.session = new CalculationSessionDto();
		this.sessionDao = sessionDao;
	}

	/**
	 * This is the starting point of calculation. It receives input, then
	 * call the staic method calculate from ArithmeticCalculator class.
	 * At the end this method the result is returned.
	 * @return result of the calculate.
	 */
	public String startSession(String input) {
		Double result;
		result = ArithmeticCalculator.calculate(input);
		return result.toString();
	}

	/**
	 * Add a function to current session with correspondent values.
	 * 
	 * @param input
	 * @param result
	 */
	public void addFunction(String input, String result) {
		FunctionDto function = new FunctionDto(input, result.toString());
		session.addFunction(function);
	}

	/**
	 * Retrive a Calculation session that corresponds with param "name"
	 * 
	 * @param name of the calculation session to retrieve
	 * @return
	 * @throws SessionNotFoundException 
	 */
	public CalculationSessionDto retrieveSession(String name) throws CalculationSessionNotFoundException {
		CalculationSessionDto calculationSession;
		calculationSession = sessionDao.getCalculationSession(name);
		return calculationSession;
	}

	/**
	 * Return all the calculation session recorded in DB
	 * @return
	 */
	public List<CalculationSessionDto> listSession() {
		List<CalculationSessionDto> list;
		try {
			list = sessionDao.listCalculationSession();
		} catch (CalculationSessionNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}
	
	/**
	 * Save the current calculation session with the name passed as parameter.
	 * Then update the session reference with a new CalculationSession object.
	 * @param name
	 */
	public void saveCalculationSession(String name) {
		session.setName(name);
		sessionDao.saveCalculationSession(session);
		session = new CalculationSessionDto();
	}
}
