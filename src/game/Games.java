package game;

import java.util.ArrayList;
import java.util.Date;

public class Games <T> {
	public ArrayList<T> games = new ArrayList<>();
	
	public Games() {
		
	}
	public ArrayList<T> getGames() {
		return games;
	}

	public void setGames(ArrayList<T> games) {
		this.games = games;
	}
	public  T getByID(int iD) {
		for(int i=0;i<games.size();i++) {
			if(games.get(i).equals(iD)) return (T) getGames().get(i);
		}
		return null;
	}
	
	
}
