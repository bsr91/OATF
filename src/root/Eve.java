package root;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ui.AppFrame;
import ui.ProgressPopup;

public class Eve {
	private String isk;
	private String iskID;
	private String vol;
	private String volID;
	
	
	public static final EveSystem Jita=new EveSystem("Jita","30000142","10000002");
	public static final EveSystem Dodixie=new EveSystem("Dodixie","30002659","10000032");
	public static final EveSystem Amarr=new EveSystem("Amarr","30002187","10000043");
	public static ArrayList<EveSystem> sysList;
	
	public static final int BUY=0;
	public static final int SELL=1;
	
	public Object lock;
	
	//constructor
	public Eve(){
		isk="http://api.eve-central.com/api/marketstat?usesystem=";
		iskID="&typeid=";
		
		vol="http://api.eve-marketdata.com/api/item_history2.xml?char_name=demo&days=2&region_ids=";
		volID="&type_ids=";
		
		sysList=new ArrayList<EveSystem>();
		sysList.add(Jita);
		sysList.add(Amarr);
		sysList.add(Dodixie);
		
		lock=new Object();
		
	}
	
	
	//populates Itemarray with the data requested
	public void populateMarketData(final ArrayList<Item> list, final AppFrame a){
		final ProgressPopup p=new ProgressPopup(list);
		p.setLocationRelativeTo(a);
		
		final MarketThread j=new MarketThread(Eve.Jita,list,this,p);
		final MarketThread am=new MarketThread(Eve.Amarr,list,this,p);
		final MarketThread d=new MarketThread(Eve.Dodixie,list,this,p);
		
		j.start();
		am.start();
		d.start();		
		
		p.getCancel().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==p.getCancel()){
					p.dispose();
					j.stopTask();
					am.stopTask();
					d.stopTask();
				}
			}
		});
	}
	
	//gets buy/max or sell/min for item in system
	public double getPrice(Item i,int type,EveSystem s){
		double r=0.0;
		String u=isk+s.getSysID()+iskID+i.getID();
		String[] sell={"sell","min"};
		String[] buy={"buy","max"};
		String[] t;
		if(type==Eve.SELL){
			t=sell;
		}else if(type==Eve.BUY){
			t=buy;
		}else return r;
		try{
			Document d=Jsoup.connect(u).get();
			for(Element e:d.select(t[0])){
				double x=Double.parseDouble(e.select(t[1]).text());
				r=x;
			}
		}catch(IOException x){
			x.printStackTrace();
		}
		return r;
	}	
	
	//retrieves last 24hr sales volumes for the item, in the region specified
	public long getSVR(Item i,EveSystem s){
		long r=0;
		String u=vol+s.getRegID()+volID+i.getID();
		try{
			Document d=Jsoup.connect(u).get();
			Element e=d.select("row").first();
			if(e!=null){
				long x=Long.parseLong(e.attr("volume"));
				r=x;
			}
		}catch(IOException x){
			x.printStackTrace();
		}		
		return r;
	}
}
