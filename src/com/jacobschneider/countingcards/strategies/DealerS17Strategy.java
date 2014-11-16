package com.jacobschneider.countingcards.strategies;

import com.jacobschneider.countingcards.framework.PlayStrategy;
import com.jacobschneider.countingcards.game.Card;
import com.jacobschneider.countingcards.game.Hand;

/**
 * A PlayStrategy implementation for a dealer. These rules are popular in casinos
 * and S17 means the dealer stands on soft 17.
 * 
 * @author Jacob
 *
 */
public class DealerS17Strategy implements PlayStrategy {

	@Override
	public Action decideAction(Hand dealer, Card doNotUse) {
		if (dealer.getMaxValueUnder22() < 17) {
			return Action.Hit;
		} else {
			return Action.Stand;
		}
	}

}
