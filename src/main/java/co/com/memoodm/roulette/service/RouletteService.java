package co.com.memoodm.roulette.service;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import co.com.memoodm.roulette.exceptions.RouletteDontExistException;
import co.com.memoodm.roulette.model.application.Roulette;
import co.com.memoodm.roulette.persistence.dao.RouletteRepository;

@Service
@Transactional
public class RouletteService {

	@Autowired
	private RouletteRepository rouletteRepository;

	public Map<String, Roulette> getAll() {
		
		return rouletteRepository.getAll();
	}
	
	public Roulette getById(String rouletteId) {
		Roulette roulette = rouletteRepository.getById(rouletteId);
		Assert.notNull(roulette,new RouletteDontExistException().getMessage());
		
		return roulette;
	}

	public String createNew() {
		Roulette roulette = new Roulette(UUID.randomUUID().toString(), false);
		this.save(roulette);
		
		return roulette.getId();
	}

	public void activateRoulete(Roulette roulette) {
		roulette.setActive(true);
	}

	public void deactivateRoulete(Roulette roulette) {
		roulette.setActive(false);
	}

	public void cleanGamblesAndResults(Roulette roulette) {
		roulette.setGambles(new LinkedList<>());
		roulette.setSelectedNumber(null);
	}

	public void executeThrowBallEvent(Roulette roulette) {
		roulette.executeThrowBallEvent();
	}
	
	public void save(Roulette roulette) {
		rouletteRepository.save(roulette);
	}
	
}
