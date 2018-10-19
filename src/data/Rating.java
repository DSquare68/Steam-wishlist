package data;

import java.text.DecimalFormat;

public class Rating {
	private int positive, allRate;
	
	public Rating() {
		
	}
	
	public Rating(int positive, int allRate) {
		this.positive = positive;
		this.allRate = allRate;
	}

	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getAllRate() {
		return allRate;
	}
	public void setAllRate(int allRate) {
		this.allRate = allRate;
	}
	public double getPercentPositive() {
		return (double) (Double.valueOf((double)positive/allRate).isNaN() ? 0 : (positive/allRate));
	}
	@Override
	public String toString() {
		return (new DecimalFormat("##.##").format(getPercentPositive()))+"%";
	}
	
}
