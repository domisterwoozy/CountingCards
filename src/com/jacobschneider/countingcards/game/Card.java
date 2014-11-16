package com.jacobschneider.countingcards.game;

/**
 * Represents a card object with a number and a suit. Can be directly converted to it's blackjack value.
 * 
 * @author Jacob
 *
 */
public class Card {
	public static enum CardNumber {
		Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), 
		Ten(10), Jack(10), Queen(10), King(10),
		Ace(11); // not always
		
		private int value;
		
		private CardNumber(int value) {
			this.value = value;
		}
		
		/**
		 * 
		 * @return - Blackjack value of the CardNumber. Ace returns 11 but can also be worth 1 in blackjack.
		 */
		public int getValue() {
			return value;
		}
		

		
	}
	public static enum CardSuit {
		Hearts, Spades, Diamonds, Clubs
	}

	
	public final CardNumber cardNumber;
	public final CardSuit cardSuit;
	
	
	/**
	 * 
	 * @param cardNumber - Card number (Ace through King)
	 * @param cardSuit - suit of the card
	 */
	public Card(CardNumber cardNumber, CardSuit cardSuit) {
		this.cardNumber = cardNumber;
		this.cardSuit = cardSuit;
	}

}
