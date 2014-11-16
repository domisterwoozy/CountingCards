package com.jacobschneider.countingcards.game;

import java.util.ArrayList;
import java.util.List;

import com.jacobschneider.countingcards.framework.Pair;
import com.jacobschneider.countingcards.framework.PlayStrategy.Action;
import com.jacobschneider.countingcards.game.Card.CardNumber;

/**
 * Represents a hand. Includes the hand's cards and bet.
 * 
 * @author Jacob
 *
 */
public class Hand {
	private List<Card> cards;
	private float bet;
	private boolean isDoubleDown = false;
	
	private Hand(List<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * Factory method to create a Hand with no cards and no bet.
	 * 
	 * @return - an empty hand
	 */
	public static Hand emptyHand() {
		ArrayList<Card> cards = new ArrayList<Card>();
		return new Hand(cards);		
	}
	
	/**
	 * After a split occurs two new hands are crated. This takes one card from the previous hand
	 * and returns a new Hand object.
	 * 
	 * @param card - one of the split cards from the previous Hand.
	 * @return - the new hand with one card
	 */
	private static Hand createFromSplit(Card card) {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(card);
		return new Hand(cards);	
	}
	
	/**
	 * Sets the wager for the hand
	 * @param bet - the wager
	 */
	public void setBet(float bet) {
		this.bet = bet;
	}
	
	/**
	 * 
	 * @return - the bet placed on this hand
	 */
	public float getBet() {
		return bet;
	}
	
	/**
	 * Adds a new card to the hand.
	 * 
	 * @param card - the new card
	 */
	public void hit(Card card) {
		//System.out.println("Hand hit, Card: " + card.cardNumber);
		if (isDoubleDown) {
			throw new IllegalStateException("Cannot do any actions after a double down");
		}
		cards.add(card);		
	}	
	
	/**
	 * Adds a new card to the hand and doubles the bet
	 * 
	 * @param card - the new card
	 */
	public void doubleDown(Card card) {
		//System.out.println("Doubledown, Card: " + card.cardNumber);
		if (isDoubleDown) {
			throw new IllegalStateException("Cannot do any actions after a double down");
		}
		bet *= 2;
		cards.add(card);
		isDoubleDown = true;
	}
	
	/**
	 * Converts an eligble hand into two new hands. A hand is only eligible if it has only two cards
	 * and they have the same number.
	 * 
	 * @return - a Pair object containing the two new hands.
	 */
	public Pair<Hand,Hand> split() {
		//System.out.println("Splitting Hand");
		if (isDoubleDown) {
			throw new IllegalStateException("Cannot do any actions after a double down");
		}
		if (isSplitable()) {
			Hand hand1 = Hand.createFromSplit(cards.get(0));
			Hand hand2 = Hand.createFromSplit(cards.get(1));
			hand1.setBet(bet);
			hand2.setBet(bet);
			return Pair.create(hand1, hand2);
		} else {
			throw new IllegalStateException("Cannot split a hand unelss hand has only 2 cards and both cards are the same");
		}
	}	

	/**
	 * 
	 * @return - whether or not the Hand is a BlackJack
	 */
	public boolean isBlackJack() {
		if (cards.size() == 2 && (cards.get(0).cardNumber == CardNumber.Ace || cards.get(1).cardNumber == CardNumber.Ace)
				&& getValues().get(0) == 21) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return - if an ace is being used as a 10 instead of a 1
	 */
	public boolean isSoft() {
		// soft if has ace and max value under 22 is not the lowest possible value
		return (hasAce() && getMaxValueUnder22() != getMin());
	}
	
	/**
	 * 
	 * @return - if an ace is being as a 10 and the max value if 17
	 */
	public boolean isSoft17() {
		return (isSoft() && getMaxValueUnder22() == 17);
	}
	
	/**
	 * 
	 * @return - if the hand only contains one card
	 */
	public boolean isOneCard() {
		return (cards.size() == 1);
	}
	
	/**
	 * To be splitable a Hand must have only two cards and the two cards must be the same number
	 * 
	 * @return - if the hand is splitable
	 */
	public boolean isSplitable() {
		if (cards.size() == 2 && (cards.get(0).cardNumber == cards.get(1).cardNumber)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return - first card dealt to the Hand
	 */
	public Card getCardOne() {
		return cards.get(0);
	}
	
	/**
	 * 
	 * @return - second card dealt to the Hand
	 */
	public Card getCardTwo() {
		return cards.get(1);
	}
	
	/**
	 * 
	 * @return - if the Hand contains an ace
	 */
	public boolean hasAce() {
		for (Card c : cards) {
			if (c.cardNumber == CardNumber.Ace) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return - if the hand has gone bust
	 */
	public boolean isBust() {
		List<Integer> values = getValues();
		return (values.get(values.size() - 1) > 21);
	}
	
	/**
	 * 
	 * @return - the maximum value this hand can have that is under 22
	 */
	public int getMaxValueUnder22() {
		int max = 0;
		for (Integer i : getValues()) {
			if (i > max && i < 22) {
				max = i;
			}
		}
		return max;
	}
	
	/**
	 * 
	 * @return - the maximum value this hand can have
	 */
	public int getMax() {
		return getValues().get(0);
	}
	
	/**
	 * 
	 * @return - the smallest value this hand can have
	 */
	public int getMin() {
		List<Integer> values = getValues();
		return values.get(values.size() - 1);
	}
	
	/**
	 * 
	 * @return - a List of all possible values this hand can have
	 */
	public List<Integer> getValues() {
		List<Integer> values = new ArrayList<Integer>();
		int numAces = 0;
		int sum = 0;
		for (Card c : cards) {
			sum += c.cardNumber.getValue();
			if (c.cardNumber == CardNumber.Ace) {
				numAces++;
			}
		}
		values.add(sum);
		for (int i = 0; i < numAces; i++) {
			sum -= 10;
			values.add(sum);
		}
		return values;
	}
	
	/**
	 * Debug method.
	 */
	public void printCards() {
		for (Card c : cards) {
			System.out.println("Card: " + c.cardNumber);
		}
	}
	

}
