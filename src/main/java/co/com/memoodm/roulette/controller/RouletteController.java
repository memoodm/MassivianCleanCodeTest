package co.com.memoodm.roulette.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.memoodm.roulette.model.api.GameResultDTO;
import co.com.memoodm.roulette.model.api.PaymentDTO;
import co.com.memoodm.roulette.model.application.Gamble;
import co.com.memoodm.roulette.model.application.Roulette;
import co.com.memoodm.roulette.service.GambleService;
import co.com.memoodm.roulette.service.RouletteService;

@RestController
@RequestMapping("/api/roulette")
public class RouletteController {

	@Autowired
	private RouletteService rouletteService;
	
	@Autowired
	private GambleService gambleService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		try {
			Map<String, Roulette> rulettes = rouletteService.getAll();
			
			return ResponseEntity.status(HttpStatus.OK).body(rulettes);
		}
		catch(Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> createRoulette(){
		try {
			String rouletteId = rouletteService.createNew();
			
			return ResponseEntity.status(HttpStatus.OK).body(rouletteId);
		}
		catch(Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping
	@RequestMapping("/open")
	public ResponseEntity<?> openRoulette(@RequestParam String rouletteId){
		try {
			Roulette roulette = rouletteService.getById(rouletteId);
			rouletteService.cleanGamblesAndResults(roulette);
			rouletteService.activateRoulete(roulette);
			rouletteService.save(roulette);
			
			return ResponseEntity.status(HttpStatus.OK).body(true);
		}
		catch(Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping
	@RequestMapping("/close")
	public ResponseEntity<?> closeRoulette(@RequestParam String rouletteId){
		try {
			Roulette roulette = rouletteService.getById(rouletteId);
			roulette.validateActive();
			rouletteService.deactivateRoulete(roulette);
			rouletteService.executeThrowBallEvent(roulette);
			rouletteService.save(roulette);
			List<Gamble> winnerGambles = gambleService.getWinnerGambles(roulette);
			List<PaymentDTO> payments = gambleService.payGambles(winnerGambles);
			GameResultDTO gameResultDTO = new GameResultDTO(roulette.getSelectedNumber(),payments);
			
			return ResponseEntity.status(HttpStatus.OK).body(gameResultDTO);
		}
		catch(Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
}
