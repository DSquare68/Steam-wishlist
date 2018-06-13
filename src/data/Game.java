package data;

import java.util.Date;
import sql.GameColumns;
import sql.SQLCommands;

public class Game {
	private int ID=0;
	private int hash=0;
	private String name, link, discountS="",tagsS="";
	private DDate reliseDate, addDate;
	private DList tags = new DList();
	private double price, originalPrice, discount;
	private double wishNumber; //TODO rename
	private Rating rate;
	/**
	 * only the path because we don't want to keeping all images in RAM 
	 */
	private String imagePath; 
	
	public Game() {
		
	}
	public Game(int id,int hash, String name, String link, DDate reliseDate, DDate addDate, DList tags, double price,
			double orginalPrice, double discount, double wishNumber, Rating rate, String imagePath) {
		super();
		this.ID=id;
		this.hash=hash;
		this.name = name;
		this.link = link;
		this.reliseDate = reliseDate;
		this.addDate = addDate;
		this.tags = tags;
		this.price = price;
		this.originalPrice = orginalPrice;
		this.discount = discount;
		this.wishNumber = wishNumber;
		this.rate = rate;
		this.imagePath = imagePath;
	}
	public Game(int id,int hash,String name, String link, DDate reliseDate, DDate addDate, String[] tags, double price,
			double orginalPrice, double discount, double wishNumber, Rating rate, String imagePath) {
		super();
		this.ID=id;
		this.hash=hash;
		this.name = name;
		this.link = link;
		this.reliseDate = reliseDate;
		this.addDate = addDate;
		for(int i=0;i<tags.length;i++) {
			this.tags.add(tags[i]);
		}
		this.price = price;
		this.originalPrice = orginalPrice;
		this.discount = discount;
		this.wishNumber = wishNumber;
		this.rate = rate;
		this.imagePath = imagePath;
	}
	public Game(int id,int hash,String name, String link, String reliseDate, String addDate, DList tags, String price,
			String orginalPrice, String discount, double wishNumber, Rating rate, String imagePath) {
		super();
		this.ID=id;
		this.hash=hash;
		this.name =name.replaceAll("\\\\u\\w\\w\\w\\w", "");
		this.link = link;
		this.reliseDate = new DDate(Long.valueOf(reliseDate.equals("") ? "0" : reliseDate)*1000);
		this.addDate = new DDate(Long.valueOf(addDate.equals("") ? "0" : addDate)*1000); 
		this.tags=tags;
		this.tags.forEach(e->e.replaceAll("[>\\[\\]\"]", ""));
		this.price = Double.valueOf(price.equals("") ? "0" : price)/100;
		this.originalPrice = Double.valueOf(orginalPrice.equals("") ? "0" : orginalPrice.replaceAll("[>,]", ""))/100;
		this.discount = Double.valueOf(discount.equals("") ? "0" : discount)/100;
		this.wishNumber = wishNumber;
		this.rate = rate;
		this.imagePath = imagePath;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getReliseDate() {
		return reliseDate;
	}

	public void setReliseDate(DDate reliseDate) {
		this.reliseDate = reliseDate;
	}

	public DDate getAddDate() {
		return addDate;
	}

	public void setAddDate(DDate addDate) {
		this.addDate = addDate;
	}

	public DList getTags() {
		return tags;
	}

	public void setTags(DList tags) {
		this.tags = tags;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double orginalPrice) {
		this.originalPrice = orginalPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public double getWishNumber() {
		return wishNumber;
	}


	public void setWishNumber(double wishNumber) {
		this.wishNumber = wishNumber;
	}


	public Rating getRate() {
		return rate;
	}


	public void setRate(Rating rate) {
		this.rate = rate;
	}
	public static String[] toTableNames() {	
		String[] result = {Language.texts.getString("hash"),Language.texts.getString("title"),Language.texts.getString("price"),Language.texts.getString("originalPrice"),Language.texts.getString("discount"),Language.texts.getString("releasedDate"),Language.texts.getString("wishNumber"),Language.texts.getString("rate"),Language.texts.getString("tags"),Language.texts.getString("addDate")};
		return result;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getHash() {
		return hash;
	}
	public void setHash(int hash) {
		this.hash = hash;
	}
	public String valuesToString() {
		return ID+", '"+name.replaceAll("'","''")+"', '"+link+"', "+reliseDate.getTime()+", "+addDate.getTime()+", '"+tags.toString()+"', "+price+", "+originalPrice+", "+discount+", "+wishNumber+", "+rate.getPositive()+", "+rate.getAllRate()+", '"+imagePath+"'";
	}
	public String valuesAndColumnsToString() {
		return GameColumns.ID+SQLCommands.EQUALS+ID+", "+GameColumns.NAME+SQLCommands.EQUALS+"'"+name.replaceAll("'","''")+"', "+GameColumns.LINK+SQLCommands.EQUALS+"'"+link+"', "+GameColumns.RELEASED_DATE+SQLCommands.EQUALS+reliseDate.getTime()+", "+GameColumns.ADD_DATE+SQLCommands.EQUALS+addDate.getTime()+", "+GameColumns.TAGS+SQLCommands.EQUALS+"'"+tags.toString()+"', "+GameColumns.PRICE+SQLCommands.EQUALS+price+", "+GameColumns.ORIGINAL_PRICE+SQLCommands.EQUALS+originalPrice+", "+GameColumns.DISCOUNT+SQLCommands.EQUALS+discount+", "+GameColumns.WISH_NUMBER+SQLCommands.EQUALS+wishNumber+", "+GameColumns.RATE_POSITIVE+SQLCommands.EQUALS+rate.getPositive()+", "+GameColumns.RATE_ALL+SQLCommands.EQUALS+rate.getAllRate()+", "+GameColumns.IMAGE_PATH+SQLCommands.EQUALS+"'"+imagePath+"'";
	}
	public String getDiscountS() {
		return discountS.equals("") ? String.valueOf(discount*100)+"%" : discountS;
	}
	public void setDiscount() {
		this.discountS=String.valueOf(discount*100)+"%";
	}
	public void setDiscountS(String discountS) {
		this.discountS = discountS;
	}
	public String getTagsS() {
		return tags.toString();
	}
	public void setTagsS(String tagsS) {
		this.tagsS = tagsS;
	}
}
