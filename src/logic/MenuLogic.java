package logic;

import java.util.ArrayList;
import java.util.Date;

import data.Settings;
import data.Tree;
import game.Game;
import game.GameHistory;
import game.GameScanned;
import game.GameTable;
import sql.Sql;

public class MenuLogic {
	
	public static void changeUser() {
		
	}
	public static void reload() {
		Scraper scrap= new Scraper(Settings.ID, Settings.profile);
		ArrayList<GameScanned> gS= scrap.wczytajSource();
		Tree<Double> treeWishList = Sql.get.getWishNumber(null);
		ArrayList<Game> game =  new ArrayList<Game>();
		gS.forEach(e->game.add(new Game(e,treeWishList==null ? 0.0 : treeWishList.find(treeWishList.getRoot(), e.getID()))));
		Sql.Insert.insertOrReplaceGameScanned(gS); 
		Sql.Insert.insertOrReplaceGames(game);
		TableLogic.addGameToTable();
	}
	public static void home() {
		TableLogic.addGameToTable();
	}
	@SuppressWarnings("deprecation")
	public static void saveDiscount(ArrayList<GameTable> array) {
		array.forEach(e->{if(e.getDiscount()!=0 && !Sql.Check.historyGameExists(e.getID(),(new Date((new Date()).getYear(),(new Date()).getMonth(),(new Date()).getDate())).getTime())) Sql.Insert.insertOrReplaceGameHistory(new GameHistory(e.getID(), e.getPrice(), e.getOriginalPrice(), e.getDiscount(), (new Date((new Date()).getYear(),(new Date()).getMonth(),(new Date()).getDate())).getTime()));});
	}
	public static void saveState(ArrayList<GameTable> array) {
		
	}

}
