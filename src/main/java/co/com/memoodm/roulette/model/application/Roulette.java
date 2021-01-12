package co.com.memoodm.roulette.model.application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import co.com.memoodm.roulette.exceptions.RouletteNotActiveException;
import co.com.memoodm.roulette.utils.ApplicationConstants;
import lombok.Data;

@Data
public class Roulette implements Serializable {
	
	private static final long serialVersionUID = -5168326486128557598L;
	
	private String id;
	private Boolean active;
	private List<Gamble> gambles;
	private Integer selectedNumber;
	
	public Roulette(String id, Boolean active){
		this.id = id;
		this.active = active;
		this.gambles = new LinkedList<>();
	}

	public void validateActive() {
		if(!this.getActive()) {
			throw new RouletteNotActiveException();
		}
	}
	
	public void executeThrowBallEvent() {
		int fieldNumberResult = ThreadLocalRandom.current().nextInt(ApplicationConstants.ROULETTE_MIN_NUMBER_FIELD, ApplicationConstants.ROULETTE_MAX_NUMBER_FIELD + 1);
		setSelectedNumber(fieldNumberResult);
	}
	
}
