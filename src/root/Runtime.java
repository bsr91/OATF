package root;

import java.io.IOException;

public class Runtime {
	public static void main(String[] args){
		try {
			new TradeFinder();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
