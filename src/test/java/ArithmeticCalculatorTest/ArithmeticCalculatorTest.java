package ArithmeticCalculatorTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.company.project.services.calculator.ArithmeticCalculator;

public class ArithmeticCalculatorTest {
	String input;
	ArrayList<String> postfixString;
	Double result;

	@Test
	public void testCalculate1() {
		input = "5*(3*(8-23))";
		result = ArithmeticCalculator.calculate(input);
		assertEquals(-225, result, 0);
	}

	@Test
	public void testCalculate2() {
		input = "10/10";
		result = ArithmeticCalculator.calculate(input);
		assertEquals(1, result, 0);
	}

	@Test
	public void testCalculate3() {
		input = "5*(5*5)";
		result = ArithmeticCalculator.calculate(input);
		assertEquals(125, result, 0);
	}

	@Test
	public void testCalculate4() {
		input = "3*(10+3)";
		result = ArithmeticCalculator.calculate(input);
		assertEquals(39, result, 0);
	}

	@Test
	public void testCalculate5() {
		input = " (2+2)*(log (10/3))";
		result = ArithmeticCalculator.calculate(input);
		assertEquals(2.0915149811213505, result, 0);

	}

	@Test
	public void testCalculate6() {
		input = "5+0";
		result = ArithmeticCalculator.calculate(input);
		assertEquals(5, result, 0);

	}
	@Test
	public void testCalculate7(){
		input = "(5+6)+(8)";
		result = ArithmeticCalculator.calculate(input);
		assertEquals(53, result, 0);
	}
}
