package com.company.project.data.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Represent a Calculation Session entity
 * 
 * @author Manuel Varela
 *
 */
@Entity(name = "CalculationSession")
public class CalculationSessionDto {

	@Id
	@Column(name = "name")
	private String name;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="Calculations_Session_id")
	private Set<FunctionDto> functions = new HashSet<FunctionDto>();

	public CalculationSessionDto() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<FunctionDto> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<FunctionDto> functions) {
		this.functions = functions;
	}

	public void addFunction(FunctionDto function) {
		functions.add(function);
	}

	@Override
	public String toString() {
		return ("Session name:" + name + ", functions=" + functions.toString());
	}

}
