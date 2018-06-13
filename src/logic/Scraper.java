package logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import data.DList;
import data.Game;
import data.Games;
import data.Rating;
import data.Window;
import gui.Welcome;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class Scraper {
	String ID;
	long profile;
	private static final String LINK="https://store.steampowered.com/app/";
	private static String[] keys = {"name","release_date","added","tags","price", "discount_original_price","discount_pct","reviews_total","reviews_percent","capsule"};
	public Scraper(String id,long profile) {
		this.ID =id;
		this.profile=profile;
		URL url=null;
		try{if(ID.equals(null)||ID.equals("")||ID==null) url = new URL("https://store.steampowered.com/wishlist/profiles/"+profile); else url =new URL("https://store.steampowered.com/wishlist/id/"+id);} catch (Exception e) {e.printStackTrace();}
		HttpURLConnection.setFollowRedirects(false);
		wczytajSource(url);
	}
	public void wczytajSource(URL url){
		try {
			HttpURLConnection yc =(HttpURLConnection) url.openConnection();
			yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
			yc.setRequestProperty("Cookie", "DSID=NO_DATA"); 
			BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				if(inputLine.startsWith("	var g_rgAppInfo =")) {
					Games.games = readGames(inputLine);

					break;
				}

			}
				a.append(inputLine);
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private ArrayList<Game> readGames(String inputLine) {
		ArrayList<String> gamesS=new ArrayList<String>();
		ArrayList<Game> games = new ArrayList<Game>();
		ArrayList<String> links = new ArrayList<String>();
		String checker="},\"";
		String a;
		while(true) {
			if(inputLine.length()<1000) checker="}};";
			gamesS.add(inputLine.substring(inputLine.indexOf(":{"),inputLine.indexOf(checker)));
			a=inputLine.substring(0,inputLine.indexOf(":{"));
			links.add(a.replaceAll("[>\\[\\]\":{}]","").replace("	var g_rgAppInfo = ", ""));
			inputLine=":"+inputLine.substring(inputLine.indexOf(checker)+2, inputLine.length());
			if (inputLine.length()<5) break;
		}
		Welcome.progressBarMax=gamesS.size()*(Welcome.addToDateBaseBool ? 3 : 2);
		gamesS.forEach(e->{games.add(getGame(e));Welcome.prograsssBar.setValue(Welcome.prograsssBar.get()+1);});
		links.forEach(e->{games.get(links.indexOf(e)).setLink(LINK+e); games.get(links.indexOf(e)).setID(Integer.valueOf(e)); Welcome.prograsssBar.setValue(Welcome.prograsssBar.get()+1);});
		return games;
	}
	private Game getGame(String gameLine) {
		return new Game(0,0,getValue(gameLine,keys[0]),"",getValue(gameLine, keys[1]),getValue(gameLine,keys[2]),getValues(gameLine,keys[3]),getValue(gameLine,keys[4]),getValue(gameLine,keys[5]),getValue(gameLine,keys[6]),0.0,
				new Rating(Integer.valueOf(getValue(gameLine, keys[7]).replaceAll("[,\"]","").equals("") ? 0 : Integer.valueOf(getValue(gameLine, keys[7]).replaceAll("[,\"]","")))*Integer.valueOf(getValue(gameLine, keys[8]).equals("") ? 0: Integer.valueOf(getValue(gameLine, keys[8]).replaceAll("[,\"]",""))),
						Integer.valueOf(getValue(gameLine, keys[7]).replaceAll(",","").equals("") ? 0 : Integer.valueOf(getValue(gameLine, keys[7]).replaceAll("[,\"]",""))))
				,getValue(gameLine, keys[9]));
	}
	public String getValue(String gameLine, String key) {
		if(!gameLine.contains(key)) return "";
		if(key.equals(keys[5])) { 
			return gameLine.substring(gameLine.indexOf(key)+key.length()+2,gameLine.indexOf("z\\u0142<\\/div>"));
		}
		gameLine=gameLine.substring(gameLine.indexOf("\""+key+"\":")+key.length()+1);
		gameLine=gameLine.substring(2,gameLine.indexOf(",")<0 ? gameLine.length() : gameLine.indexOf(","));
		return gameLine.replaceAll("[\"\\[\\](){},>]", "");
		
	}
	public DList getValues(String gameLine, String key) {
		DList result = new DList();
		if(!gameLine.contains(key)) return result;
		if(gameLine.contains("early_acces")) {
			gameLine=gameLine.substring(gameLine.indexOf(key)+key.length()+2,gameLine.indexOf("\"],\"early_"));
		} else {
			gameLine=gameLine.substring(gameLine.indexOf(key)+key.length()+2,gameLine.indexOf("\"],\"win")<0 ? gameLine.indexOf("\"],\"prere") : gameLine.indexOf("\"],\"win"));
		}
		while(true) {
			result.add(gameLine.substring(gameLine.indexOf("[\"")+2,gameLine.indexOf(",")==-1 ? gameLine.length() : gameLine.indexOf(",")).replaceAll("[>\\[\\]\"]", ""));
			gameLine="["+gameLine.substring(gameLine.indexOf("\",")<0 ? gameLine.length()-1 : gameLine.indexOf("\",")+2);
			if(gameLine.length()<5) break;
			
		}
		
		return result;
	}
	public static void main(String[] args) {
		
	}
}
