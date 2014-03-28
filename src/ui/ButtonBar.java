package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import root.EveSystem;

public class ButtonBar extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JButton resultButton;
	private JButton refreshButton;
	private JButton export;
	private OATF oatf;
	private FilterBar fBar;
	public ButtonBar(FilterBar fBar, OATF oatf){
		super();
		this.oatf=oatf;
		this.fBar=fBar;
		resultButton=new JButton("Find Results");
		resultButton.addActionListener(this);
		refreshButton=new JButton("Refresh Data");
		refreshButton.addActionListener(this);
		export=new JButton("Export Data");
		
		add(resultButton);
		add(refreshButton);
		add(export);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==resultButton){
			EveSystem buyS=(EveSystem)fBar.getBuyDropdown().getComboBox().getSelectedItem();
			int btype=fBar.getBuySwitch().getSelectedType();

			EveSystem sellS=(EveSystem)fBar.getSellDropdown().getComboBox().getSelectedItem();
			int stype=fBar.getSellSwitch().getSelectedType();
			
			oatf.handle(new GetResultRequestEvent(buyS,btype,sellS,stype,10));
			
		}else if(e.getSource()==refreshButton){
			oatf.handle(new RefreshAllDataEvent());
		}
		
		else if(e.getSource()==export){
			oatf.handle(new ExportDataEvent());
		}
		
	}

}
