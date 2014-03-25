package ui;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import root.*;

public class ResultsTable extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel tModel;
	private JPanel panel;
	private Vector<String> c;

	private Vector<Vector<Object>> results; 
	public ResultsTable(){
		super();
		setLayout(new BorderLayout());

		c=new Vector<String>();
		String[] c_={"Item Name","Buy Price","Sell Price","Tax","Net","Margin","Sales","MDPE"};
		for(String x:c_){
			c.add(x);
		}

		results=new Vector<Vector<Object>>();		
		@SuppressWarnings("rawtypes")
		final Class[] classes={String.class,Double.class,Double.class,Double.class,Double.class,Double.class,Long.class,Double.class};
		DefaultTableModel model=new DefaultTableModel(results,c){
			private static final long serialVersionUID = 1L;
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		        if (columnIndex < classes.length) 
		            return classes[columnIndex];
		        return super.getColumnClass(columnIndex);
		    }
		};

		panel=new JPanel(new GridLayout());
		table=new JTable(model);
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		tModel=(DefaultTableModel)table.getModel();
		
		
		JScrollPane jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.add(jsp);
		add(panel,BorderLayout.CENTER);
		add(table.getTableHeader(), BorderLayout.NORTH);
	}

	public void generateTable(ArrayList<Item> items, EveSystem buySys,int buyType,EveSystem sellSys, int sellType, int min){
		results.clear();
		for(Item i:items){

			String name=i.getName();
			double bPrice=0;
			double sPrice=0;
			double tax=0;
			double net=0;
			long margin=0;
			long sales=0;
			long MDPE=0;

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
			net=sPrice-bPrice-Math.ceil(tax);

			if((net/sPrice)*100>=min){
				margin=(long) Math.floor((net/sPrice)*100);
				sales=i.getSVR(sellSys);
				MDPE=(long) (sales*net);

				Vector<Object> row=new Vector<Object>();
				row.add(name);
				row.add(new Double(bPrice));
				row.add(new Double(sPrice));
				row.add(new Double(Math.ceil(tax)));
				row.add(new Double(net));
				row.add(new Double(margin));
				row.add(new Long(sales));
				row.add(new Double(MDPE));

				results.add(row);
			}
		}
		tModel.fireTableDataChanged();
	}

}
