package com.company.project.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.services.CalculationSessionService;

@RestController
@RequestMapping(value = "/calculadora")
public class CalculatorRestService {

	private final CalculationSessionService calculationService;

	@Autowired
	public CalculatorRestService(CalculationSessionService calculationService) {
		this.calculationService = calculationService;
	}
	
	/** 
	 * Method to check if the service is up and running
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping(@RequestParam(value = "message", required = false) String message) {
		return message + " returned";
	}
	
	/**
	 * Use the calculation service to calculate the operation 
	 * @param operation, represent the operation that will be calculate
	 * @return result of the arithmetic calculation
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String makeOperation(@RequestParam(value = "operation") String operation) {
		String result = calculationService.startSession(operation);
		calculationService.addFunction(operation, result);
		return result;
	}

}
