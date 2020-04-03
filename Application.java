package eg.edu.alexu.csd.datastructure.stack.cs84;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		Expression  operation = new Expression();
		boolean Exit = true;
		Scanner sc = new Scanner(System.in);
		while(Exit) {
			System.out.println("Choose operation:\n1: Convert from Infix to Postfix\n2: Evaluate Postfix expression\n3: Exit");
			int choice = sc.nextInt();
			if(choice==1) {
				System.out.println("Enter Infix expression:");
				String expression = sc.nextLine();
				System.out.println(operation.infixToPostfix(expression));
			}else if(choice==2) {
				try {
				/*if the expression has symbols you are asked to input 
				 * a numeric value for this symbol so the expression can be evaluated
				 */
				System.out.println("Enter Postfix expression with spaces:");
				String expression = sc.nextLine();
				String[] arr = expression.split(" ");
				StringBuffer sb = new StringBuffer();
				for(int i=0; i<arr.length; i++) {
					if(arr[i].matches(".*[a-z].*") || arr[i].matches(".*[A-Z].*")) {
						String temp = arr[i];
						System.out.println("Enter the numeric value of the symbol"+arr[i]+":");
						String value = sc.nextLine();
						arr[i]=value;
						for(int j=i; j<arr.length; j++) {
							if(arr[j]==temp) {
								arr[j]=value;
							}
						}
					}
					sb.append(arr[i]);
					sb.append(" ");
				}
				String exp = sb.toString();
				System.out.println(operation.evaluate(exp));
				}
				catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				
			}else if(choice==3) {
				Exit=false;
			}else {
				System.out.println("Invalid input\n");
			}
		}
		sc.close();
	}
}
