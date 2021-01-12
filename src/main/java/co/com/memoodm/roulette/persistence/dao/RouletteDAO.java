package co.com.memoodm.roulette.persistence.dao;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import co.com.memoodm.roulette.model.application.Roulette;

@Repository
public class RouletteDAO implements RouletteRepository{

	@Value("${persistenceKey_roulette_id}")
	private String KEY;

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	private HashOperations hashOperations;
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public Map<String, Roulette> getAll() {
		
		return hashOperations.entries(KEY);
	}

	@Override
	public Roulette getById(String id) {
		
		return (Roulette) hashOperations.get(KEY,id);
	}

	@Override
	public void save(Roulette roulette) {
		hashOperations.put(KEY, roulette.getId(), roulette);
	}

}
