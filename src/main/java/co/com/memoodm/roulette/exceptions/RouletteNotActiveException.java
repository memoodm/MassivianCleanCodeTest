package co.com.memoodm.roulette.exceptions;

public class RouletteNotActiveException extends RuntimeException{

	private static final long serialVersionUID = 3008542011179204079L;

	public RouletteNotActiveException() {
		super("The selected roulette is not active");
	}
}
