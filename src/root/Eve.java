package root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Eve {
	private String isk;
	private String iskID;
	private String vol;
	private String volID;
	
	
	public static EveSystem Jita;
	public static EveSystem Dodixie;
	public static EveSystem Amarr;
	
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
	
	//checks an itemID is tradeable
	public boolean hasMarketData(Item i){
		if((getSellOrder(i,Jita))>0){
			return true;
		}else return false;
	}
	//retrieves lowest sell order for the item, in the system specified
	public double getSellOrder(Item i,EveSystem s){
		double r=0.0;
		String u=isk+s.getSysID()+iskID+i.getID();
		try{
			Document d=Jsoup.connect(u).get();
			for(Element e:d.select("sell")){
				double x=Double.parseDouble(e.select("min").text());
				r=x;
			}
		}catch(IOException x){
			x.printStackTrace();
		}
		return r;
	}
	
	
	
	//retrieves highest buy order for the item, in the system specified
	public double getBuyOrder(Item i,EveSystem s){
		double r=0.0;
		String u=isk+s.getSysID()+iskID+i.getID();
		try {
			Document d=Jsoup.connect(u).get();
			for(Element e:d.select("buy")){
				double x=Double.parseDouble(e.select("max").text());
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
			for(Element e:d.select("row")){
				long x=Long.parseLong(e.attr("volume"));
				r=x;
			}
		}catch(IOException x){
			x.printStackTrace();
		}		
		return r;
	}
}
