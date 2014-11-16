package com.jacobschneider.countingcards.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import com.jacobschneider.countingcards.framework.BettingStrategy;
import com.jacobschneider.countingcards.framework.Drawable;

/**
 * Implementation of Drawable that represents a group of decks.
 * 
 * @author Jacob
 *
 */
public class Shoe implements Drawable {
	private Deque<Card> cards = new ArrayDeque<Card>();
	private int plasticCardPos = 0;
	private int numDecks;
	private List<BettingStrategy> listeners = new ArrayList<BettingStrategy>();
	
	private Shoe(Collection<Card> cards, int numDecks) {
		this.numDecks = numDecks;
		this.cards.addAll(cards);
		insertPlasticCard();		
	}
	
	/**
	 * Creates a shuffled shoe containing a certain number of decks. A plastic card is automatically inserted
	 * into the middle of the shoe to determine the last hand of the shoe.
	 * 
	 * @param numDecks - number of decks in the shoe
	 * @return - the shoe
	 */
	public static Shoe createShoe(int numDecks) {
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < numDecks; i++) {
			cards.addAll(Deck.getUnshuffledDeck());
		}
		Collections.shuffle(cards);
		return new Shoe(cards, numDecks);
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
		throw new IllegalStateException("Cannot draw card from an empty shoe");
		
	}

	@Override
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	/**
	 * Determines if the current state of the draw position is past the plastic card.
	 * If so the Shoe should be reshuffled after the current hand.
	 * 
	 * @return - if the plastic card has been passed.
	 * 
	 */
	public boolean isLastHand() {
		return (cardsLeft() < plasticCardPos);
	}
	
	/**
	 * Inserts a plastic card into the shoe that signifies that this is the last hand before a new shuffle.
	 * 
	 * @param pos - Float b/w 0 and 1 where 0 is the bottom of the shoe (last card) and 1 is the top (first card)
	 */
	public void insertPlasticCard(float pos) {
		if (pos <= 0 || pos >= 1) {
			throw new IllegalArgumentException("Position must be between 0 and 1");
		}
		plasticCardPos = (int) (cardsLeft() * pos);
	}
	
	/**
	 * Randomly a plastic card into the shoe that signifies that this is the last hand before a new shuffle.
	 */
	public void insertPlasticCard() {
		Random rand = new Random();
		insertPlasticCard(0.25f + 0.5f * rand.nextFloat());		
	}

	@Override
	public int cardsLeft() {
		return cards.size();
	}

	@Override
	public void addDrawListener(BettingStrategy betStrat) {
		listeners.add(betStrat);
		betStrat.setShoeSize(numDecks);
	}

	/**
	 * Reshuffles the shoe keeping the same number of decks. Also notifies
	 * all listeners that it has been reshuffled.
	 */
	public void reshuffle() {
		List<Card> newCards = new ArrayList<Card>();
		for (int i = 0; i < numDecks; i++) {
			newCards.addAll(Deck.getUnshuffledDeck());
		}
		Collections.shuffle(newCards);
		this.cards.clear();
		this.cards.addAll(newCards);
		insertPlasticCard();
		for (BettingStrategy listener : listeners) {
			listener.newShoe();
		}
	}

}
