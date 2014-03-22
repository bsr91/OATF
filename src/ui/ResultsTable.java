package ui;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;

import root.*;

public class ResultsTable extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private JPanel panel;
	private String[] c={"Item Name","Buy Price","Sell Price","Tax","Net","Margin","Sales","MDPE"};

	private ArrayList<ArrayList<Object>> results; 
	public ResultsTable(){
		super();
		setLayout(new BorderLayout());

		results=new ArrayList<ArrayList<Object>>();		

		panel=new JPanel(new GridLayout());


		add(panel, BorderLayout.CENTER);
	}

	public void generateTable(ArrayList<Item> items, EveSystem buySys,int buyType,EveSystem sellSys, int sellType, int min){
		for(Item i:items){
			
			String name=i.getName();
			double bPrice=0;
			double sPrice=0;
			double tax=0;
			double net=0;
			String margin="0";
			long sales=0;
			double MDPE=0;

			double tax1=0;
			double tax2=0;

			if(buyType==Eve.BUY){
				bPrice=i.getBuyOrder(buySys);
				tax1=0.008*bPrice;
			}else if(buyType==Eve.SELL){
				bPrice=i.getSellOrder(buySys);
				tax1=0;
			}

			if(sellType==Eve.BUY){
				sPrice=i.getBuyOrder(sellSys);
				tax2=0.008*sPrice;
			}else if(sellType==Eve.SELL){
				sPrice=i.getSellOrder(sellSys);
				tax2=0.017*sPrice;
			}


			tax=tax1+tax2;
			net=sPrice-bPrice-tax;

			if(net/sPrice>=min){
				margin=percentage((net/sPrice));
				sales=i.getSVR(sellSys);
				MDPE=sales*net;

				ArrayList<Object> row=new ArrayList<Object>();
				row.add(name);
				row.add(bPrice);
				row.add(sPrice);
				row.add(tax);
				row.add(net);
				row.add(margin);
				row.add(MDPE);

				results.add(row);
			}
		}
		table=new JTable((Object[][]) results.toArray(),c);
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		panel.add(table);
		add(table.getTableHeader(), BorderLayout.NORTH);
	}
	private String percentage(double d){
		String r="";
		int d_1=(int)Math.floor(d*100);
		r=d_1+"%";
		return r;
	}

}
