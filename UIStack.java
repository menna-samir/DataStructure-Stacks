package eg.edu.alexu.csd.datastructure.stack.cs84;

import java.util.Scanner;

public class UIStack {
public static void main(String[] args) {
	Stack S = new Stack();
	Scanner sc = new Scanner(System.in);
	Boolean Exit = true;
	while(Exit) {
		System.out.println("Choose Operation:\n1: Push\n2: Pop\n3: Peek\n4: Get size\n5: Check if empty\n6: Exit");
		int choice = sc.nextInt();
		if(choice==1) {
			try {
				System.out.println("Choose input type:\n1: integer\n2: string\n3: character\n4: float");
					int choice1 = sc.nextInt();
					if(choice1==1) {
						System.out.println("Enter the input:");
						int element = sc.nextInt();
						S.push(element);
					}else if(choice1==2) {
						System.out.println("Enter the input:");
						String element = sc.nextLine();
						S.push(element);
					}else if(choice1==3) {
						System.out.println("Enter the input:");
						char element = sc.next().charAt(0);
						S.push(element);
					}else if(choice1==4) {
						System.out.println("Enter the input:");
						float element = sc.nextFloat();
						S.push(element);
					}else {
						System.out.println("wrong input");
					}
			}
			catch (Exception StackOverflowError) {
				System.out.println("The stack is full");
			}
		}else if(choice==2) {
			try {
				System.out.println(S.pop());	
			}
			catch (Exception EmptyStackException) {
				System.out.println("The stack is Empty");
			}
		}else if(choice==3) {
			try {
				System.out.println(S.peek());	
			}
			catch (Exception EmptyStackException) {
				System.out.println("The stack is Empty");
			}
		}else if(choice==4) {
			System.out.println("Size of stack ="+S.size());
		}else if(choice==5) {
			if(S.isEmpty()==true) {
				System.out.println("The Stack is empty.");
			}else {
				System.out.println("The Stack is not empty.");
			}
		}else if(choice==6) {
			Exit=false;
		}else {
			System.out.println("Invalid input\n");
		}
	}
	sc.close();
}
}
