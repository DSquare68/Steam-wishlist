package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.Double;

import data.DDate;
import data.DList;
import data.Rating;
import data.Tree;
import game.Game;
import game.GameBought;
import game.GameDatebase;
import game.GameHistory;
import game.GameScanned;
import game.GameStatus;
import game.GameTable;
import game.Games;
import gui.Welcome;

public class Sql extends InitSql {
	
	public Sql() {
		
	}
	public static class Insert{
		public static void insertGameScanned(GameScanned game) {
			try {
				statement.executeUpdate(insert.insertGameScanned+" ( "+game.toInsert()+" ); ");
				if(Welcome.addToDateBaseBool) Welcome.prograsssBar.setValue(Welcome.prograsssBar.getValue()+1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertGame(Game game) {
			try {
				statement.executeUpdate(insert.insertGame+" ( "+game.valuesToString()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertGameTable(GameTable game) {
			try {
				statement.executeUpdate(insert.insertGameTable+" ( "+game.toInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertGameHistory(GameDatebase game) {
			try {
				statement.executeUpdate(insert.insertGameHistory+" ( "+game.toHistoryInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertGameStatus(GameDatebase game) {
			try {
				statement.executeUpdate(insert.insertGameStatus+" ( "+game.toStatusInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertGameBought(GameBought game) {
			try {
				statement.executeUpdate(insert.insertGameBought+" ( "+game.toInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertOrReplaceGameScanned(GameScanned game) {
			try {
				statement.executeUpdate(insert.insertOrReplaceGameScanned+" ( "+game.toInsert()+" ); ");
				if(Welcome.addToDateBaseBool) Welcome.prograsssBar.setValue(Welcome.prograsssBar.getValue()+1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertOrReplaceGame(Game game) {
			try {
				statement.executeUpdate(insert.insertOrReplaceGame+" ( "+game.valuesToString()+" ); ");
				if(Welcome.addToDateBaseBool) Welcome.prograsssBar.setValue(Welcome.prograsssBar.getValue()+1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertOrReplaceGameTable(GameTable game) {
			try {
				statement.executeUpdate(insert.insertOrReplaceGameTable+" ( "+game.toInsert()+" ); ");
				if(Welcome.addToDateBaseBool) Welcome.prograsssBar.setValue(Welcome.prograsssBar.getValue()+1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertOrReplaceGameHistory(GameDatebase game) {
			try {
				statement.executeUpdate(insert.insertOrReplaceGameHistory+" ( "+game.toHistoryInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertOrReplaceGameHistory(GameHistory game) {
			try {
				statement.executeUpdate(insert.insertOrReplaceGameHistory+" ( "+game.toInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertOrReplaceGameStatus(GameDatebase game) {
			try {
				statement.executeUpdate(insert.insertOrReplaceGameStatus+" ( "+game.toStatusInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertOrReplaceGameBought(GameBought game) {
			try {
				statement.executeUpdate(insert.insertOrReplaceGameBought+" ( "+game.toInsert()+" ); ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void insertGames(ArrayList<GameTable> games) {
			games.forEach(e->insertGameTable(e));
		}
		public static void insertGameScanned(ArrayList<GameScanned> games) {
			games.forEach(e->insertGameScanned(e));
		}
		public static void insertOrReplaceGamesTable(ArrayList<GameTable> games) {
			games.forEach(e->insertOrReplaceGameTable(e));
		}
		public static void insertOrReplaceGameScanned(ArrayList<GameScanned> games) {
			games.forEach(e->insertOrReplaceGameScanned(e));
		}
		public static void insertOrReplaceGames(ArrayList<Game> games) {
			games.forEach(e->insertOrReplaceGame(e));
		}
	}
	public static class Count{
		public static int countGameScanned() {
			try {
				return statement.executeQuery(count.countGameScanned).getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		}
		public static int countGame() {
			try {
				return statement.executeQuery(count.countGame).getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		}
		public static int countGameTable() {
			try {
				return statement.executeQuery(count.countGameTable).getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		}
		public static int countGameHistory() {
			try {
				return statement.executeQuery(count.countGameHistory).getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		}
		public static int countGameStatus() {
			try {
				return statement.executeQuery(count.countGameStatus).getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		}
		public static int countGameBought() {
			try {
				return statement.executeQuery(count.countGameBought).getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		}
	}
	public static class get{
		public static Tree<Double> getWishNumber(String where){
			if(where==null) {
				Tree<Double> tree = new Tree<Double>();
				ResultSet rs=null;
				try {
					rs= statement.executeQuery(sql.SQLCommands.get.getWishNumber);
					if(rs.isClosed()) return null;
					while(rs.next()) {
						if(tree.getRoot()==null) tree.setRoot(rs.getInt(1), rs.getDouble(2));
						tree.setRoot(tree.insert(tree.getRoot(),rs.getInt(1), rs.getDouble(2)));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				return null;
			}
			return null;
			
		}
		public static Tree<Integer> getHash(String where){
			if(where==null) {
				Tree<Integer> tree = new Tree<Integer>();
				ResultSet rs=null;
				try {
					rs= statement.executeQuery(sql.SQLCommands.get.getHash);
					if(rs.isClosed()) return null;
					while(rs.next()) {
						if(tree.getRoot()==null) tree.setRoot(rs.getInt(1), rs.getInt(2));
						tree.setRoot(tree.insert(tree.getRoot(),rs.getInt(1), rs.getInt(2)));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return tree;
			} else {
				return null;
			}
			
		}
		public static Games<GameScanned> getAllGamesScanned() {
			Games<GameScanned> games = new Games<GameScanned>();
			ArrayList<GameScanned> gameList = new ArrayList<>();
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGameScanned);
				while(rs.next()) {
					gameList.add(new GameScanned(rs.getInt(1)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			games.setGames(gameList);
			return games;
		}
		public static Games<Game> getAllGames() {
			Games<Game> games = new Games<Game>();
			ArrayList<Game> gameList = new ArrayList<>();
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGame);
				while(rs.next()) {
					DList dList= new DList();
					dList.removeAll();
					dList.addAll(rs.getString(6));
					gameList.add(new Game(rs.getInt(1), rs.getString(2), rs.getString(3), new DDate(rs.getLong(4)), new DDate(rs.getLong(5)), dList, rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), new Rating(rs.getInt(11),rs.getInt(12)), rs.getString(13)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			games.setGames(gameList);
			return games;
		}
		public static Games<GameTable> getAllGamesTable() {
			Games<GameTable> games = new Games<GameTable>();
			ArrayList<GameTable> gameList = new ArrayList<>();
		
			Tree<Integer> treeHash=getHash(null);
			Games<Game> ggames = getAllGames(); 
			if(treeHash==null) return null;
			if(ggames==null) {System.out.println("ERRROR in get AllGamesTable not find in database game"); return null;}
			int k=0;
			while(k<treeHash.size()) {
				gameList.add(new GameTable(ggames.getGames().get(k),treeHash.find(treeHash.getRoot(),ggames.getGames().get(k++).getID())));
			}
			games.setGames(gameList);
			return games;
		}
		public static Games<GameHistory> getAllGamesHistory() {
			Games<GameHistory> games = new Games<GameHistory>();
			ArrayList<GameHistory> gameList = new ArrayList<>();
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGameHistory);
				while(rs.next()) {
					gameList.add(new GameHistory(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getLong(5)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			games.setGames(gameList);
			return games;
		}
		public static Games<GameStatus> getAllGamesStatus() {
			Games<GameStatus> games = new Games<GameStatus>();
			ArrayList<GameStatus> gameList = new ArrayList<>();
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGameStatus);
				while(rs.next()) {
					gameList.add(new GameStatus(rs.getInt(1),rs.getInt(2)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			games.setGames(gameList);
			return games;
		}
		public static void getAllGamesBought() {
			Games<GameBought> games = new Games<GameBought>();
			ArrayList<GameBought> gameList = new ArrayList<>();
			Games<GameTable> gameTable =getAllGamesTable();
			
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGameBought);
				while(rs.next()) {
					gameList.add(new GameBought(rs.getInt(1),gameTable,rs.getInt(2),rs.getLong(3)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			games.setGames(gameList);
		}
		public GameTable getGame(String where) {
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGameTable+WHERE+where);
				rs.first();
				DList dList= new DList();
				dList.removeAll(dList);
				dList.addAll(rs.getString(5));
				return new GameTable(rs.getInt(1), 0, rs.getString(2), rs.getString(2), new DDate(rs.getLong(3)), new DDate(rs.getLong(4)), dList, rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), new Rating(rs.getInt(10),rs.getInt(11)), rs.getString(12));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
	}
	public static class Update{
		public static void updateGameScanned(GameScanned game, String where) {
			try {
				statement.executeUpdate(update.updateGameScanned+" ( "+game.valuesAndColumnsToString()+" ) "+WHERE+where+"; ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void updateGame(Game game, String where) {
			try {
				statement.executeUpdate(update.updateGame+" ( "+game.valuesAndColumnsToString()+" ) "+WHERE+where+"; ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void updateGameTable(GameTable game, String where) {
			try {
				statement.executeUpdate(update.updateGameTable+" ( "+game.valuesAndColumnsToString()+" ) "+WHERE+where+"; ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void updateGameHistory(GameHistory game, String where) {
			try {
				statement.executeUpdate(update.updateGameHistory+" ( "+game.valuesAndColumnsToString()+" ) "+WHERE+where+"; ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void updateGameStatus(GameStatus game, String where) {
			try {
				statement.executeUpdate(update.updateGameStatus+" ( "+game.valuesAndColumnsToString()+" ) "+WHERE+where+"; ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void updateGameBought(GameBought game, String where) {
			try {
				statement.executeUpdate(update.updateGameBought+" ( "+game.valuesAndColumnsToString()+" ) "+WHERE+where+"; ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public class Delete{
		public void deleteGame(String where) {
			try {
				statement.executeUpdate(delete.deleteGame+WHERE+where+"; ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static class Check implements SQLCommands.get{
		public static boolean historyGameExists(int ID, long date) {
			try {
				ResultSet rs = statement.executeQuery(getDateBought+WHERE+Sql.ID+EQUALS+ID+" AND "+Sql.DISCOUNT_DATE+EQUALS+String.valueOf(date));
				if(rs.isClosed()) return false; else return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
}
