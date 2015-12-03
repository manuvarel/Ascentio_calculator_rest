package com.company.project.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.data.dto.CalculationSessionDto;
import com.company.project.exception.CalculationSessionNotFoundException;
import com.company.project.services.CalculationSessionService;

@RestController
@RequestMapping(value = "/session")
public class SessionRestService {

	private final CalculationSessionService calculationService;

	@Autowired
	public SessionRestService(CalculationSessionService calculationService) {
		this.calculationService = calculationService;
	}
	
	/**
	 * Return all the session stored in Database.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<CalculationSessionDto> listSession() {
		return calculationService.listSession();
	}
	
	/**
	 * Search a particular Calculation Session previously saved.
	 * @param sessionName, field that identify a particular Calculation Session
	 * @return
	 * @throws CalculationSessionNotFoundException
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public CalculationSessionDto getSession(@PathVariable(value = "name") String sessionName)
			throws CalculationSessionNotFoundException {
		return calculationService.retrieveSession(sessionName);
	}

	/**
	 * Save the current Calculation Session
	 * @param sessionName, field that identify the Calculation Session to save.
	 * @return
	 * @throws CalculationSessionNotFoundException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public CalculationSessionDto saveSession(@RequestParam(value = "name") String sessionName)
			throws CalculationSessionNotFoundException {
		calculationService.saveCalculationSession(sessionName);
		return getSession(sessionName);
	}

	@ExceptionHandler(CalculationSessionNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handleException(CalculationSessionNotFoundException ex) {
	}

}
