package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import root.Eve;

public class TypeSwitch extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private ButtonGroup grp;
	private JRadioButton buy;
	private JRadioButton sell;
	private int selected;
	
	public TypeSwitch(){
		super();
		buy=new JRadioButton("Buy Order");
		sell=new JRadioButton("Sell Order");
		grp=new ButtonGroup();
		grp.add(buy);
		grp.add(sell);
		
		buy.addActionListener(this);
		sell.addActionListener(this);
		
		add(buy);
		add(sell);
	}
	public int getSelectedType(){
		return selected;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==buy){
			selected=Eve.BUY;
		}else if(e.getSource()==sell){
			selected=Eve.SELL;
		}
	}
	

}
