package root;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Eve {
	private String isk;
	private String iskID;
	private String vol;
	private String volID;
	
	
	public static EveSystem Jita;
	public static EveSystem Dodixie;
	public static EveSystem Amarr;
	
	public static final int BUY=0;
	public static final int SELL=1;
	
	//constructor
	public Eve(){
		isk="http://api.eve-central.com/api/marketstat?usesystem=";
		iskID="&typeid=";
		
		vol="http://api.eve-marketdata.com/api/item_history2.xml?char_name=demo&days=2&region_ids=";
		volID="&type_ids=";
		
		Jita=new EveSystem("Jita","30000142","10000002");
		Amarr=new EveSystem("Amarr","30002187","10000043");
		Dodixie=new EveSystem("Dodixie","30002659","10000032");
		
		
	}
	
	//checks an itemID has been traded in last 48hrs
	public boolean hasMarketData(Item i){
		if((getSVR(i,Jita))>10){
			return true;
		}else return false;
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
