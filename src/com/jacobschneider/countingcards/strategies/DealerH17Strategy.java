package com.jacobschneider.countingcards.strategies;

import com.jacobschneider.countingcards.framework.PlayStrategy;
import com.jacobschneider.countingcards.game.Card;
import com.jacobschneider.countingcards.game.Hand;

/**
 * A PlayStrategy implementation for a dealer. These rules are popular in casinos
 * and H17 means the dealer hits on soft 17.
 * 
 * @author Jacob
 *
 */
public class DealerH17Strategy implements PlayStrategy {


	@Override
	public Action decideAction(Hand dealer, Card doNotUse) {
		//System.out.println("****Dealer Deciding****");
		//dealer.printCards();
		if (dealer.getMaxValueUnder22() < 17 || dealer.isSoft17()) {
			//System.out.println("***Dealer Decision: " + Action.Hit);
			return Action.Hit;
		} else {
			//System.out.println("***Dealer Decision: " + Action.Stand);
			return Action.Stand;
		} 
	}

}
