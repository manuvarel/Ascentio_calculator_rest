package com.company.project.data.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represent a Function entity
 * 
 * @author Manuel Varela
 *
 */
@Entity(name = "Function")
public class FunctionDto {
	/**
	 * Id for a unique Function
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "functionid")
	private Integer functionid;
	/**
	 * String that represent a operation in the Function
	 */
	@Column(name = "operation")
	private String operation;
	/**
	 * String that represent the arithmetic result of the operation.
	 */
	@Column(name = "result")
	private String result;

	public FunctionDto() {

	}

	public FunctionDto(String operation, String result) {
		this.operation = operation;
		this.result = result;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return ("Function: ID=" + functionid + " operation:" + operation + ", result=" + result );
	}

}
