package root;

public class EveSystem {
	private String name;
	private String sys;
	private String reg;
	
	public EveSystem(String name,String sys, String reg){
		this.name=name;
		this.sys=sys;
		this.reg=reg;
	}

	public String getName() {
		return name;
	}

	public String getSysID() {
		return sys;
	}

	public String getRegID() {
		return reg;
	}
	@Override
	public String toString(){
		return name;
	}
}
