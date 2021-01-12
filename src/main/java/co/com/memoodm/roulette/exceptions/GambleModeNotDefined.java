package co.com.memoodm.roulette.exceptions;

public class GambleModeNotDefined extends RuntimeException {

	private static final long serialVersionUID = 1834998363480611440L;

	public GambleModeNotDefined() {
		super("Gamble mode not defined");
	}
}
