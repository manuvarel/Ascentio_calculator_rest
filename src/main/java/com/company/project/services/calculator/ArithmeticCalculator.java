package com.company.project.services.calculator;

import java.util.Stack;

import org.springframework.stereotype.Service;

/**
 * Responsible for do the arithmetic calculate for an input.
 *
 * @author Manuel Varela
 *
 */
@Service
public class ArithmeticCalculator {

	
	/**
	 * Resposible to do the arithmetic calculate of the input
	 * @param input to be calculated
	 * @return arithmetic result of the input calculated
	 */
	public static Double calculate(String input) {

		String[] inputArray = formatter(input).split(" ");

		Stack<String> operands  = new Stack<String>();
		Stack<Double> values = new Stack<Double>();
		for (String s : inputArray) {
			if(s.isEmpty())
				continue;
			if (s.equals("("));
			else if (s.equals("+"))  
				operands.push(s);
			else if (s.equals("-"))  
				operands.push(s);
			else if (s.equals("*")) 
				operands.push(s);
			else if (s.equals("/")) 
				operands.push(s);
			else if (s.equals("l")) 
				operands.push(s);
			else if (s.equals(")")) {
				if(operands.isEmpty())
					break;
				String operand = operands.pop();
				double value = values.pop();
				if      (operand.equals("+"))
					value = values.pop() + value;
				else if (operand.equals("-"))  
					value = values.pop() - value;
				else if (operand.equals("*"))  
					value = values.pop() * value;
				else if (operand.equals("/"))  
					value = values.pop() / value;
				else if (operand.equals("l"))
					value = Math.log(value) / Math.log(10);
				values.push(value);
			}
			else values.push(Double.parseDouble(s));
		}
		return values.pop();
	}
	
	/**
	 * The input used to calculate must be have some specifications. 
	 * This method try to fix that.
	 * @param input to be formatter
	 * @return a string with the correct form
	 */
	private static String formatter(String input){
		if(input == null || input.isEmpty())
			return null;

		if(input.charAt(0) != '(' )
			input = "("+input+")";
			
		String newinput = input.replace("log", "l").replace("", " ").replaceAll("(?<=\\d) +(?=\\d)","").trim();
		return newinput;
	}

}