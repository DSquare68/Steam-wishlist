package game;

import java.util.Date;

import data.DDate;
import data.DList;
import data.Language;
import data.Rating;
import sql.GameColumns;
import sql.SQLCommands;

public class GameScanned {
	protected int ID=0;
	protected String name, link, discountS="",tagsS="";
	protected DDate reliseDate, addDate;
	protected DList tags = new DList();
	protected double price, originalPrice, discount;
	protected Rating rate;
	/**
	 * only the path because we don't want to keeping all images in RAM 
	 */
	protected String imagePath; 
	
	public GameScanned() {
		
	}
	public GameScanned(int ID) {
		this.ID=ID;
	}
	public GameScanned(int id, String name, String link, DDate reliseDate, DDate addDate, DList tags, double price,
			double orginalPrice, double discount, Rating rate, String imagePath) {
		super();
		this.ID=id;
		this.name = name;
		this.link = link;
		this.reliseDate = reliseDate;
		this.addDate = addDate;
		this.tags = tags;
		this.price = price;
		this.originalPrice = orginalPrice;
		this.discount = discount;
		this.rate = rate;
		this.imagePath = imagePath;
	}
	public GameScanned(int id, String name, String link, DDate reliseDate, DDate addDate, String[] tags, double price,
			double orginalPrice, double discount, Rating rate, String imagePath) {
		super();
		this.ID=id;
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
		this.rate = rate;
		this.imagePath = imagePath;
	}
	public GameScanned(int id,String name, String link, String reliseDate, String addDate, DList tags, String price,
			String orginalPrice, String discount, Rating rate, String imagePath) {
		super();
		this.ID=id;
		this.name =name.replaceAll("\\\\u\\w\\w\\w\\w", "");
		this.link = link;
		this.reliseDate = new DDate(Long.valueOf(reliseDate.equals("") ? "0" : reliseDate)*1000);
		this.addDate = new DDate(Long.valueOf(addDate.equals("") ? "0" : addDate)*1000); 
		this.tags=tags;
		this.tags.forEach(e->e.replaceAll("[>\\[\\]\"]", ""));
		this.price = Double.valueOf(price.equals("") ? "0" : price)/100;
		this.originalPrice = Double.valueOf(orginalPrice.equals("") ? "0" : orginalPrice.replaceAll("[>,]", ""))/100;
		this.discount = Double.valueOf(discount.equals("") ? "0" : discount)/100;
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

	public DDate getReliseDate() {
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
	
	public Rating getRate() {
		return rate;
	}


	public void setRate(Rating rate) {
		this.rate = rate;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	
	public static String[] toTableNames() {	
		String[] result = {Language.texts.getString("title"),Language.texts.getString("price"),Language.texts.getString("originalPrice"),Language.texts.getString("discount"),Language.texts.getString("releasedDate"),Language.texts.getString("rate"),Language.texts.getString("tags"),Language.texts.getString("addDate")};
		return result;
	}
	public String valuesAndColumnsToString() {
		return GameColumns.ID+SQLCommands.EQUALS+ID;
	}
	public String valuesToString() {
		return ID+", '"+name.replaceAll("'","''")+"', '"+link+"', "+reliseDate.getTime()+", "+addDate.getTime()+", '"+tags.toString()+"', "+price+", "+originalPrice+", "+discount+", "+rate.getPositive()+", "+rate.getAllRate()+", '"+imagePath+"'";
	}
	public String getDiscountS() {
		return discountS.equals("") ? String.valueOf(discount*100)+"%" : discountS;
	}
	public String toInsert() {
		return String.valueOf(ID);
	}
	
}
