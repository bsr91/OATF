package root;

import java.io.Serializable;

public class Item implements Serializable{
	private static final long serialVersionUID = -6122011084370038358L;
	
	private int id;
	private String name;
	
	private double sellAtJ;
	private double buyAtJ;
	
	private double sellAtA;
	private double buyAtA;
	
	private double sellAtD;
	private double buyAtD;
	
	private long svrJ;
	private long svrA;
	private long svrD;
	
	public Item(int id,String name){
		this.id=id;
		this.name=name.trim();
	}
	public void reset(){
		sellAtJ=0;
		buyAtJ=0;
		sellAtA=0;
		buyAtA=0;
		sellAtD=0;
		buyAtD=0;
		svrJ=0;
		svrA=0;
		svrD=0;
	}
	@Override
	public String toString(){
		return name;
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getSellOrder(EveSystem s) {
		double sell=0;
		if(s.equals(Eve.Jita)){
			sell=sellAtJ;
		}else if(s.equals(Eve.Amarr)){
			sell=sellAtA;
		}else if(s.equals(Eve.Dodixie)){
			sell=sellAtD;
		}
		return Math.ceil(sell);
	}
	public double getBuyOrder(EveSystem s) {
		double buy=0;
		if(s.equals(Eve.Jita)){
			buy=buyAtJ;
		}else if(s.equals(Eve.Amarr)){
			buy=buyAtA;
		}else if(s.equals(Eve.Dodixie)){
			buy=buyAtD;
		}
		return Math.floor(buy);
	}
	public long getSVR(EveSystem s) {
		long svr=0;
		if(s.equals(Eve.Jita)){
			svr=svrJ;
		}else if(s.equals(Eve.Amarr)){
			svr=svrA;
		}else if(s.equals(Eve.Dodixie)){
			svr=svrD;
		}
		return svr;
	}
	public void setSellAt(EveSystem s,double sellAt) {
		if(s.equals(Eve.Jita)){
			sellAtJ=sellAt;
		}else if(s.equals(Eve.Amarr)){
			sellAtA=sellAt;
		}else if(s.equals(Eve.Dodixie)){
			sellAtD=sellAt;
		}
	}
	public void setBuyAt(EveSystem s,double buyAt) {
		if(s.equals(Eve.Jita)){
			buyAtJ=buyAt;
		}else if(s.equals(Eve.Amarr)){
			buyAtA=buyAt;
		}else if(s.equals(Eve.Dodixie)){
			buyAtD=buyAt;
		}
	}
	public void setSVR(EveSystem s,long svr) {
		if(s.equals(Eve.Jita)){
			svrJ=svr;
		}else if(s.equals(Eve.Amarr)){
			svrA=svr;
		}else if(s.equals(Eve.Dodixie)){
			svrD=svr;
		}
	}
}
