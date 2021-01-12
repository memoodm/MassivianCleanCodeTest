package co.com.memoodm.roulette.model.api;

import java.util.List;

import lombok.Data;

@Data
public class GameResultDTO {

	private Integer selectedNumber;
	private List<PaymentDTO> payments;
	
	public GameResultDTO() {
		
	}
	
	public GameResultDTO(Integer selectedNumber,List<PaymentDTO> payments) {
		this.selectedNumber = selectedNumber;
		this.payments = payments;
	}
	
}
