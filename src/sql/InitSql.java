package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Resources.ResourceLoader;

public class InitSql implements SQLCommands{
	protected static Connection conn;
	protected static Statement statement;
	
	public InitSql() {
		
	}
	public static void init() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+ResourceLoader.getPathToData()+"Datebase\\games.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createStatment() {
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Statement getStatement() {
		createStatment();
		return statement;
	}
	public void closeStatement() {
		try {
			if(!conn.isClosed())statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create() {
		try {
			statement = conn.createStatement();
		    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		    ResultSet rs = statement.executeQuery(CHECK_IF_TABLE_EXISTS+GAME_SCANNED+"'"); 
		    if(rs.getInt(1)==0) {
		    	statement.executeUpdate(init.createGameScanned);
		    }
		    rs = statement.executeQuery(CHECK_IF_TABLE_EXISTS+GAME+"'"); 
		    if(rs.getInt(1)==0) {
		    	statement.executeUpdate(init.createGame);
		    }
		    rs = statement.executeQuery(CHECK_IF_TABLE_EXISTS+GAME_TABLE+"'"); 
		    if(rs.getInt(1)==0) {
		    	statement.executeUpdate(init.createGameTable);
		    }
		    rs = statement.executeQuery(CHECK_IF_TABLE_EXISTS+GAME_HISTORY+"'"); 
		    if(rs.getInt(1)==0) {
		    	statement.executeUpdate(init.createGameHistory);
		    }
		    rs = statement.executeQuery(CHECK_IF_TABLE_EXISTS+GAME_STATUS+"'"); 
		    if(rs.getInt(1)==0) {
		    	statement.executeUpdate(init.createGameStatus);
		    }
		    rs = statement.executeQuery(CHECK_IF_TABLE_EXISTS+GAME_BOUGHT+"'"); 
		    if(rs.getInt(1)==0) {
		    	statement.executeUpdate(init.createGameBought);
		    }
			}catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
