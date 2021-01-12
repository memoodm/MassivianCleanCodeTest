package co.com.memoodm.roulette.exceptions;

public class RouletteDontExistException extends RuntimeException{

	private static final long serialVersionUID = 6115443507130418604L;

	public RouletteDontExistException() {
		super("Roulette don't exist");
	}
}
