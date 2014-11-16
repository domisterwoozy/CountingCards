package com.jacobschneider.countingcards.game;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.SwingUtilities;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.jacobschneider.countingcards.framework.Pair;
import com.jacobschneider.countingcards.framework.PlayStrategy;
import com.jacobschneider.countingcards.framework.PlayStrategy.Action;
import com.jacobschneider.countingcards.graphs.MyLineChart;

/**
 * A game of Blackjack.
 * 
 * @author Jacob
 *
 */
public class BlackJackGame {
	public static enum Resplitting {Two, Four, Unlimited};
	public static enum SplitAces {NotAllowed, AllowedNoHit, Allowed};
	
	private final float minBet;
	private final float bjMult;
	private final PlayStrategy dealerStrategy;
	
	private Player[] players;
	private List<ArrayList<Hand>> playerHands;
	private Hand dealer;
	private final Shoe shoe;
	
	private int totalHands = 0;

	/**
	 * Creates a game of Blackjack that can be started with the play method.
	 * 
	 * @param players - an array of players participating in the game
	 * @param shoeSize - the number of decks in the shoe
	 * @param minBet - the minimum bet players can make
	 * @param dealerStrategy - an implementation of BettingStrategy that defines the dealer's actions
	 * @param bjMult - The payout multiplier when a player gets BlackJack. Usually 3/2 or 6/5.
	 */
	public BlackJackGame(Player[] players, int shoeSize, float minBet, PlayStrategy dealerStrategy, float bjMult) {
		this.players = players;
		this.playerHands = new ArrayList<ArrayList<Hand>>();
		shoe = Shoe.createShoe(shoeSize);
		this.minBet = minBet;
		this.dealerStrategy = dealerStrategy;
		this.bjMult = bjMult;
		
		for (int i = 0; i < players.length; i++) {
			shoe.addDrawListener(players[i].getCardCounter());
		}
	}
	
	/**
	 * Reshuffles the show
	 */
	public void reshuffle() {
		shoe.reshuffle();
	}
	
	/**
	 * Entry point to begin the game.
	 * 
	 * @param numShoes - number of shoes to play
	 */
	public void play(int numShoes) {
		// initialize objects for graphing
		final XYSeriesCollection dataset = new XYSeriesCollection();
		for (int i = 0; i < players.length; i++) {
			XYSeries series = new XYSeries(players[i].getName());
			dataset.addSeries(series);
		}	
		
		for (int i = 0; i < numShoes; i++) {
			// every 100 hands save the players average profits for graphing
			if (totalHands % 100 == 0 && totalHands != 0) {
				for (int j = 0; j < players.length; j++) {
					XYSeries s = (XYSeries) dataset.getSeries().get(j);					
					float edge = (100.0f) * ((float)players[j].getCash() / (float)getTotalHands()) / (players[j].getCardCounter().getAvgMultiplier() * minBet);
					s.add(totalHands, edge);					
				}
			}
			// deal hands until shoe says the end card has been reached
			while (!shoe.isLastHand()) {
				dealHand();
			}
			reshuffle();
		}
		
		// graph the data
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MyLineChart(dataset).setVisible(true);				
			}			
		});
	}
	
	public void dealHand() {		
		// reset hands
		totalHands++;
		playerHands.clear();
		for (int i = 0; i < players.length; i++) {
			ArrayList<Hand> hands = new ArrayList<Hand>();
			Hand h = Hand.emptyHand();
			h.setBet((players[i].getCardCounter().getBetMultiplier() * minBet));
			hands.add(h);
			playerHands.add(hands);
		}
		dealer = Hand.emptyHand();
		
		// deal first two cards
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < players.length; i++) {
				// players only have one hand at this point but still doing this for consistantcy
				ArrayList<Hand> hands = playerHands.get(i);
				for (Hand h : hands) {
					h.hit(shoe.drawCard());
				}
			}
			dealer.hit(shoe.drawCard());
		}	
		
		// check for bjs
		boolean dealerBJ = dealer.isBlackJack();
		for (int i = 0; i < players.length; i++) {
			ArrayList<Hand> hands = playerHands.get(i);
			Hand h = hands.get(0);
			if (h.isBlackJack()) {
				hands.remove(h);
				if (!dealerBJ) {
					players[i].winBet(h.getBet() * bjMult);
				} else {
					// push				
				}
			} else {
				if (dealerBJ) {
					players[i].looseBet(h.getBet());
				} else {
					// no one has blackjack, keep playing
				}
			}
		}
		if (dealerBJ) {
			return;
		}		
		
		// players play		
		for (int i = 0; i < players.length; i++) {
			Player activePlayer = players[i];
			ArrayList<Hand> hands = playerHands.get(i);
			ListIterator<Hand> iter = hands.listIterator();
			while (iter.hasNext()) {
				Hand h = iter.next();
				Action playerAction = null;
				do {
					playerAction = activePlayer.getPlayStrategy().decideAction(h, dealer.getCardTwo());
					switch (playerAction) {
						case DoubleDown:
							h.doubleDown(shoe.drawCard());
							break;
						case Hit:
							h.hit(shoe.drawCard());
							break;
						case Split:
							Pair<Hand,Hand> newHands = h.split();
							iter.remove();
							iter.add(newHands.first);
							iter.add(newHands.second);	
							iter.previous();
							iter.previous();
							break;
						case Stand:
							break;
						case Surrender:
							break;				
					}
				} while (playerAction != Action.DoubleDown && playerAction != Action.Split && playerAction != Action.Stand && !h.isBust());
			}			
		}
		
		// dealer plays
		Action dealerAction = null;
		do {
			dealerAction = dealerStrategy.decideAction(dealer, null);
			switch (dealerAction) {
				case Hit:
					dealer.hit(shoe.drawCard());
					break;
				case Stand:
					break;
				default:
					throw new IllegalStateException("Dealer can only hit or stand");				
			}
		} while (dealerAction != Action.Stand && !dealer.isBust());
		
		// payout
		for (int i = 0; i < players.length; i++) {
			ArrayList<Hand> hands = playerHands.get(i);
			for (Hand h : hands) {
				if (h.isBust()) {
					players[i].looseBet(h.getBet());
				} else if (dealer.isBust()) {
					players[i].winBet(h.getBet());
				} else if (h.getMaxValueUnder22() < dealer.getMaxValueUnder22()) {
					players[i].looseBet(h.getBet());
				} else if (h.getMaxValueUnder22() == dealer.getMaxValueUnder22()){
					// push
				} else {
					players[i].winBet(h.getBet());
				}
			}							
		}
		
		
		
	}

	/**
	 * 
	 * @return - the number of hands played in the game
	 */
	public int getTotalHands() {
		return totalHands;
	}


}
