package eg.edu.alexu.csd.datastructure.stack.cs84;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.*;
import eg.edu.alexu.csd.datastructure.stack.cs84.Stack;
import java.util.regex.Pattern;
/**
 * This class implements the conversion from Infix expression to postfix expression
 * and to evaluate a numeric postfix expression
 * @author Menna Samir
 *
 */
public class Expression implements IExpressionEvaluator{
	
	/**
	 * This method checks if the given expression is correct 
	 * and can be converted to postfix expression or not
	 * It adjusts the spaces and the minus operation
	 * @param expression
	 * 			numeric or symbolic expression
	 * @return expression if it's valid and null if the expression is invalid
	 */
	public String check(String expression) {
		String expr = new String();
		int j=-1;
		for(int i=0; i<expression.length();i++) {
			if(i==expression.length()-1) {
				if(!Character.isWhitespace(expr.charAt(j))) {
					expr=expr+" "+Character.toString(expression.charAt(i));
					j=j+2;
				}else {
					expr=expr+Character.toString(expression.charAt(i));
					j++;
				}
			}else if(!Character.isLetterOrDigit(expression.charAt(i)) && !Character.isWhitespace(expression.charAt(i))) {
				if(!Character.isWhitespace(expression.charAt(i+1))) {
					if(i!=0) {
					if(!Character.isWhitespace(expr.charAt(j))) {
							expr=expr+" "+Character.toString(expression.charAt(i))+" ";
							j=j+3;
						}else {
							expr=expr+Character.toString(expression.charAt(i))+" ";
							j=j+2;
						}
					}else {
						expr=expr+Character.toString(expression.charAt(i))+" ";
						j=j+2;
					}
				}else {
					if(!Character.isWhitespace(expr.charAt(j))) {
						expr=expr+" "+Character.toString(expression.charAt(i));
						j=j+2;
					}else {
						expr=expr+Character.toString(expression.charAt(i));
						j++;	
					}
				}
			}else {
				expr=expr+Character.toString(expression.charAt(i));
				j++;
			}
		}
		
		String[] arr = expr.split(" ");
		List<String> exp = new ArrayList<String>(Arrays.asList(arr));
		int flag=0;
		for(int i=0;i<exp.size()-1;i++) {
			if(exp.get(i)==" ") {
				continue;
			}
			if(exp.get(i).matches(".*[a-z].*") || exp.get(i).matches(".*[A-Z].*") || exp.get(i).matches(".*[0-9].*")){
				if(exp.get(i+1).matches(".*[a-z].*") || exp.get(i+1).matches(".*[A-Z].*") || exp.get(i+1).matches(".*[0-9].*")){
					return null;
				}
			}
			if(exp.get(i).contains("+") || exp.get(i).contains("-") || exp.get(i).contains("/") || exp.get(i).contains("*") || exp.get(i).contains("(") || exp.get(i).contains(")") || exp.get(i).contains("[") || exp.get(i).contains("]") || exp.get(i).contains("{") || exp.get(i).contains("}")) {
				if(exp.get(i).contains("(") || exp.get(i).contains("[") || exp.get(i).contains("{")){
					flag++;
					if(exp.get(i+1).contains("-")) {
						exp.add(i+1, "0");
					}
				}else if(exp.get(i).contains(")") || exp.get(i).contains("]") || exp.get(i).contains("}")) {
					flag--;
				}else if(exp.get(i).contains("+") || exp.get(i).contains("-") || exp.get(i).contains("/") || exp.get(i).contains("*")) {
					if(exp.get(i+1).matches(".*[a-z].*") || exp.get(i+1).matches(".*[A-Z].*") || exp.get(i+1).matches(".*[0-9].*")) {
						exp.add(i+1, " ");
						continue;
					}else if(exp.get(i+1).contains("-") && exp.get(i+2).contains("-")){
						return null;
					}else if(exp.get(i+1).contains("-") && (exp.get(i+2).contains("(")  || exp.get(i+2).contains("[") || exp.get(i+2).contains("{"))) {
						exp.add(i+1, exp.get(i+2));
						exp.add(i+2, "0");
						for(int k=i+3;k<exp.size();k++) {
							if(exp.get(k).contains(")") || exp.get(k).contains("]") || exp.get(k).contains("}")) {
								exp.add(k, exp.get(k));
								break;
							}
						}
					}else if(exp.get(i+1).contains("-") && (exp.get(i+2).matches(".*[a-z].*") || exp.get(i+2).matches(".*[A-Z].*") || exp.get(i+2).matches(".*[0-9].*"))) {
						exp.add(i+1, "(");
						exp.add(i+2, "0");
						exp.add(i+5, ")");
					}else if(exp.get(i+1).contains("+") || exp.get(i+1).contains("*") || exp.get(i+1).contains("/") || exp.get(i+1).contains(")") || exp.get(i+1).contains("]") || exp.get(i+1).contains("}")){
						return null;
					}
				}
			}
			exp.add(i+1, " ");
		}
		if(exp.get(exp.size()-1).matches(".*[a-z].*") || exp.get(exp.size()-1).matches(".*[A-Z].*") || exp.get(exp.size()-1).matches(".*[0-9].*") || exp.get(exp.size()-1).contains(")") || exp.get(exp.size()-1).contains("}") || exp.get(exp.size()-1).contains("]")) {
			if(exp.get(exp.size()-1).contains(")") || exp.get(exp.size()-1).contains("}") || exp.get(exp.size()-1).contains("]")) {
				flag--;	
			}
		}else {
			return null;
		}
		if(flag!=0) {
			return null;
		}
		String str = new String();
		for(int i=0; i<exp.size(); i++) {
			str+=exp.get(i);
		}
		return str;
	}
	
	/**
	 * This method decides the precedence of the operands 
	 * compared with the others
	 * @param operand
	 * @return the degree of precedence of the given operand
	 */
	public int precedenc(String operand) {
		int res=0;
		if(operand.contains("+") || operand.contains("-")) {
			res=0;
		}else if(operand.contains("*") || operand.contains("/")) {
			res=1;
		}else {
			res=-1;
		}
		return res;
	}
	
	/**
	 * Takes a symbolic/numeric infix expression as input and converts it to
	 * postfix notation. There is no assumption on spaces between terms or the
	 * length of the term
	 * @param expression
	 * 			Infix expression
	 * @return postfix expression
	 */
	public String infixToPostfix(String expression) {
		if(check(expression)==null) {
			return "wrong input";
		}else {
			String[] arr = check(expression).split(" ");
			List<String> exp = new ArrayList<String>(Arrays.asList(arr));
			StringBuffer sb = new StringBuffer();
			Stack s = new Stack();
			for(int i=0;i<arr.length;i++) {
				if(exp.get(i).matches(".*[a-z].*") || exp.get(i).matches(".*[A-Z].*") || exp.get(i).matches(".*[0-9].*")) {
					sb.append(exp.get(i));
					sb.append(" ");
				}else if(exp.get(i).contains("+") || exp.get(i).contains("-") || exp.get(i).contains("/") || exp.get(i).contains("*")){
					while(!s.isEmpty() && !(((String)s.peek()).contains("(") || ((String)s.peek()).contains("[") || ((String)s.peek()).contains("{")) && precedenc(exp.get(i))<=precedenc((String)s.peek())) {
						sb.append(s.peek());
						sb.append(" ");
						s.pop();
					}
					s.push(exp.get(i));
				}else if(exp.get(i).contains("(") || exp.get(i).contains("[") || exp.get(i).contains("{")) {
					s.push(exp.get(i));
				}else if(exp.get(i).contains(")") || exp.get(i).contains("]") || exp.get(i).contains("}")) {
					while(!s.isEmpty() && !(((String)s.peek()).contains("(") || ((String)s.peek()).contains("[") || ((String)s.peek()).contains("{"))) {
						sb.append(s.peek());
						sb.append(" ");
						s.pop();
					}
					s.pop();
				}
			}
			while(!s.isEmpty()) {
				sb.append(s.peek());
				sb.append(" ");
				s.pop();
			}
			if(sb.charAt(sb.length()-1)==' ') {
				sb.deleteCharAt(sb.length()-1);
			}
			return sb.toString();
		}
	}
	
	/**
	 * This method takes two numbers and operand 
	 * and evaluates an arithmetic operation
	 * @param operand
	 * 			the arithmetic operation symbol
	 * @param num1
	 * 			first number in the equation
	 * @param num2
	 *  		second number in the equation
	 * @return the result of the arithmetic operation performed as string
	 */
	public String eval(String operand, String num1, String num2) {
		int res=0;
		if(operand.contains("+")) {
			res=Integer.parseInt(num1) + Integer.parseInt(num2);
		}else if(operand.contains("-")) {
			res=Integer.parseInt(num1) - Integer.parseInt(num2);
		}else if(operand.contains("/")) {
			res=Integer.parseInt(num1) / Integer.parseInt(num2);
		}else {
			res=Integer.parseInt(num1) * Integer.parseInt(num2);
		}
		return Integer.toString(res);
	}

	/**
	 * This method checks if the given expression is postfix or not
	 * @param expression
	 * 			postfix expression
	 * @return return integer to indicate the validity of the expression
	 */
	public static int checkPostfix(String expression) {
		String arr[] = expression.split(" ");
		int counter=0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i].matches("[a-z_A-Z_0-9]*$")) {
				counter++;
			}else if(arr[i].contains("+") || arr[i].contains("/") || arr[i].contains("-") || arr[i].contains("*")){
				if(!arr[i].contains("**")) {
					counter=counter-2;
					counter++;	
				}
			}
			if(counter<=0) {
				break;
			}
		}
		return counter;
	}
	
	/**
	 * Evaluate a postfix numeric expression, with a single space separator
	 *
	 * @param expression
	 * 			postfix expression
	 * @return the expression evaluated value
	 */
	public int evaluate(String expression) {
		if(checkPostfix(expression) != 1) {
			throw new IllegalArgumentException("The expression is not a postfix");
		}else {
		String[] arr = expression.split(" ");
		Stack s = new Stack();
		for(int i=0; i<arr.length; i++) {
			if(arr[i].matches("[a-z_A-Z]*$")) {
				throw new IllegalArgumentException("Can't evaluate a symbol");
			}
			if(arr[i].matches("[0-9]*$")) {
				s.push(arr[i]);
			}else {
				String num2 = (String) s.peek();
				s.pop();
				String num1 = (String) s.peek();
				s.pop();
				s.push(eval(arr[i],num1,num2));
			}
		}
		return Integer.parseInt((String) s.peek());
		}
	}
}
	
