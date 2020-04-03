package eg.edu.alexu.csd.datastructure.stack.cs84;

import java.util.EmptyStackException;

public class Stack implements IStack{
	private int size=0;
	private Object[] stk = new Object[1000];
	private int t=-1;
	
	/**
	 * Removes the element at the top of stack and returns that element.
	 *
	 * @return top of stack element, or through exception if empty
	 */
	public Object pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}else {
			Object e = stk[t];
			stk[t]=0;
			t=t-1;
			size--;
			return e;
		}
	}

	/**
	 * Get the element at the top of stack without removing it from stack.
	 *
	 * @return top of stack element, or through exception if empty
	 */
	public Object peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}else {
			return stk[t];
		}
	}

	/**
	 * Pushes an item onto the top of this stack.
	 *
	 * @param object
	 * to insert
	 */
	public void push(Object element) {
		if(size==1000) {
			throw new StackOverflowError();
		}else {
			t=t+1;
			size++;
			stk[t]=element;
		}
	}

	/**
	 * Tests if this stack is empty
	 *
	 * @return true if stack empty
	 */
	public boolean isEmpty() {
		if(t<0 || size==0) {
			return true;
		}else {
			return false;	
		}
	}

	/**
	 * Returns the number of elements in the stack.
	 *
	 * @return number of elements in the stac
	 */
	public int size() {
		return size;
	}

}
