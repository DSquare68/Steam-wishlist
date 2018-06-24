package game;

import sql.GameColumns;
import sql.SQLCommands;

public class GameStatus {
	private int ID, status;
	
	public GameStatus() {
		
	}

	public GameStatus(int iD, int status) {
		super();
		ID = iD;
		this.status = status;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String valuesAndColumnsToString() {
		return GameColumns.ID+SQLCommands.EQUALS+ID+", "+GameColumns.STATUS+SQLCommands.EQUALS+status;
	}
	
}
