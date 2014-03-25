package ui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import root.EveSystem;

public class SystemPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JComboBox<EveSystem> box;
	
	public SystemPanel(String text){
		super();
		label=new JLabel(text);
		box=new JComboBox<EveSystem>();
		box.setEditable(false);
		
		add(label);
		add(box);
	}
	public JComboBox<EveSystem> getComboBox(){
		return box;
	}

}
