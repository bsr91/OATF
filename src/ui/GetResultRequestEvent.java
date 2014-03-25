package ui;


import root.*;

public class GetResultRequestEvent{
	private EveSystem buySystem;
	private EveSystem sellSystem;
	
	private int buyType;
	private int sellType;
	
	private int minmarg;
	public GetResultRequestEvent(EveSystem buySystem, int buyType, EveSystem sellSystem, int sellType, int minmarg){
		this.buySystem=buySystem;
		this.buyType=buyType;
		this.sellSystem=sellSystem;
		this.sellType=sellType;
		this.minmarg=minmarg;
	}

	public EveSystem getBuySystem() {
		return buySystem;
	}

	public EveSystem getSellSystem() {
		return sellSystem;
	}

	public int getBuyType() {
		return buyType;
	}

	public int getSellType() {
		return sellType;
	}
	public int getMinimumMargin(){
		return minmarg;
	}
}
