package ui;

import java.awt.BorderLayout;
import java.io.IOException;

import root.*;

public class OATF {
	private AppFrame app;
	private ResultsTable table;
	private FilterBar fBar;
	private TradeFinder tf;
	private ButtonBar bb;
	public OATF() throws IOException{
		tf=new TradeFinder(this);
		
		
		app=new AppFrame();
		table=new ResultsTable();	
		fBar=new FilterBar();
		bb=new ButtonBar(fBar,this);
		
		
		
		app.getContentPane().add(fBar,BorderLayout.NORTH);
		app.getContentPane().add(bb,BorderLayout.SOUTH);
		app.getContentPane().add(table,BorderLayout.CENTER);
		
		app.setVisible(true);
	}
	public static void main(String[] args){
		try {
			new OATF();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//filters events to correct methods
	public void handle(Object e){
		if(e.getClass().equals(GetResultRequestEvent.class)){
			getResults((GetResultRequestEvent)e);
		}else if(e.getClass().equals(RefreshAllDataEvent.class)){
			tf.getData(app);
		}else if(e.getClass().equals(ExportDataEvent.class)){
			tf.exportItemDB();
		}
	}
	
	//fills table (fired from JButton in FilterBar)
	private void getResults(GetResultRequestEvent e){
		table.generateTable(tf.getItemIDList(),
				e.getBuySystem(), e.getBuyType(),
				e.getSellSystem(), e.getSellType(),
				e.getMinimumMargin());
	}
}
