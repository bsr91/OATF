package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import root.*;

public class ProgressPopup extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JProgressBar p;
	private JPanel c;
	private JLabel lbl;
	private JButton cancel;
	public ProgressPopup(ArrayList<Item> list){
		p=new JProgressBar();
		p.setMaximum(list.size()*3);
		p.setStringPainted(true);
		JPanel pP=new JPanel();
		pP.add(p);
		
		c=new JPanel(new BorderLayout());
		c.setSize(this.getPreferredSize());
		
		lbl=new JLabel("Importing Data");
		JPanel lP=new JPanel();
		lP.add(lbl);
		
		
		cancel=new JButton("Cancel");
		JPanel bP=new JPanel();
		bP.add(cancel);
		
		c.add(lP, BorderLayout.NORTH);
		c.add(pP, BorderLayout.CENTER);
		c.add(bP, BorderLayout.SOUTH);
		
		add(c);
		setSize(new Dimension(300,150));
		setVisible(true);
		
	}
	public JProgressBar getBar(){
		return p;
	}
	public void update(String text){
		p.setValue(p.getValue()+1);
		lbl.setText(text);
	}
	public JButton getCancel(){
		return cancel;
	}

}
