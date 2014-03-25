package ui;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import root.Eve;
import root.EveSystem;

public class FilterBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private TypeSwitch buySwitch;
	private TypeSwitch sellSwitch;
	
	private SystemPanel buyDropdown;
	private SystemPanel sellDropdown;
	public FilterBar(){
		super();
		
		buyDropdown=new SystemPanel("Buy System:");
		buySwitch=new TypeSwitch();
		buyDropdown.add(buySwitch);
		JComboBox<EveSystem> buyBox=buyDropdown.getComboBox();
		
		sellDropdown=new SystemPanel("Sell System:");
		sellSwitch=new TypeSwitch();
		sellDropdown.add(sellSwitch);
		JComboBox<EveSystem> sellBox=sellDropdown.getComboBox();
		
		for(EveSystem s:Eve.sysList){
			buyBox.addItem(s);
			sellBox.addItem(s);
		}
		add(buyDropdown);
		add(sellDropdown);
		
	}
	public TypeSwitch getBuySwitch() {
		return buySwitch;
	}
	public TypeSwitch getSellSwitch() {
		return sellSwitch;
	}
	public SystemPanel getBuyDropdown() {
		return buyDropdown;
	}
	public SystemPanel getSellDropdown() {
		return sellDropdown;
	}

}
