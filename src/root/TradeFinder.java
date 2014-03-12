package root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class TradeFinder {
	private URL typeIDURL;
	private Eve eve;
	private ArrayList<Item> itemArray; 
	
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
		typeIDURL=new URL("http://eve-files.com/chribba/typeid.txt");
		
		//thread for populating itemDB
		Thread t=new Thread(new Runnable(){
			public void run(){
				try {
					populateItemDatabase();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		});
		t.run();
		/**
		 * TODO Experimental zone
		 */
	}


	//populate item database ArrayList (no BPOs)
	private void populateItemDatabase() throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(typeIDURL.openStream()));
		Date start=new Date();
		ArrayList<String> page=new ArrayList<String>();
		try{
			String line;
			while((line=in.readLine())!=null){
				//item filter
				if(lineIsFiltered(line)){

					String[] split=line.split(" ");
					String id=split[0];
					String name="";
					for(String p:split){
						if(!p.equalsIgnoreCase(id)&&(p.length()>0)){
							name=name+p+" ";
						}
					}
					name.trim();
					//checks id string is integer, filters DUST514
					if(isInteger(id)&&(id.length()<6)){
						page.add(id+"@"+name);
					}
				}
			}
		}finally{
			in.close();

			int c=0;
			for(String l:page){
				String id=l.split("@")[0];
				String name=l.split("@")[1].trim();
				Item item=new Item(Integer.parseInt(id),name);
				//checks Item has sales volume (Jita)
				long svr=eve.getSVR(item, Eve.Jita);
				if(svr>10){
					c++;
					getItemIDList().add(item);
					item.setSvr(Eve.Jita, svr);
					System.out.println(c+": "+item.getName());
				}
			}
			page.clear();
			Date end=new Date();
			long diff=end.getTime()-start.getTime();
			System.out.println("Database Populated: Time taken = "+diff/60000+" mins");
		}
	}
	
	private boolean lineIsFiltered(String line){
		for(String f:filter){
			if(line.contains(f)){
				return false;
			}
		}
		return true;
	}

	//catch method boolean int test
	private boolean isInteger(String i){
		try{
			Integer.parseInt(i);
		}catch(NumberFormatException x){
			return false;
		}
		return true;
	}
	
	//get itemID list
	public synchronized ArrayList<Item> getItemIDList(){
		return itemArray;
	}
}

