package game;

import data.DDate;
import data.DList;
import data.Language;
import data.Rating;
import sql.GameColumns;
import sql.SQLCommands;

public class GameDatebase extends GameBought{
	/**
	 * 0-unknown
	 * 1-wishlist
	 * 2-bought
	 * 3-canceled
	 */
	protected int status; 
	protected long discountDate;
	public GameDatebase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GameDatebase(int id, int hash, String name, String link, DDate reliseDate, DDate addDate, DList tags,
			double price, double orginalPrice, double discount, double wishnumber, Rating rate, String imagePath,
			long boughtDate, double boughtPrice, int status, long discountDate) {
		super(id, hash, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath,
				boughtDate, boughtPrice);
		this.status=status;
		this.discountDate=discountDate;
	}
	public GameDatebase(int id, int hash, String name, String link, DDate reliseDate, DDate addDate, String[] tags,
			double price, double orginalPrice, double discount, double wishnumber, Rating rate, String imagePath,
			long boughtDate, double boughtPrice, int status, long discountDate) {
		super(id, hash, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath,
				boughtDate, boughtPrice);
		this.status=status;
		this.discountDate=discountDate;
	}
	public GameDatebase(int id, int hash, String name, String link, String reliseDate, String addDate, DList tags,
			String price, String orginalPrice, String discount, double wishnumber, Rating rate, String imagePath,
			long boughtDate, double boughtPrice, int status, long discountDate) {
		super(id, hash, name, link, reliseDate, addDate, tags, price, orginalPrice, discount, wishnumber, rate, imagePath,
				boughtDate, boughtPrice);
		this.status=status;
		this.discountDate=discountDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getDiscountDate() {
		return discountDate;
	}
	public void setDiscountDate(long discountDate) {
		this.discountDate = discountDate;
	}
	public static String[] toTableNames() {	
		String[] result = {Language.texts.getString("hash"),Language.texts.getString("title"),Language.texts.getString("price"),Language.texts.getString("originalPrice"),Language.texts.getString("discount"),Language.texts.getString("releasedDate"),Language.texts.getString("rate"),Language.texts.getString("tags"),Language.texts.getString("addDate"),Language.texts.getString("wishNumber"),Language.texts.getString("boughtDate"),Language.texts.getString("boughtPrice"),Language.texts.getString("status"),Language.texts.getString("discountDate")};
		return result;
	}
	public String valuesAndColumnsToString() {
		return GameColumns.ID+SQLCommands.EQUALS+ID+", "+GameColumns.HASH+SQLCommands.EQUALS+hash+", "+GameColumns.NAME+SQLCommands.EQUALS+"'"+name.replaceAll("'","''")+"', "+GameColumns.LINK+SQLCommands.EQUALS+"'"+link+"', "+GameColumns.RELEASED_DATE+SQLCommands.EQUALS+reliseDate.getTime()+", "+GameColumns.ADD_DATE+SQLCommands.EQUALS+addDate.getTime()+", "+GameColumns.TAGS+SQLCommands.EQUALS+"'"+tags.toString()+"', "+GameColumns.PRICE+SQLCommands.EQUALS+price+", "+GameColumns.ORIGINAL_PRICE+SQLCommands.EQUALS+originalPrice+", "+GameColumns.DISCOUNT+SQLCommands.EQUALS+discount+", "+GameColumns.WISH_NUMBER+SQLCommands.EQUALS+wishNumber+", "+GameColumns.RATE_POSITIVE+SQLCommands.EQUALS+rate.getPositive()+", "+GameColumns.RATE_ALL+SQLCommands.EQUALS+rate.getAllRate()+", "+GameColumns.IMAGE_PATH+SQLCommands.EQUALS+"'"+imagePath+"'"+", "+GameColumns.BOUGHT_DATE+SQLCommands.EQUALS+boughtDate+", "+GameColumns.BOUGHT_PRICE+SQLCommands.EQUALS+boughtPrice+", "+GameColumns.STATUS+SQLCommands.EQUALS+status+", "+GameColumns.DISCOUNT_DATE+SQLCommands.EQUALS+discountDate;
	}
	public String valuesToString() {
		return ID+", '"+hash+", '"+name.replaceAll("'","''")+"', '"+link+"', "+reliseDate.getTime()+", "+addDate.getTime()+", '"+tags.toString()+"', "+price+", "+originalPrice+", "+discount+", "+wishNumber+", "+rate.getPositive()+", "+rate.getAllRate()+", '"+imagePath+"'"+", '"+boughtDate+"'"+", '"+boughtPrice+"'"+", '"+status+"'"+", '"+discountDate+"'";
	}
	public String toHistoryInsert() {
	 return ID+", "+price+", "+originalPrice+", "+discount+", "+discountDate;
	}
	public String toStatusInsert() {
		return ID+", "+status;
	}
	
	
}
