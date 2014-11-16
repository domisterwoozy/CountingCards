package com.jacobschneider.countingcards.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import com.jacobschneider.countingcards.framework.BettingStrategy;
import com.jacobschneider.countingcards.framework.Drawable;
import com.jacobschneider.countingcards.game.Card.CardNumber;
import com.jacobschneider.countingcards.game.Card.CardSuit;

/**
 * A Drawable implementation that represents a classic 52 card deck.
 * 
 * @author Jacob
 *
 */
public class Deck implements Drawable {
	private Deque<Card> cards = new ArrayDeque<Card>();
	private List<BettingStrategy> listeners = new ArrayList<BettingStrategy>();
	
	private static final List<Card> unshuffledCards = new ArrayList<Card>();
	
	/**
	 * 
	 * @return - A List of 52 cards sorted by suit and number
	 */
	public static List<Card> getUnshuffledDeck() {
		List<Card> cards = new ArrayList<Card>();
		cards.addAll(unshuffledCards);
		return cards;
	}
	
	static {
		unshuffledCards.add(new Card(CardNumber.Ace, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Two, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Three, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Four, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Five, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Six, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Seven, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Eight, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Nine, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Ten, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Jack, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Queen, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.King, CardSuit.Spades));
		unshuffledCards.add(new Card(CardNumber.Ace, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Two, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Three, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Four, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Five, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Six, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Seven, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Eight, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Nine, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Ten, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Jack, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Queen, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.King, CardSuit.Hearts));
		unshuffledCards.add(new Card(CardNumber.Ace, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Two, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Three, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Four, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Five, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Six, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Seven, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Eight, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Nine, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Ten, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Jack, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Queen, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.King, CardSuit.Clubs));
		unshuffledCards.add(new Card(CardNumber.Ace, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Two, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Three, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Four, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Five, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Six, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Seven, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Eight, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Nine, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Ten, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Jack, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.Queen, CardSuit.Diamonds));
		unshuffledCards.add(new Card(CardNumber.King, CardSuit.Diamonds));
	}
	
	private Deck(List<Card> cards) {
		this.cards.addAll(cards);
	}
	
	/**
	 * Static factory for the Deck class.
	 * 
	 * @return - a Deck with shuffled cards.
	 */
	public static Deck createShuffledDeck() {
		List<Card> shuffledCards = new ArrayList<Card>();
		shuffledCards.addAll(unshuffledCards);
		Collections.shuffle(shuffledCards);
		return new Deck(shuffledCards);
	}

	@Override
	public Card drawCard() {
		if (!isEmpty()) {
			Card draw = cards.pop();
			for (BettingStrategy strat : listeners) {
				strat.readDealtCard(draw);
			}
			return draw;
		}
		throw new IllegalStateException("Cannot draw card from an empty deck");
	}

	@Override
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	@Override
	public int cardsLeft() {
		return cards.size();
	}

	@Override
	public void addDrawListener(BettingStrategy betStrat) {
		listeners.add(betStrat);		
	}
	

}
