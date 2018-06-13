package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.DDate;
import data.DList;
import data.Game;
import data.Games;
import data.Rating;
import gui.Welcome;

public class Sql extends InitSql {
	
	public Sql() {
		
	}
	public static class Insert{
		public static void insertGame(Game game) {
			try {
				statement.executeUpdate(insert.insertGame+" ( "+game.valuesToString()+" ); ");
				if(Welcome.addToDateBaseBool) Welcome.prograsssBar.setValue(Welcome.prograsssBar.getValue()+1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static void insertGames(ArrayList<Game> games) {
			games.forEach(e->insertGame(e));
		}
	}
	public static class Count{
		public static int countGame() {
			try {
				return statement.executeQuery(count.countGame).getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		}
	}
	public static class get{
		public static void getAllGames() {
			Games games = new Games();
			ArrayList<Game> gameList = new ArrayList<>();
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGame);
				while(rs.next()) {
					DList dList= new DList();
					dList.removeAll();
					dList.addAll(rs.getString(6));
					gameList.add(new Game(rs.getInt(1), 0, rs.getString(2), rs.getString(3), new DDate(rs.getLong(4)), new DDate(rs.getLong(5)), dList, rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), new Rating(rs.getInt(11),rs.getInt(12)), rs.getString(13)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			games.setGames(gameList);
		}
		public Game getGame(String where) {
			ResultSet rs=null;
			try {
				rs= statement.executeQuery(sql.SQLCommands.get.getGame+WHERE+where);
				rs.first();
				DList dList= new DList();
				dList.removeAll(dList);
				dList.addAll(rs.getString(5));
				return new Game(rs.getInt(1), 0, rs.getString(2), rs.getString(2), new DDate(rs.getLong(3)), new DDate(rs.getLong(4)), dList, rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), new Rating(rs.getInt(10),rs.getInt(11)), rs.getString(12));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
	}
	public static class Update{
		public static void updateGame(Game game, String where) {
			try {
				statement.executeUpdate(update.updateGame+" ( "+game.valuesAndColumnsToString()+" ) "+WHERE+where+"; ");
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
}
