package co.com.memoodm.roulette.persistence.dao;

import java.util.Map;

import co.com.memoodm.roulette.model.application.Roulette;

public interface RouletteRepository {

	public Map<String,Roulette> getAll();
	public Roulette getById(String id);
	public void save(Roulette roulette);
	
}
