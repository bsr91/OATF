package root;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TradeFinder {
	private Eve eve;
	private ArrayList<Item> itemArray; 
	
	private File itemF;
	
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
		itemF=new File("itemdb.txt");
		
		createItemDatabase();
	}


	
	//reads filtered+prepared item name/id list from file into array
	private void createItemDatabase() throws IOException{
		BufferedReader in=new BufferedReader(new FileReader(itemF));
		try{
			String line;
			while((line=in.readLine())!=null && (line.length()>0)){
				String[] parts=line.split(delimit);
				int id=Integer.parseInt(parts[0]);
				String name=parts[1];
				Item i=new Item(id,name);
				itemArray.add(i);
				
				System.out.println(id+": "+name);
			}
		}finally{
			in.close();
		}
		
	}
	
	//get itemID list
	public synchronized ArrayList<Item> getItemIDList(){
		return itemArray;
	}
}

