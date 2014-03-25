package root;

import java.util.ArrayList;

public class MarketThread extends Thread {
	private EveSystem sys;
	private ArrayList<Item> list;
	private boolean isReady;
	private Eve eve;

	//Constructor
	public MarketThread(EveSystem sys,ArrayList<Item> list,Eve eve){
		this.sys=sys;
		this.list=list;
		this.eve=eve;
		this.isReady=false;
	}
	public void run(){
		for(Item i:list){
			i.setBuyAt(sys, eve.getPrice(i,Eve.BUY,sys));
			i.setSellAt(sys, eve.getPrice(i,Eve.SELL,sys));
			i.setSVR(sys,eve.getSVR(i,sys));
		}
		isReady=true;
		System.out.println("Thread "+sys.getName()+" ready.");

	}
	public boolean isReady(){
		return isReady;
	}
}
