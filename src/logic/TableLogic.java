package logic;

import java.util.ArrayList;

import game.GameScanned;
import game.GameTable;
import game.Games;
import game.Game;
import gui.Central;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import sql.Sql;

public class TableLogic {
	static Games<GameTable> games = new Games<GameTable>(); //TODO Games('class'.getcurrent/last/fromapril2015
	public static void addGameToTable() {
		ObservableList<GameTable> gamesList = FXCollections.observableArrayList();
		games =Sql.get.getAllGamesTable();
		if(games==null) {
			games = new Games<GameTable>();
			Games<Game>  gScanned= Sql.get.getAllGames();
			ArrayList<GameTable> gTable =new ArrayList<>();
			gScanned.getGames().forEach(e->gTable.add(new GameTable(e,gScanned.getGames().indexOf(e)+1)));
			games.setGames( gTable);
			
			Sql.Insert.insertOrReplaceGamesTable(games.getGames());
		}else {
			
		}
		gamesList.addAll(games.getGames());
		Central.table.setItems(gamesList);
	}
}
