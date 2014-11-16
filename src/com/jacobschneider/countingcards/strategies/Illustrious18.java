package com.jacobschneider.countingcards.strategies;

import com.jacobschneider.countingcards.framework.BettingStrategy;
import com.jacobschneider.countingcards.framework.PlayStrategy;
import com.jacobschneider.countingcards.game.Card;
import com.jacobschneider.countingcards.game.Hand;

/**
 * Acts as both a BettingStrategy and PlayStrategy by implementing both interfaces.
 * Under the hood it is standard high low counting and basic strategy except for in 18 specific situations.
 * These 18 specific situations are called the Illustrious18.
 * 
 * @author Jacob
 *
 */
public class Illustrious18 implements BettingStrategy, PlayStrategy {
	private SingleLevelHighLow betStrat;
	private StandardStrategy playStrat;
	
	/**
	 * 
	 * @param spread - spread in the betting multiplier
	 */
	public Illustrious18(int spread) {
		this.betStrat = new SingleLevelHighLow(spread);
		this.playStrat = new StandardStrategy();
	}

	@Override
	public Action decideAction(Hand player, Card dealer) {
		int trueCount = betStrat.getTrueCount();
		int dealerVal = dealer.cardNumber.getValue();
		if (player.isSoft()) {
			return playStrat.decideAction(player, dealer);
		} else if (player.isSplitable()) {			
			if (player.getCardOne().cardNumber.getValue() == 10 && (dealerVal == 6 || dealerVal == 5)) {
				if (trueCount >= 5) {
					return Action.Split;
				}
			}
			return playStrat.decideAction(player, dealer);
		}
		switch (player.getMaxValueUnder22()) {
		case 16:
			if (dealerVal == 10 && trueCount >= 0) {
				return Action.Stand;
			} else if (dealerVal == 9 && trueCount >= 5) {
				return Action.Stand;
			}
			break;
		case 15:
			if (dealerVal == 10 && trueCount >= 4) {
				return Action.Stand;
			}
			break;
		case 13:
			if (dealerVal == 2 && trueCount < 0) {
				return Action.Hit;
			} else if (dealerVal == 3 && trueCount < -1) {
				return Action.Hit;
			}
			break;
		case 12:
			if (dealerVal == 2 && trueCount >= 4) {
				return Action.Stand;
			} else if (dealerVal == 3 && trueCount >= 2) {
				return Action.Stand;
			} else if (dealerVal == 4 && trueCount < 0) {
				return Action.Hit;
			} else if (dealerVal == 5 && trueCount < -1) {
				return Action.Hit;
			} else if (dealerVal == 6 && trueCount < 0) {
				return Action.Hit;
			}
			break;
		case 11:
			if (dealerVal == 11 && trueCount >= 1) {
				return Action.DoubleDown;
			}
			break;
		case 10:
			if (dealerVal == 11 && trueCount >= 4) {
				return Action.DoubleDown;
			} else if (dealerVal == 10 && trueCount >= 4) {
				return Action.DoubleDown;
			}
			break;
		case 9:
			if (dealerVal == 7 && trueCount >= 4) {
				return Action.DoubleDown;
			} else if (dealerVal == 2 && trueCount >= 1) {
				return Action.DoubleDown;
			}
			break;			
		}
		return playStrat.decideAction(player, dealer);
	}

	@Override
	public float getBetMultiplier() {
		return betStrat.getBetMultiplier();
	}

	@Override
	public void readDealtCard(Card card) {
		betStrat.readDealtCard(card);		
	}

	@Override
	public void newShoe() {
		betStrat.newShoe();		
	}

	@Override
	public void setShoeSize(int numDecks) {
		betStrat.setShoeSize(numDecks);		
	}

	@Override
	public float getAvgMultiplier() {
		return betStrat.getAvgMultiplier();
	}

}
