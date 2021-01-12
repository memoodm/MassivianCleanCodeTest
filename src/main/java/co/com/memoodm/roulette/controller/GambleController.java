package co.com.memoodm.roulette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.memoodm.roulette.model.api.GambleDTO;
import co.com.memoodm.roulette.service.GambleService;

@RestController
@RequestMapping("/api/gamble")
public class GambleController {
	
	@Autowired
	private GambleService gambleService;
	
	@PostMapping
	public ResponseEntity<?> setGamble(@RequestHeader("userId") String userId, @RequestBody GambleDTO gambleDTO){
		try {
			gambleService.add(userId,gambleDTO);
			
			return ResponseEntity.status(HttpStatus.OK).body(true);	
		}
		catch(Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
}
