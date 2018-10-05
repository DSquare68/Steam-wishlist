package game;

import sql.GameColumns;
import sql.SQLCommands;

public class GameHistory {
	private int ID, PK;
	private double price, originalPrice, discount;
	private long discountDate;
	public GameHistory() {
		
	}
	public GameHistory(int PK, int iD, double price, double originalPrice, double discount, long discountDate) {
		super();
		this.PK=PK;
		this.ID = iD;
		this.price = price;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.discountDate = discountDate;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public long getDiscountDate() {
		return discountDate;
	}
	public void setDiscountDate(long discountDate) {
		this.discountDate = discountDate;
	}
	public int getPK() {
		return PK;
	}
	public void setPK(int pK) {
		PK = pK;
	}
	public String valuesAndColumnsToString() {
		return GameColumns.PK+SQLCommands.EQUALS+PK+", "+GameColumns.ID+SQLCommands.EQUALS+ID+", "+GameColumns.PRICE+SQLCommands.EQUALS+price+", "+GameColumns.ORIGINAL_PRICE+SQLCommands.EQUALS+ID+", "+GameColumns.ID+SQLCommands.EQUALS+originalPrice+", "+GameColumns.DISCOUNT+SQLCommands.EQUALS+discount+", "+GameColumns.DISCOUNT_DATE+SQLCommands.EQUALS+discountDate;
	}
	public String toInsert() {
		return ID+", "+price+", "+originalPrice+", "+discount+", "+discountDate;
	}
	
}
