package game;

import java.util.Date;

import data.DDate;
import data.DList;
import data.Language;
import data.Rating;
import sql.GameColumns;
import sql.SQLCommands;

public class GameTable extends Game{
	protected int hash;
	public GameTable() {
		
	}
	
	public GameTable(int id, int hash, String name, String link, DDate reliseDate, DDate addDate, DList tags, double price,
			double orginalPrice, double discount, double wishnumber, Rating rate, String imagePath) {
		super(id, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath);
		this.hash=hash;
	}

	public GameTable(int id, int hash, String name, String link, DDate reliseDate, DDate addDate, String[] tags, double price,
			double orginalPrice, double discount, double wishnumber, Rating rate, String imagePath) {
		super(id, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath);
		this.hash=hash;
	}

	public GameTable(int id, int hash,String name, String link, String reliseDate, String addDate, DList tags, String price,
			String orginalPrice, String discount, double wishnumber, Rating rate, String imagePath) {
		super(id, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath);
		this.hash=hash;
	}

	public GameTable(GameTable gameTable) {
		super(gameTable.getID(),gameTable.getName(),gameTable.getLink(), gameTable.getReliseDate(),gameTable.getAddDate(),
				gameTable.getTags(),gameTable.getPrice(),gameTable.getOriginalPrice(),gameTable.getDiscount(),gameTable.getWishNumber(),gameTable.getRate(),gameTable.getImagePath());
		this.hash=gameTable.getHash();
	}
	public GameTable(Game gameTable, int hash) {
		super(gameTable.getID(),gameTable.getName(),gameTable.getLink(), gameTable.getReliseDate(),gameTable.getAddDate(),
				gameTable.getTags(),gameTable.getPrice(),gameTable.getOriginalPrice(),gameTable.getDiscount(),gameTable.getWishNumber(),gameTable.getRate(),gameTable.getImagePath());
		this.hash=hash;
	}

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}
	public static String[] toTableNames() {	
		String[] result = {Language.texts.getString("hash"),Language.texts.getString("title"),Language.texts.getString("price"),Language.texts.getString("originalPrice"),Language.texts.getString("discount"),Language.texts.getString("releasedDate"),Language.texts.getString("wishNumber"),Language.texts.getString("rate"),Language.texts.getString("addDate"),Language.texts.getString("tags")};
		return result;
	}
	public String valuesAndColumnsToString() {
		return GameColumns.ID+SQLCommands.EQUALS+ID+", "+GameColumns.HASH+SQLCommands.EQUALS+hash;
	}
	public String valuesToString() {
		return ID+", '"+hash+", '"+name.replaceAll("'","''")+"', '"+link+"', "+reliseDate.getTime()+", "+addDate.getTime()+", '"+tags.toString()+"', "+price+", "+originalPrice+", "+discount+", "+wishNumber+", "+rate.getPositive()+", "+rate.getAllRate()+", '"+imagePath+"'";
	}
	public String toInsert() {
		return ID+", "+hash;
	}
	
}
