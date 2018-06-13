package sql;

public interface SQLCommands extends GameColumns{
	static String initAllGame=ID+" INTEGER, "+NAME+" TEXT, "+LINK+" TEXT, "+RELEASED_DATE+" INTEGER, "+ADD_DATE+" INTEGER, "+TAGS+" TEXT, "+PRICE+" REAL, "+ORIGINAL_PRICE+" REAL, "+DISCOUNT+" DOUBLE, "+WISH_NUMBER+" REAL, "+RATE_POSITIVE+" INTEGER, "+RATE_ALL+" INTEGER, "+IMAGE_PATH+" TEXT ";
	static String allGame=ID+", "+NAME+" , "+LINK+" , "+RELEASED_DATE+" , "+ADD_DATE+" , "+TAGS+" , "+PRICE+" , "+ORIGINAL_PRICE+" , "+DISCOUNT+" , "+WISH_NUMBER+" , "+RATE_POSITIVE+" , "+RATE_ALL+" , "+IMAGE_PATH;
	final static public String GAME="game"; 
	public String EQUALS=" = ";
	public String WHERE= " WHERE ";
	String CHECK_IF_TABLE_EXISTS="SELECT count(*) FROM sqlite_master WHERE type = 'table' AND name = '";
	interface insert {
		String insertGame="INSERT INTO "+GAME+" ( "+allGame+") VALUES ";
	}
	interface delete{
		String deleteGame ="DELETE FROM"+GAME;
	}
	interface update{
		String updateGame = "UPDATE "+GAME+" SET ";
	}
	interface get{
		String getGame="SELECT "+allGame+" FROM "+GAME;
	}
	interface count{
		static final String countGame = "SELECT COUNT(*) FROM "+GAME;
	}
	interface init{
		String initGame="CREATE TABLE "+GAME+" ( "+initAllGame+" );";
	}
}
