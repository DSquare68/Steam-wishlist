package logic;

import data.Game;
import data.Games;
import gui.Central;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableLogic {
	static Games games = new Games(); //TODO Games('class'.getcurrent/last/fromapril2015
	public static void addGameToTable() {
		ObservableList<Game> gamesList = FXCollections.observableArrayList();
		games.getGames().forEach(e->{e.setHash(games.getGames().indexOf(e)+1);gamesList.add(e);});
		Central.table.setItems(gamesList);
	}

}
