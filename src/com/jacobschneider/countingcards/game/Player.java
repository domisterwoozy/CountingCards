package com.jacobschneider.countingcards.game;

import com.jacobschneider.countingcards.framework.BettingStrategy;
import com.jacobschneider.countingcards.framework.PlayStrategy;

/**
 * A player that can be used in a BlackJack game
 * 
 * @author Jacob
 *
 */
public class Player {
	private PlayStrategy playStrategy;
	private BettingStrategy bettingStrategy;
	private float cash = 0;
	private String name;
	
	/**
	 * 
	 * @param playStrategy - The player's PlayStrategy implementation
	 * @param bettingStrategy - The player's BettingStrategy implementation
	 */
	public Player(String name, PlayStrategy playStrategy, BettingStrategy bettingStrategy) {
		this.name = name;
		this.playStrategy = playStrategy;
		this.bettingStrategy = bettingStrategy;
	}

	/**
	 * 
	 * @return - the players PlayStrategy
	 */
	public PlayStrategy getPlayStrategy() {
		return playStrategy;
	}

	/**
	 * 
	 * @return - the players betting strategy
	 */
	public BettingStrategy getCardCounter() {
		return bettingStrategy;
	}
	
	/**
	 * Called after the player wins a hand
	 * @param amt - amount of money won
	 */
	public void winBet(float amt) {
		//System.out.println("Player Won: " + amt);
		this.cash += amt;
	}
	
	/**
	 * Called after the player loses a hand
	 * @param amt - amount of money lost
	 */
	public void looseBet(float amt) {
		//System.out.println("Player lost: " + amt);
		this.cash -= amt;
	}
	
	/**
	 * 
	 * @return - returns total cash
	 */
	public float getCash() {
		return cash;
	}

	/**
	 * 
	 * @return - name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of a player.
	 * 
	 * @param name - name of player
	 */
	public void setName(String name) {
		this.name = name;
	}


}
