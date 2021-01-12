package co.com.memoodm.roulette.exceptions;

public class GambleExceedAmountException extends RuntimeException{

	private static final long serialVersionUID = 2604774068495406542L;

	public GambleExceedAmountException() {
		super("The amount to gamble exceed the allowed limit");
	}
}
