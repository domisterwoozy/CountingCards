package com.jacobschneider.countingcards.strategies;

import com.jacobschneider.countingcards.framework.BettingStrategy;
import com.jacobschneider.countingcards.game.Card;

/**
 * Implementation of a basic card counting strategy. Cards 2 through 6 increase the count
 * and 10s decrease the count.
 * 
 * @author Jacob
 *
 */
public class SingleLevelHighLow implements BettingStrategy {
	private int runningCount = 0;
	private int cardsDealt = 0;
	private int numDecks;
	private float betIncr;
	
	private int numBets = 0;
	private int totalMult = 0;
	
	/**
	 * 
	 * @param spread - the highest possible bet multiplier
	 */
	public SingleLevelHighLow(int spread) {
		this.betIncr = (float) Math.pow(spread, 1.0f/5.0f);
	}
	
	public int getTrueCount() {
		return Math.round(runningCount / decksLeft());
	}

	@Override
	public float getBetMultiplier() {
		numBets++;
		float mult = 1.0f;
		//System.out.println("Running: " + String.valueOf(runningCount));
		//System.out.println("Decks left: " + String.valueOf(decksLeft()));
		int trueCount = getTrueCount();
		//System.out.println("True: " + String.valueOf(trueCount));
		if (trueCount <= 0) {
			mult = 1.0f;
		} else {
			switch (trueCount) {
				case 1:
					mult = betIncr;
				case 2:
					mult = (float) Math.pow(betIncr, 2);
				case 3:
					mult = (float) Math.pow(betIncr, 3);
				case 4:
					mult = (float) Math.pow(betIncr, 4);
				default:
					mult = (float) Math.pow(betIncr, 5);
			}
		}
		totalMult += mult;
		return mult;
	}

	@Override
	public void readDealtCard(Card card) {
		cardsDealt++;
		if (card.cardNumber.getValue() >= 2 && card.cardNumber.getValue() <= 6) {
			runningCount++;
		} else if (card.cardNumber.getValue() >= 10) {
			runningCount--;
		}		
	}

	@Override
	public void newShoe() {
		this.cardsDealt = 0;
		this.runningCount = 0;		
	}

	@Override
	public void setShoeSize(int numDecks) {
		this.numDecks = numDecks;		
	}
	
	private float decksLeft() {
		//System.out.println("Cards dealt: " + cardsDealt);
		return (52.0f * numDecks - cardsDealt) / 52.0f;
	}

	@Override
	public float getAvgMultiplier() {
		return totalMult / numBets;
	}

}
