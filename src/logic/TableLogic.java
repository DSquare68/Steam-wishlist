package logic;

import java.util.ArrayList;
import java.util.Optional;

import data.Language;
import data.Tree;
import game.GameScanned;
import game.GameTable;
import game.Games;
import game.Game;
import gui.Central;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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
		}else if(games.games.size()<Sql.Count.countGame()){
			Tree<Integer> treeHash=Sql.get.getHash(null);
			Games<Game> gamesAll= Sql.get.getAllGames();
			gamesAll.getGames().forEach(e->{if(treeHash.find(treeHash.getRoot(), e.getID())==null) {
				games.getGames().add(new GameTable(e, games.getGames().size()+1));
				};
			});
			//TODO what if we buy games the size will be mismatch... 
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(Language.texts.getString("alertTableSizeMismatchTitle"));
			alert.setHeaderText(Language.texts.getString("alertTableSizeMismatchHeader"));
			alert.setContentText(Language.texts.getString("alertTableSizeMismatchContent"));

			ButtonType buttonTypeOne = new ButtonType(Language.texts.getString("alertTableSizeMismatchButton1"));
			ButtonType buttonTypeTwo = new ButtonType(Language.texts.getString("alertTableSizeMismatchButton2"));
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,  buttonTypeCancel);

		
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne){
			    Sql.Insert.insertOrReplaceGamesTable(games.getGames());
			} else if (result.get() == buttonTypeTwo) {
			    // ... user chose "Two"
			} else {
			    System.out.println("Wrong button.");
			}
			
		}
		gamesList.addAll(games.getGames());
		Central.table.setItems(gamesList);
	}
}
