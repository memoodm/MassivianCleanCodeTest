package co.com.memoodm.roulette.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.memoodm.roulette.exceptions.GambleModeNotDefined;
import co.com.memoodm.roulette.model.api.GambleDTO;
import co.com.memoodm.roulette.model.api.PaymentDTO;
import co.com.memoodm.roulette.model.application.*;
import co.com.memoodm.roulette.persistence.dao.RouletteRepository;

@Service
@Transactional
public class GambleService {

	@Autowired
	private RouletteService rouletteService;
	
	public void add(String userId,GambleDTO gambleDTO) {	
		Roulette roulette = rouletteService.getById(gambleDTO.getRouletteId());
		roulette.validateActive();
		Gamble gamble = this.generateGamble(userId,gambleDTO);
		roulette.getGambles().add(gamble);
		rouletteService.save(roulette);
	}
	
	public List<Gamble> getWinnerGambles(Roulette roulette) {
		List<Gamble> winnerGambles = roulette.getGambles()
				.stream()
				.filter(e->e.isWinner(roulette.getSelectedNumber()))
				.collect(Collectors.toList());
		
		return winnerGambles;
	}
	
	public List<PaymentDTO> payGambles(List<Gamble> winnerGambles) {
		List<PaymentDTO> payGambles = winnerGambles
				.stream()
				.map((e)->{ return new PaymentDTO(e.obtainUser(), e.generatePaymentAmount());})
				.collect(Collectors.toList());
		
		return payGambles;
	}
	
	private Gamble generateGamble(String userId, GambleDTO gambleDTO) {
		switch(gambleDTO.getMode()) {
			case BY_COLOR: 
				
				return new GambleColor(
						userId,
						gambleDTO.getItem(),
						gambleDTO.getMoney()
						);
				
			case BY_NUMBER:
				
				return new GambleNumber(
						userId,
						Integer.parseInt(gambleDTO.getItem()),
						gambleDTO.getMoney());
				
			default:
				throw new GambleModeNotDefined();
		}
	}

	

	
	
}
