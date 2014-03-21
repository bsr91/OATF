package root;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class TradeFinder {
	private Eve eve;
	private ArrayList<Item> itemArray; 
	
	private File itemNameFile;
	private File itemXMLFile;
	
	private final String delimit="=";
	
	@SuppressWarnings("unused")
	private String[] filter={"Blueprint","Shadow","True","Domination","Ammatar Navy","Blood","Dread","Guristas","Khanid","Syndicate","Thukker",
			" Tag","Gistii","Corpii","Pithi","Centii","Coreli","Gistum","Corpum","Pithum","Centum","Corelum","Gist","Corpus","Pith","Centus","Core",
			"Mizuro","Hakim","Gotan","Tobias","Tairei","Selynne","Ahremen","Draclira","Kaikka","Thon","Vepas","Chelm","Brynn","Tuvan","Setele","Cormack",
			"Container","Amarr","Gallente","Minmatar","Stargate","Sun","Planet","Clone","Civilian","Character","Player","Bonus","DNA","Wreckage","Wreck",
			"Corpse","Advanced","Standard","Improved","Strong","Synth","Command","CONCORD","Broken","Extractor","Facility","Crates","Group","(","Zainou",
			"Inherent","Expert","Women","Men's","Link","Guardian","Angel","Apis","Jove","R.Db","R.A.M.-","Datacore","Engineering","Physics","Specialization",
			"Processing","Sansha","Array","Tower","NEO YC","Alliance","Capital","Sentient","Militia","Hardwiring","Poteque","Eifyr","Mordu","Tournament",
			"Hive","Rigging","Bunker","TEST","Strain","Incognito","Esoteric","Occult","Cryptic","'s","Cloud","Elite","Praktor","State","Divine","Chief",
			"Cosmos","Yan Jung","LCO ","Psycho","Cyber","Station","Debris","Talocan","Alvi","Alvum","Apis","Citadel","Veldspar","Spodumain","Pyroxes",
			"Scordite","Plagioclase","Module","Sentry","DED ","EoM ","Leader","Commander","Modified"};
	
	//constructor
	public TradeFinder() throws IOException{
		eve=new Eve();
		itemArray=new ArrayList<Item>();
		itemNameFile=new File("itemdb.txt");
		itemXMLFile=new File("itemdb.xml");
		
		
		importItemDatabase();
		eve.populateMarketData(getItemIDList());

		try{
			synchronized(eve.lock){
				while(!eve.hasPopulatedData()){
					eve.lock.wait();
				}
			}
		}catch(InterruptedException x){
			x.printStackTrace();
		}
		System.out.println("Exporting Database");
		exportItemDB();

	}

	//exports item database as xml Item objects to preserve data
	private void exportItemDB(){
		try{
			if(itemXMLFile.exists()){
				File temp=new File("temp.xml");
				temp.createNewFile();

				itemXMLFile.delete();
				temp.renameTo(itemXMLFile);
			}else{
				itemXMLFile.createNewFile();
			}
			XStream xml=new XStream(new StaxDriver());
			BufferedWriter out=new BufferedWriter(new FileWriter(itemXMLFile));

			for(Item item:itemArray){
				String x=xml.toXML(item);
				out.write(x);
				out.newLine();
			}
			out.close();
		}catch(IOException x){
			x.printStackTrace();
		}
	}
	
	//imports Item database from xml and fills item array
	private void importItemDatabase(){
		try{
			XStream xml=new XStream(new StaxDriver());
			BufferedReader in=new BufferedReader(new FileReader(itemXMLFile));
			String line;
			while((line=in.readLine())!=null){
				Item i=(Item)xml.fromXML(line);
				itemArray.add(i);
			}
			in.close();
		}catch(IOException x){
			x.printStackTrace();
		}

	}



	
	//reads filtered+prepared item name/id list from file into array
	@SuppressWarnings("unused")
	private void createItemDatabase(){
		try{
			BufferedReader in=new BufferedReader(new FileReader(itemNameFile));
			String line;
			while((line=in.readLine())!=null && (line.length()>0)){
				String[] parts=line.split(delimit);
				int id=Integer.parseInt(parts[0]);
				String name=parts[1];
				Item i=new Item(id,name);
				itemArray.add(i);
			}
			in.close();
		}catch(IOException x){
			x.printStackTrace();
		}
		
	}
	
	//get itemID list
	public synchronized ArrayList<Item> getItemIDList(){
		return itemArray;
	}
}

