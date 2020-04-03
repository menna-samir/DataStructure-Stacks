package eg.edu.alexu.csd.datastructure.stack.cs84;

public interface IExpressionEvaluator {
	public String infixToPostfix(String expression);
	public int evaluate(String expression);
}
