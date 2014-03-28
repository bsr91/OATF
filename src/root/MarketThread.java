package root;

import java.util.ArrayList;

import ui.ProgressPopup;

public class MarketThread extends Thread {
	private EveSystem sys;
	private ArrayList<Item> list;
	private Eve eve;
	private ProgressPopup p;
	private boolean stop;

	//Constructor
	public MarketThread(EveSystem sys,ArrayList<Item> list,Eve eve, ProgressPopup p){
		this.sys=sys;
		this.list=list;
		this.eve=eve;
		this.p=p;
	}
	public void run(){
		stop=false;
		for(Item i:list){
			if(!stop){
				i.setBuyAt(sys, eve.getPrice(i,Eve.BUY,sys));
				i.setSellAt(sys, eve.getPrice(i,Eve.SELL,sys));
				i.setSVR(sys,eve.getSVR(i,sys));
				System.out.println(sys+": "+i.getName());
				p.update(sys+": "+i.getName());
			}else break;
		}
		System.out.println("Thread "+sys.getName()+" ready.");

	}
	public void stopTask(){
		stop=true;
	}
}
