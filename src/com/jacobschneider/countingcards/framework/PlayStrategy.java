package com.jacobschneider.countingcards.framework;

import com.jacobschneider.countingcards.game.Card;
import com.jacobschneider.countingcards.game.Hand;

/**
 * An interface defining a players play strategy. The specific implementation of this interface defines
 * a player's decision based off of the current hand a dealer's shown card. Can be combined with a class
 * that implements PlayStrategy to create a complete BlackJack strategy.
 * 
 * @author Jacob
 *
 */
public interface PlayStrategy {
	/**
	 * A complete list of actions a player can take on each turn.
	 * 
	 * @author Jacob
	 *
	 */
	public static enum Action {Stand, Hit, Split, DoubleDown, Surrender};
	
	/**
	 * 
	 * @param player - the player's current hand
	 * @param dealer - the dealer's up card
	 * @return - the action a player should take
	 */
	public Action decideAction(Hand player, Card dealer);
}
