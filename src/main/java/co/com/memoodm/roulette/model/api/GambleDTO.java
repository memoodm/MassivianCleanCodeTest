package co.com.memoodm.roulette.model.api;

import co.com.memoodm.roulette.utils.GambleModesEnum;
import lombok.Data;

@Data
public class GambleDTO {

	private String rouletteId;
	private Double money;
	private GambleModesEnum mode;
	private String item;
	
	public GambleDTO() {
		
	}
	
	public GambleDTO(String rouletteId, Double money, GambleModesEnum mode, String item) {
		super();
		this.rouletteId = rouletteId;
		this.money = money;
		this.mode = mode;
		this.item = item;
	}
	
	
	
}
