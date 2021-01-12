package co.com.memoodm.roulette.exceptions;

public class RouletteColorInvalidException extends RuntimeException{

	private static final long serialVersionUID = -7363214535287927433L;

	public RouletteColorInvalidException() {
		super("The selected color is invalid");
	}
}
