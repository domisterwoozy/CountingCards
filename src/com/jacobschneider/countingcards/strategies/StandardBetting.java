package com.jacobschneider.countingcards.strategies;

import com.jacobschneider.countingcards.framework.BettingStrategy;
import com.jacobschneider.countingcards.game.Card;

/**
 * An BettingStrategy implementation that always bets the same.
 * @author Jacob
 *
 */
public class StandardBetting implements BettingStrategy {
	private float mult;
	
	/**
	 * 
	 * @param mult - multiple of the minimum bet to bet
	 */
	public StandardBetting(float mult) {
		this.mult = mult;
	}

	@Override
	public float getBetMultiplier() {
		return mult;
	}

	@Override
	public void readDealtCard(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newShoe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShoeSize(int numDecks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getAvgMultiplier() {
		return mult;
	}

}
