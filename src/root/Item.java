package root;

public class Item {
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
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getSellAt(EveSystem s) {
		double sell=0;
		if(s.equals(Eve.Jita)){
			sell=sellAtJ;
		}else if(s.equals(Eve.Amarr)){
			sell=sellAtA;
		}else if(s.equals(Eve.Dodixie)){
			sell=sellAtD;
		}
		return sell;
	}
	public double getBuyAt(EveSystem s) {
		double buy=0;
		if(s.equals(Eve.Jita)){
			buy=buyAtJ;
		}else if(s.equals(Eve.Amarr)){
			buy=buyAtA;
		}else if(s.equals(Eve.Dodixie)){
			buy=buyAtD;
		}
		return buy;
	}
	public long getSvr(EveSystem s) {
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
	public void setSvr(EveSystem s,long svr) {
		if(s.equals(Eve.Jita)){
			svrJ=svr;
		}else if(s.equals(Eve.Amarr)){
			svrA=svr;
		}else if(s.equals(Eve.Dodixie)){
			svrD=svr;
		}
	}
}
