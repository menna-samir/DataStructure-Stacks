package eg.edu.alexu.csd.datastructure.stack.cs84;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApplicationTest {
	Expression n = new Expression();
	@Test
	void testPostfix() {
		String expression = "12 + 3 * 4";
		assertEquals("12 3 4 * +",n.infixToPostfix(expression));
		
		String expression1 = "a * b + 5";
		assertEquals("a b * 5 +",n.infixToPostfix(expression1));
		
		String expression2 = "(1 + 2) * 7";
		assertEquals("1 2 + 7 *",n.infixToPostfix(expression2));
		
		String expression3 = "a * b / c";
		assertEquals("a b * c /",n.infixToPostfix(expression3));
		
		String expression4 = "(a / (b - c + d)) * (e - a) * c";
		assertEquals("a b c - d + / e a - * c *",n.infixToPostfix(expression4));
		
		String expression5 = "a / b - c + d * e - a * c";
		assertEquals("a b / c - d e * + a c * -",n.infixToPostfix(expression5));
		
		String expression6 = "2 + - 3 * 4";
		assertEquals("2 0 3 - 4 * +",n.infixToPostfix(expression6));
		
		String expression7 = "2 + / 3* 4";
		assertEquals("wrong input",n.infixToPostfix(expression7));
		
		String expression8 = "a*(b+c)*d";
		assertEquals("a b c + * d *",n.infixToPostfix(expression8));
		
		String expression9 = "2 + 3 ** 4";
		assertEquals("wrong input",n.infixToPostfix(expression9));
		
		String expression10 = "2 + 3 +-- 4";
		assertEquals("wrong input",n.infixToPostfix(expression10));
	}

	@Test
	void testEvaluation() {
		String expression = "6 2 / 3 - 4 2 * +";
		assertEquals(8,n.evaluate(expression));
		
		String expression1 = "12 3 4 * +";
		assertEquals(24,n.evaluate(expression1));
		
		String expression2 = "1 2 + 7 *";
		assertEquals(21,n.evaluate(expression2));
		
		String expression3 = "2 0 3 - 4 * +";
		assertEquals(-10,n.evaluate(expression3));
		
		String expression4 = "a b * c /";
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->n.evaluate(expression4));
		assertEquals("Can't evaluate a symbol",exception.getMessage());
		
		String expression5 = "2 + 3 * 4";
		IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () ->n.evaluate(expression5));
		assertEquals("The expression is not a postfix",exception1.getMessage());
		
		String expression6 = "2 3 4 ** +";
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () ->n.evaluate(expression6));
		assertEquals("The expression is not a postfix",exception2.getMessage());
		
		String expression7 = "1 2 + 7 * 5";
		IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () ->n.evaluate(expression7));
		assertEquals("The expression is not a postfix",exception3.getMessage());
		
		String expression8 = "234*+";
		IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () ->n.evaluate(expression8));
		assertEquals("The expression is not a postfix",exception4.getMessage());
	}
}
