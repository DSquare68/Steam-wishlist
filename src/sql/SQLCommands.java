package sql;

public interface SQLCommands extends GameColumns{
	static String initGameScanned=ID+" INTEGER PRIMARY KEY";
	static String initGame=ID+" INTEGER PRIMARY KEY, "+NAME+" TEXT, "+LINK+" TEXT, "+RELEASED_DATE+" INTEGER, "+ADD_DATE+" INTEGER, "+TAGS+" TEXT, "+PRICE+" REAL, "+ORIGINAL_PRICE+" REAL, "+DISCOUNT+" DOUBLE, "+WISH_NUMBER+" REAL, "+RATE_POSITIVE+" INTEGER, "+RATE_ALL+" INTEGER, "+IMAGE_PATH+" TEXT ";
	static String initGameTable=ID+" INTEGER PRIMARY KEY, "+HASH+" INTEGER ";
	static String initGameHistory=PK+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ID+" INTEGER, "+PRICE+" REAL, "+ORIGINAL_PRICE+" REAL, "+DISCOUNT+" DOUBLE, "+DISCOUNT_DATE+" INTEGER ";
	static String initGameStatus=ID+" INTEGER PRIMARY KEY, "+STATUS+" INTEGER ";
	static String initGameBought=ID+" INTEGER PRIMARY KEY, "+BOUGHT_PRICE+" DOUBLE, "+BOUGHT_DATE+" INTEGER ";
	static String allGameScanned=ID;
	static String allGame=ID+", "+NAME+" , "+LINK+" , "+RELEASED_DATE+" , "+ADD_DATE+" , "+TAGS+" , "+PRICE+" , "+ORIGINAL_PRICE+" , "+DISCOUNT+" , "+WISH_NUMBER+" , "+RATE_POSITIVE+" , "+RATE_ALL+" , "+IMAGE_PATH;
	static String allGameTable=ID+", "+HASH;
	static String allGameHistory=PK+", "+ID+", "+PRICE+", "+ORIGINAL_PRICE+", "+DISCOUNT+", "+DISCOUNT_DATE;
	static String gameHistory=ID+", "+PRICE+", "+ORIGINAL_PRICE+", "+DISCOUNT+", "+DISCOUNT_DATE;
	static String allGameStatus=ID+", "+STATUS;
	static String allGameBought=ID+", "+BOUGHT_PRICE+", "+BOUGHT_DATE;
	final static public String GAME="game", GAME_SCANNED="game_scanned",GAME_TABLE="game_table",GAME_HISTORY="game_history",GAME_STATUS="game_status",GAME_BOUGHT="game_bought"; 
	public String EQUALS=" = ";
	public String WHERE= " WHERE ";
	public static String KEY_DUPLICATE=" ON DUPLICATE KEY UPDATE ";
	String CHECK_IF_TABLE_EXISTS="SELECT count(*) FROM sqlite_master WHERE type = 'table' AND name = '";
	interface insert {
		String insertGameScanned="INSERT INTO "+GAME_SCANNED+" ( "+allGameScanned+") VALUES ";
		String insertGame="INSERT INTO "+GAME+" ( "+allGame+") VALUES ";
		String insertGameTable="INSERT INTO "+GAME_TABLE+" ( "+allGameTable+") VALUES ";
		String insertGameHistory="INSERT INTO "+GAME_HISTORY+" ( "+allGameHistory+") VALUES ";
		String insertGameStatus="INSERT INTO "+GAME_STATUS+" ( "+allGameStatus+") VALUES ";
		String insertGameBought="INSERT INTO "+GAME_BOUGHT+" ( "+allGameBought+") VALUES ";
		
		String insertOrReplaceGameScanned="INSERT OR REPLACE INTO "+GAME_SCANNED+" ( "+allGameScanned+") VALUES ";
		String insertOrReplaceGame="INSERT OR REPLACE INTO "+GAME+" ( "+allGame+") VALUES ";
		String insertOrReplaceGameTable="INSERT OR REPLACE INTO "+GAME_TABLE+" ( "+allGameTable+") VALUES ";
		String insertOrReplaceGameHistoryPK="INSERT OR REPLACE INTO "+GAME_HISTORY+" ( "+allGameHistory+") VALUES ";
		String insertOrReplaceGameHistory="INSERT OR REPLACE INTO "+GAME_HISTORY+" ( "+gameHistory+") VALUES ";
		String insertOrReplaceGameStatus="INSERT OR REPLACE INTO "+GAME_STATUS+" ( "+allGameStatus+") VALUES ";
		String insertOrReplaceGameBought="INSERT OR REPLACE INTO "+GAME_BOUGHT+" ( "+allGameBought+") VALUES ";
	}
	interface delete{
		String deleteGameScanned ="DELETE FROM"+GAME_SCANNED;
		String deleteGame ="DELETE FROM"+GAME;
		String deleteGameTable ="DELETE FROM"+GAME_TABLE;
		String deleteGameHistory ="DELETE FROM"+GAME_HISTORY;
		String deleteGameStatus ="DELETE FROM"+GAME_STATUS;
		String deleteGameBought ="DELETE FROM"+GAME_BOUGHT;
	}
	interface update{
		String updateGameScanned = "UPDATE "+GAME_SCANNED+" SET ";
		String updateGame = "UPDATE "+GAME+" SET ";
		String updateGameTable = "UPDATE "+GAME_TABLE+" SET ";
		String updateGameHistory = "UPDATE "+GAME_HISTORY+" SET ";
		String updateGameStatus = "UPDATE "+GAME_STATUS+" SET ";
		String updateGameBought = "UPDATE "+GAME_BOUGHT+" SET ";
	}
	interface get{
		String getGameScanned="SELECT "+allGameScanned+" FROM "+GAME_SCANNED;
		String getGame="SELECT "+allGame+" FROM "+GAME;
		String getGameTable="SELECT "+allGameTable+" FROM "+GAME_TABLE;
		String getGameHistory="SELECT "+allGameHistory+" FROM "+GAME_HISTORY;
		String getGameStatus="SELECT "+allGameStatus+" FROM "+GAME_STATUS;
		String getGameBought="SELECT "+allGameBought+" FROM "+GAME_BOUGHT;
		
		String getWishNumber="SELECT "+ID+", "+WISH_NUMBER+" FROM "+GAME;
		String getHash="SELECT "+ID+", "+HASH+" FROM "+GAME_TABLE;
		String getDateBought="SELECT "+ID+", "+DISCOUNT_DATE+" FROM "+GAME_HISTORY;
	}
	interface count{
		static final String countGameScanned = "SELECT COUNT(*) FROM "+GAME_SCANNED;
		static final String countGame = "SELECT COUNT(*) FROM "+GAME;
		static final String countGameTable = "SELECT COUNT(*) FROM "+GAME_TABLE;
		static final String countGameHistory = "SELECT COUNT(*) FROM "+GAME_HISTORY;
		static final String countGameStatus = "SELECT COUNT(*) FROM "+GAME_STATUS;
		static final String countGameBought = "SELECT COUNT(*) FROM "+GAME_BOUGHT;
	}
	interface init{
		String createGame="CREATE TABLE "+GAME+" ( "+initGame+" );";
		String createGameScanned="CREATE TABLE "+GAME_SCANNED+" ( "+initGameScanned+" );";
		String createGameTable="CREATE TABLE "+GAME_TABLE+" ( "+initGameTable+" );";
		String createGameHistory="CREATE TABLE "+GAME_HISTORY+" ( "+initGameHistory+" );";
		String createGameStatus="CREATE TABLE "+GAME_STATUS+" ( "+initGameStatus+" );";
		String createGameBought="CREATE TABLE "+GAME_BOUGHT+" ( "+initGameBought+" );";
	}
}
