package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel c;
	public AppFrame(){
		super();
		
		c=new JPanel(new BorderLayout());
		setSize(new Dimension(1200,1000));
		setContentPane(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
