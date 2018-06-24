package game;

import java.util.ArrayList;

import data.DDate;
import data.DList;
import data.Language;
import data.Rating;
import sql.GameColumns;
import sql.SQLCommands;

public class GameBought extends GameTable {
	protected long boughtDate;
	protected double boughtPrice;
	public GameBought() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GameBought(int id, int hash, String name, String link, DDate reliseDate, DDate addDate, DList tags,
			double price, double orginalPrice, double discount, double wishnumber, Rating rate, String imagePath, long boughtDate, double boughtPrice) {
		super(id, hash, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath);
		this.boughtDate=boughtDate;
		this.boughtPrice=boughtPrice;
	}
	public GameBought(int id, int hash, String name, String link, DDate reliseDate, DDate addDate, String[] tags,
			double price, double orginalPrice, double discount, double wishnumber, Rating rate, String imagePath, long boughtDate, double boughtPrice) {
		super(id, hash, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath);
		this.boughtDate=boughtDate;
		this.boughtPrice=boughtPrice;
	}
	public GameBought(int id, int hash, String name, String link, String reliseDate, String addDate, DList tags,
			String price, String orginalPrice, String discount, double wishnumber, Rating rate, String imagePath, long boughtDate, double boughtPrice) {
		super(id, hash, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath);
		this.boughtDate=boughtDate;
		this.boughtPrice=boughtPrice;
	}
	public GameBought(int ID, Games<GameTable> games, int boughtPrice, long boughtDate) {
		super( games.getByID(ID));
		this.boughtPrice=boughtPrice;
		this.boughtDate=boughtDate;
		
	}
	public long getBoughtDate() {
		return boughtDate;
	}
	public void setBoughtDate(long boughtDate) {
		this.boughtDate = boughtDate;
	}
	public double getBoughtPrice() {
		return boughtPrice;
	}
	public void setBoughtPrice(double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}
	public static String[] toTableNames() {	
		String[] result = {Language.texts.getString("hash"),Language.texts.getString("title"),Language.texts.getString("price"),Language.texts.getString("originalPrice"),Language.texts.getString("discount"),Language.texts.getString("releasedDate"),Language.texts.getString("rate"),Language.texts.getString("tags"),Language.texts.getString("addDate"),Language.texts.getString("wishNumber"),Language.texts.getString("boughtDate"),Language.texts.getString("boughtPrice")};
		return result;
	}
	public String valuesAndColumnsToString() {
		return GameColumns.ID+SQLCommands.EQUALS+ID+", "+GameColumns.BOUGHT_PRICE+SQLCommands.EQUALS+boughtPrice+", "+GameColumns.BOUGHT_DATE+SQLCommands.EQUALS+boughtDate;
	}
	public String valuesToString() {
		return ID+", '"+hash+", '"+name.replaceAll("'","''")+"', '"+link+"', "+reliseDate.getTime()+", "+addDate.getTime()+", '"+tags.toString()+"', "+price+", "+originalPrice+", "+discount+", "+wishNumber+", "+rate.getPositive()+", "+rate.getAllRate()+", '"+imagePath+"'"+", '"+boughtDate+"'"+", '"+boughtPrice+"'";
	}
	public String toInsert() {
		return ID+", "+boughtPrice+", "+boughtDate;
	}
}
