package com.jacobschneider.countingcards.framework;

import com.jacobschneider.countingcards.game.Card;

/**
 * Defines an interface that can be drawn from.
 * 
 * @author Jacob
 *
 */
public interface Drawable {
	/**
	 * Returns a card from the top of the Drawable
	 * 
	 * @return - A card object
	 */
	public Card drawCard();
	
	/**
	 * @return - is the drawable Empty?
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @return - how many cards are left in the Drawable
	 */
	public int cardsLeft();
	
	/**
	 * Adds a betting strategy listener to the Drawable object.
	 * 
	 * @param betStrat - The BettingStrategy implementation to attach to the Drawable
	 */
	public void addDrawListener(BettingStrategy betStrat);

}
