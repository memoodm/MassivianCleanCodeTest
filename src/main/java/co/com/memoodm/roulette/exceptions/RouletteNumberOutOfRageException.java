package co.com.memoodm.roulette.exceptions;

public class RouletteNumberOutOfRageException extends RuntimeException{
	
	private static final long serialVersionUID = 5622314004397896734L;

	public RouletteNumberOutOfRageException() {
		super("The selected roulette number is out of range");
	}
}
