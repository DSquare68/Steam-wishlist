package logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import data.DList;
import game.Game;
import game.GameTable;
import game.Games;

public class GameFinder {
	
	public final static String QUICK_SEARCH="quick_search";
	public final static String ADVANCE_SEARCH="advance_search";
	
	private double budget, discountWeight,wishNumberWeight,rateWeight;
	private DList tags;
	private String type;
	private ArrayList<ValueOfGame> results = new ArrayList<ValueOfGame>();
	public GameFinder() {
		
	}	
	public GameFinder(String type) {
		this.type=type;
	}

	public void randomWeight(){
		switch(type) {
			case QUICK_SEARCH:
				Random r = new Random();
				discountWeight=r.nextInt(20)/2.0;
				wishNumberWeight=r.nextInt(20)/2.0;
				rateWeight=r.nextInt(20)/2.0;
				break;
			case ADVANCE_SEARCH:
				break;
		}
	}
	public Games<GameTable> findGames(Games<GameTable> games){
		games.games.forEach(e->results.add(new ValueOfGame(calculateValue(e), e.getID())));
		results.sort(new Comparator<ValueOfGame>() {

			@Override
			public int compare(ValueOfGame o1, ValueOfGame o2) {
				System.out.println(o1.value+"   "+o2.value);
				if(o1.value==o2.value) return 0;
				else if(o1.value<o2.value) return 1;
				else if(o1.value>o2.value) return -1;
				return -100;
			}
			
		});
		games = select(games,results);
		return games;
		
	}
	private double calculateValue(Game e) {
		return e.getDiscount()*discountWeight+e.getWishNumber()*wishNumberWeight+e.getRate().getPercentPositive()*rateWeight;
	}
	private Games<GameTable> select(Games<GameTable> games, ArrayList<ValueOfGame> results) {
		double budgetOfSelected=0;
		ArrayList<GameTable> cache =new ArrayList<GameTable>();
		cache.addAll(games.getGames());
		games.getGames().removeAll(games.getGames());
		int i=0;
		while(budget>budgetOfSelected) {
			ValueOfGame gameValue = results.get(i++);
			//maybe not one by one. I think to adding more randomly
			cache.forEach(e->{if(e.getID()==gameValue.id) games.getGames().add(e);});;
			budgetOfSelected+=cache.get(i-1).getPrice();
		}
		return games;
	}
	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getDiscountWeight() {
		return discountWeight;
	}

	public void setDiscountWeight(double discountWeight) {
		this.discountWeight = discountWeight;
	}

	public double getWishNumberWeight() {
		return wishNumberWeight;
	}

	public void setWishNumberWeight(double wishNumberWeight) {
		this.wishNumberWeight = wishNumberWeight;
	}

	public double getRateWeight() {
		return rateWeight;
	}

	public void setRateWeight(double rateWeight) {
		this.rateWeight = rateWeight;
	}

	public DList getTags() {
		return tags;
	}

	public void setTags(DList tags) {
		this.tags = tags;
	}
	class ValueOfGame{
		double value;
		int id;
		public ValueOfGame(double value, int id) {
			super();
			this.value = value;
			this.id = id;
		}
		
	
	}
}
