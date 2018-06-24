package game;

import data.DDate;
import data.DList;
import data.Language;
import data.Rating;
import sql.GameColumns;
import sql.SQLCommands;

public class Game extends GameScanned{
	protected double wishNumber;

	public double getWishNumber() {
		return wishNumber;
	}

	public void setWishNumber(double wishNumber) {
		this.wishNumber = wishNumber;
	}
	public Game(){
		
	}
	public Game(GameScanned gameScanned,double wishNumber) {
		super(gameScanned.getID(),gameScanned.getName(),gameScanned.getLink(), gameScanned.getReliseDate(),gameScanned.getAddDate(),
				gameScanned.getTags(),gameScanned.getPrice(),gameScanned.getOriginalPrice(),gameScanned.getDiscount(),gameScanned.getRate(),gameScanned.getImagePath());
		this.wishNumber=0.0;
	}
	public Game(int id, String name, String link, DDate reliseDate, DDate addDate, DList tags, double price,
			double orginalPrice, double discount,double wishnumber, Rating rate, String imagePath) {
		super(id,name,link,reliseDate,addDate,tags,price, orginalPrice,discount,rate,imagePath);
		this.wishNumber=wishnumber;
	}
	public Game(int id, String name, String link, DDate reliseDate, DDate addDate, String[] tags, double price,
			double orginalPrice, double discount,double wishnumber, Rating rate, String imagePath) {
		super(id,name,link,reliseDate,addDate,tags,price, orginalPrice,discount,rate,imagePath);
		this.wishNumber=wishnumber;
		}
	public Game(int id,String name, String link, String reliseDate, String addDate, DList tags, String price,
			String orginalPrice, String discount,double wishnumber, Rating rate, String imagePath) {
		super(id,name,link,reliseDate,addDate,tags,price, orginalPrice,discount,rate,imagePath);
		this.wishNumber=wishnumber;
	}
	
	public static String[] toScannedNames() {	
		String[] result = {Language.texts.getString("title"),Language.texts.getString("price"),Language.texts.getString("originalPrice"),Language.texts.getString("discount"),Language.texts.getString("releasedDate"),Language.texts.getString("rate"),Language.texts.getString("tags"),Language.texts.getString("addDate"),Language.texts.getString("wishNumber")};
		return result;
	}
	public String valuesAndColumnsToString() {
		return GameColumns.ID+SQLCommands.EQUALS+ID+", "+GameColumns.NAME+SQLCommands.EQUALS+"'"+name.replaceAll("'","''")+"', "+GameColumns.LINK+SQLCommands.EQUALS+"'"+link+"', "+GameColumns.RELEASED_DATE+SQLCommands.EQUALS+reliseDate.getTime()+", "+GameColumns.ADD_DATE+SQLCommands.EQUALS+addDate.getTime()+", "+GameColumns.TAGS+SQLCommands.EQUALS+"'"+tags.toString()+"', "+GameColumns.PRICE+SQLCommands.EQUALS+price+", "+GameColumns.ORIGINAL_PRICE+SQLCommands.EQUALS+originalPrice+", "+GameColumns.DISCOUNT+SQLCommands.EQUALS+discount+", "+GameColumns.WISH_NUMBER+SQLCommands.EQUALS+wishNumber+", "+GameColumns.RATE_POSITIVE+SQLCommands.EQUALS+rate.getPositive()+", "+GameColumns.RATE_ALL+SQLCommands.EQUALS+rate.getAllRate()+", "+GameColumns.IMAGE_PATH+SQLCommands.EQUALS+"'"+imagePath+"'";
	}
	public String valuesToString() {
		return ID+", '"+name.replaceAll("'","''")+"', '"+link+"', "+reliseDate.getTime()+", "+addDate.getTime()+", '"+tags.toString()+"', "+price+", "+originalPrice+", "+discount+", "+wishNumber+", "+rate.getPositive()+", "+rate.getAllRate()+", '"+imagePath+"'";
	}

}
