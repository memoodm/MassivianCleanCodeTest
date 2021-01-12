package co.com.memoodm.roulette.model.application;

import co.com.memoodm.roulette.exceptions.RouletteColorInvalidException;
import co.com.memoodm.roulette.utils.ApplicationConstants;
import co.com.memoodm.roulette.utils.DateUtil;
import co.com.memoodm.roulette.utils.RouletteColorsEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import co.com.memoodm.roulette.exceptions.GambleExceedAmountException;

@Data
public class GambleColor implements Gamble,Serializable{

	private static final long serialVersionUID = -5790824279242967233L;
	
	private String user;
	private String rouletteColor;
	private Double money;
	private String date;
	
	public GambleColor(String user,String color,Double money) {
		this.setUser(user);
		this.setRouletteColor(color);
		this.setMoney(money);
		this.setDate(DateUtil.dateToMassivianFormat(new Date()));
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setRouletteColor(String color) {
		List<RouletteColorsEnum> colorsEnumList = Arrays.asList( RouletteColorsEnum.values() );
		if(!colorsEnumList.stream().anyMatch(e->color.equalsIgnoreCase(e.toString()))) {
			throw new RouletteColorInvalidException();
		}
		this.rouletteColor = color;
	}
	
	public void setMoney(Double money) {
		if(money > ApplicationConstants.GAMBLE_LIMIT_MONEY) {
			throw new GambleExceedAmountException();
		}
		this.money = money;
	}	
	
	@Override
	public String obtainUser() {
		
		return this.getUser();
	}
	
	@Override
	public Boolean isWinner(Integer selectedRouletteNumber) {
		String colorSelected = selectedRouletteNumber%2==0 ? RouletteColorsEnum.BLACK.toString() : RouletteColorsEnum.RED.toString();
		
		return colorSelected.equalsIgnoreCase(this.rouletteColor);
	}
	
	@Override
	public Double generatePaymentAmount() {
		
		return ApplicationConstants.GAMBLE_BYCOLOR_MULTIPLIER * this.money;
	}

}
