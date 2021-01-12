package co.com.memoodm.roulette.model.application;

public interface Gamble {
	
	public String obtainUser();
	public Boolean isWinner(Integer number);
	public Double generatePaymentAmount();
	
	public String getUser();
	public Double getMoney();
	public String getDate();
	public void setUser(String user);
	public void setMoney(Double money);
	public void setDate(String date);
	
}
