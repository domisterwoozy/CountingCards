package com.jacobschneider.countingcards.framework;

import com.jacobschneider.countingcards.game.Card;

/**
 * An interface defining a players betting strategy. The specific implementation of this interface defines
 * how a players betting habits change based on the count of the current shoe. Can be combined with a class
 * that implements PlayStrategy to create a complete BlackJack strategy.
 * 
 * @author Jacob
 *
 */
public interface BettingStrategy {
	/**
	 * Based on the current state of the game's shoe this is what the player wants to bet.
	 * 
	 * @return - a multiple to be multiplied by the games minimum bet to set the player's bet.
	 */
	public float getBetMultiplier();
	
	/**
	 * A method to be called each time a card is dealt from a shoe. The BettingStrategy implementation
	 * must be attached as a listener to the Shoe in order for this method to be called.
	 * 
	 * @param card - the card that was dealt
	 */
	public void readDealtCard(Card card);
	
	/**
	 * Notifies the BettingStrategy that the shoe is being reshuffled. The BettingStrategy implementation
	 * must be attached as a listener to the Shoe in order for this method to be called.
	 */
	public void newShoe();
	
	/**
	 * Called when the BettingStrategy implementation is attached to a Shoe.
	 * 
	 * @param numDecks - the number of 52 card decks contained in the shoe after a reshuffle.
	 */
	public void setShoeSize(int numDecks);
	
	/**
	 * 
	 * @return - the average bet multiplier (total / numBets) over the course of the objects lifetime.
	 */
	public float getAvgMultiplier();
}
