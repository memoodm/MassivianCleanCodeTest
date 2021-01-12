package co.com.memoodm.roulette.model.application;

import java.io.Serializable;
import java.util.Date;

import co.com.memoodm.roulette.exceptions.GambleExceedAmountException;
import co.com.memoodm.roulette.exceptions.RouletteNumberOutOfRageException;
import co.com.memoodm.roulette.utils.ApplicationConstants;
import co.com.memoodm.roulette.utils.DateUtil;
import lombok.Data;

@Data
public class GambleNumber implements Gamble,Serializable{

	private static final long serialVersionUID = -9216891679177120810L;
	
	private String user;
	private Integer rouletteNumber;
	private Double money;
	private String date;
	
	public GambleNumber(String user,Integer rouletterNumber, Double money) {
		this.setUser(user);
		this.setRouletterNumber(rouletterNumber);
		this.setMoney(money);
		this.setDate(DateUtil.dateToMassivianFormat(new Date()));
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setRouletterNumber(Integer rouletteNumber) {
		if(rouletteNumber<ApplicationConstants.ROULETTE_MIN_NUMBER_FIELD || rouletteNumber>ApplicationConstants.ROULETTE_MAX_NUMBER_FIELD) {
			throw new RouletteNumberOutOfRageException();
		}
		this.rouletteNumber = rouletteNumber;
	}
	
	public void setMoney(Double money) {
		if(money>ApplicationConstants.GAMBLE_LIMIT_MONEY) {
			throw new GambleExceedAmountException();
		}
		this.money = money;
	}

	@Override
	public String obtainUser() {
		
		return this.getUser();
	}
	
	@Override
	public Boolean isWinner(Integer number) {
		
		return number.intValue() == this.rouletteNumber.intValue();
	}

	@Override
	public Double generatePaymentAmount() {
		
		return  ApplicationConstants.GAMBLE_BYNUMBER_MULTIPLIER * this.money;
	}
	
}
