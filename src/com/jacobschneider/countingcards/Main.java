package com.jacobschneider.countingcards;

import com.jacobschneider.countingcards.game.BlackJackGame;
import com.jacobschneider.countingcards.game.Player;
import com.jacobschneider.countingcards.strategies.DealerH17Strategy;
import com.jacobschneider.countingcards.strategies.DealerS17Strategy;
import com.jacobschneider.countingcards.strategies.Illustrious18;
import com.jacobschneider.countingcards.strategies.SingleLevelHighLow;
import com.jacobschneider.countingcards.strategies.StandardBetting;
import com.jacobschneider.countingcards.strategies.StandardStrategy;

public class Main {
	
	public static void main(String[] args) {
		float minBet = 1;
		float bjMult = 1.5f;
		int numDecks = 6;
		
		Illustrious18 combo = new Illustrious18(500);
		
		Player p1 = new Player("HighLow (12 spread) w/ Basic Strategy", new StandardStrategy(), new SingleLevelHighLow(500));
		Player p2 = new Player("No Counting w/ Basic Strategy", new StandardStrategy(), new StandardBetting(500));
		//Player p3 = new Player(new DealerH17Strategy(), new StandardBetting(500));
		//Player p4 = new Player(new DealerS17Strategy(), new StandardBetting(500));
		Player p5 = new Player("HighLow w/ Illustrious 18", combo, combo);
		BlackJackGame game = new BlackJackGame(new Player[] {p1, p2, p5}, numDecks, minBet, new DealerH17Strategy(), bjMult);
		game.play(400000);
		
//		System.out.println("Total Hands: " + game.getTotalHands());
//		System.out.println();
//		System.out.println("Total Profits P1: " + String.valueOf(p1.getCash()));
//		System.out.println("House Edge P1: " + (100.0f) * ((float)p1.getCash() / (float)game.getTotalHands()) / (p1.getCardCounter().getAvgMultiplier() * minBet) + "%");
//		System.out.println();
//		System.out.println("Total Profits P2: " + String.valueOf(p2.getCash()));
//		System.out.println("House Edge P2: " + (100.0f) * ((float)p2.getCash() / (float)game.getTotalHands()) / (p2.getCardCounter().getAvgMultiplier() * minBet) + "%");
		
		
	}

}
