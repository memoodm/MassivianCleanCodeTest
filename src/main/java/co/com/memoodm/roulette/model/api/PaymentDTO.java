package co.com.memoodm.roulette.model.api;

import lombok.Data;

@Data
public class PaymentDTO {
	
	private String user;
	private Double winAmount;
	
	public PaymentDTO() {
		
	}
	
	public PaymentDTO(String user,Double winAmount) {
		this.user = user;
		this.winAmount = winAmount;
	}
	
}
