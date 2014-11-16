package com.jacobschneider.countingcards.strategies;

import com.jacobschneider.countingcards.framework.PlayStrategy;
import com.jacobschneider.countingcards.game.Card;
import com.jacobschneider.countingcards.game.Hand;

/**
 * A PlayStrategy implementation representing standard BlackJack strategy.
 * 
 * @author Jacob
 *
 */
public class StandardStrategy implements PlayStrategy {
	private static Action[][] actions = new Action[10][33];
	
	static {
		actions[0][0] = Action.Stand;
		actions[0][1] = Action.Stand;
		actions[0][2] = Action.Stand;
		actions[0][3] = Action.Stand;
		actions[0][4] = Action.Stand;
		actions[0][5] = Action.Stand;
		actions[0][6] = Action.Hit;
		actions[0][7] = Action.DoubleDown;
		actions[0][8] = Action.DoubleDown;
		actions[0][9] = Action.Hit;
		actions[0][10] = Action.Hit;
		actions[0][11] = Action.Hit;
		actions[0][12] = Action.Hit;
		actions[0][13] = Action.Hit;
		actions[0][14] = Action.Stand;
		actions[0][15] = Action.Stand;
		actions[0][16] = Action.DoubleDown;
		actions[0][17] = Action.Hit;
		actions[0][18] = Action.Hit;
		actions[0][19] = Action.Hit;
		actions[0][20] = Action.Hit;
		actions[0][21] = Action.Hit;
		actions[0][22] = Action.Hit;
		actions[0][23] = Action.Split;
		actions[0][24] = Action.Stand;
		actions[0][25] = Action.Split;
		actions[0][26] = Action.Split;
		actions[0][27] = Action.Split;
		actions[0][28] = Action.Split;
		actions[0][29] = Action.DoubleDown;
		actions[0][30] = Action.Hit;
		actions[0][31] = Action.Split;
		actions[0][32] = Action.Split;
		
		
		actions[1][0] = Action.Stand;
		actions[1][1] = Action.Stand;
		actions[1][2] = Action.Stand;
		actions[1][3] = Action.Stand;
		actions[1][4] = Action.Stand;
		actions[1][5] = Action.Stand;
		actions[1][6] = Action.Hit;
		actions[1][7] = Action.DoubleDown;
		actions[1][8] = Action.DoubleDown;
		actions[1][9] = Action.DoubleDown;
		actions[1][10] = Action.Hit;
		actions[1][11] = Action.Hit;
		actions[1][12] = Action.Hit;
		actions[1][13] = Action.Hit;
		actions[1][14] = Action.Stand;
		actions[1][15] = Action.Stand;
		actions[1][16] = Action.DoubleDown;
		actions[1][17] = Action.DoubleDown;
		actions[1][18] = Action.Hit;
		actions[1][19] = Action.Hit;
		actions[1][20] = Action.Hit;
		actions[1][21] = Action.Hit;
		actions[1][22] = Action.Hit;
		actions[1][23] = Action.Split;
		actions[1][24] = Action.Stand;
		actions[1][25] = Action.Split;
		actions[1][26] = Action.Split;
		actions[1][27] = Action.Split;
		actions[1][28] = Action.Split;
		actions[1][29] = Action.DoubleDown;
		actions[1][30] = Action.Hit;
		actions[1][31] = Action.Split;
		actions[1][32] = Action.Split;
		
		
		actions[2][0] = Action.Stand;
		actions[2][1] = Action.Stand;
		actions[2][2] = Action.Stand;
		actions[2][3] = Action.Stand;
		actions[2][4] = Action.Stand;
		actions[2][5] = Action.Stand;
		actions[2][6] = Action.Stand;
		actions[2][7] = Action.DoubleDown;
		actions[2][8] = Action.DoubleDown;
		actions[2][9] = Action.DoubleDown;
		actions[2][10] = Action.Hit;
		actions[2][11] = Action.Hit;
		actions[2][12] = Action.Hit;
		actions[2][13] = Action.Hit;
		actions[2][14] = Action.Stand;
		actions[2][15] = Action.Stand;
		actions[2][16] = Action.DoubleDown;
		actions[2][17] = Action.DoubleDown;
		actions[2][18] = Action.DoubleDown;
		actions[2][19] = Action.DoubleDown;
		actions[2][20] = Action.Hit;
		actions[2][21] = Action.Hit;
		actions[2][22] = Action.Hit;
		actions[2][23] = Action.Split;
		actions[2][24] = Action.Stand;
		actions[2][25] = Action.Split;
		actions[2][26] = Action.Split;
		actions[2][27] = Action.Split;
		actions[2][28] = Action.Split;
		actions[2][29] = Action.DoubleDown;
		actions[2][30] = Action.Hit;
		actions[2][31] = Action.Split;
		actions[2][32] = Action.Split;
		
		
		actions[3][0] = Action.Stand;
		actions[3][1] = Action.Stand;
		actions[3][2] = Action.Stand;
		actions[3][3] = Action.Stand;
		actions[3][4] = Action.Stand;
		actions[3][5] = Action.Stand;
		actions[3][6] = Action.Stand;
		actions[3][7] = Action.DoubleDown;
		actions[3][8] = Action.DoubleDown;
		actions[3][9] = Action.DoubleDown;
		actions[3][10] = Action.Hit;
		actions[3][11] = Action.Hit;
		actions[3][12] = Action.Hit;
		actions[3][13] = Action.Hit;
		actions[3][14] = Action.Stand;
		actions[3][15] = Action.Stand;
		actions[3][16] = Action.DoubleDown;
		actions[3][17] = Action.DoubleDown;
		actions[3][18] = Action.DoubleDown;
		actions[3][19] = Action.DoubleDown;
		actions[3][20] = Action.DoubleDown;
		actions[3][21] = Action.DoubleDown;
		actions[3][22] = Action.DoubleDown;
		actions[3][23] = Action.Split;
		actions[3][24] = Action.Stand;
		actions[3][25] = Action.Split;
		actions[3][26] = Action.Split;
		actions[3][27] = Action.Split;
		actions[3][28] = Action.Split;
		actions[3][29] = Action.DoubleDown;
		actions[3][30] = Action.Split;
		actions[3][31] = Action.Split;
		actions[3][32] = Action.Split;
		
		
		actions[4][0] = Action.Stand;
		actions[4][1] = Action.Stand;
		actions[4][2] = Action.Stand;
		actions[4][3] = Action.Stand;
		actions[4][4] = Action.Stand;
		actions[4][5] = Action.Stand;
		actions[4][6] = Action.Stand;
		actions[4][7] = Action.DoubleDown;
		actions[4][8] = Action.DoubleDown;
		actions[4][9] = Action.DoubleDown;
		actions[4][10] = Action.Hit;
		actions[4][11] = Action.Hit;
		actions[4][12] = Action.Hit;
		actions[4][13] = Action.Hit;
		actions[4][14] = Action.Stand;
		actions[4][15] = Action.DoubleDown;
		actions[4][16] = Action.DoubleDown;
		actions[4][17] = Action.DoubleDown;
		actions[4][18] = Action.DoubleDown;
		actions[4][19] = Action.DoubleDown;
		actions[4][20] = Action.DoubleDown;
		actions[4][21] = Action.DoubleDown;
		actions[5][22] = Action.DoubleDown;
		actions[4][23] = Action.Split;
		actions[4][24] = Action.Stand;
		actions[4][25] = Action.Split;
		actions[4][26] = Action.Split;
		actions[4][27] = Action.Split;
		actions[4][28] = Action.Split;
		actions[4][29] = Action.DoubleDown;
		actions[4][30] = Action.Split;
		actions[4][31] = Action.Split;
		actions[4][32] = Action.Split;
		
		
		actions[5][0] = Action.Stand;
		actions[5][1] = Action.Stand;
		actions[5][2] = Action.Hit;
		actions[5][3] = Action.Hit;
		actions[5][4] = Action.Hit;
		actions[5][5] = Action.Hit;
		actions[5][6] = Action.Hit;
		actions[5][7] = Action.DoubleDown;
		actions[5][8] = Action.DoubleDown;
		actions[5][9] = Action.Hit;
		actions[5][10] = Action.Hit;
		actions[5][11] = Action.Hit;
		actions[5][12] = Action.Hit;
		actions[5][13] = Action.Hit;
		actions[5][14] = Action.Stand;
		actions[5][15] = Action.Stand;
		actions[5][16] = Action.Stand;
		actions[5][17] = Action.Hit;
		actions[5][18] = Action.Hit;
		actions[5][19] = Action.Hit;
		actions[5][20] = Action.Hit;
		actions[5][21] = Action.Hit;
		actions[5][22] = Action.Hit;
		actions[5][23] = Action.Split;
		actions[5][24] = Action.Stand;
		actions[5][25] = Action.Stand;
		actions[5][26] = Action.Split;
		actions[5][27] = Action.Split;
		actions[5][28] = Action.Hit;
		actions[5][29] = Action.DoubleDown;
		actions[5][30] = Action.Hit;
		actions[5][31] = Action.Split;
		actions[5][32] = Action.Split;
		
		
		actions[6][0] = Action.Stand;
		actions[6][1] = Action.Stand;
		actions[6][2] = Action.Hit;
		actions[6][3] = Action.Hit;
		actions[6][4] = Action.Hit;
		actions[6][5] = Action.Hit;
		actions[6][6] = Action.Hit;
		actions[6][7] = Action.DoubleDown;
		actions[6][8] = Action.DoubleDown;
		actions[6][9] = Action.Hit;
		actions[6][10] = Action.Hit;
		actions[6][11] = Action.Hit;
		actions[6][12] = Action.Hit;
		actions[6][13] = Action.Hit;
		actions[6][14] = Action.Stand;
		actions[6][15] = Action.Stand;
		actions[6][16] = Action.Stand;
		actions[6][17] = Action.Hit;
		actions[6][18] = Action.Hit;
		actions[6][19] = Action.Hit;
		actions[6][20] = Action.Hit;
		actions[6][21] = Action.Hit;
		actions[6][22] = Action.Hit;
		actions[6][23] = Action.Split;
		actions[6][24] = Action.Stand;
		actions[6][25] = Action.Split;
		actions[6][26] = Action.Split;
		actions[6][27] = Action.Hit;
		actions[6][28] = Action.Hit;
		actions[6][29] = Action.DoubleDown;
		actions[6][30] = Action.Hit;
		actions[6][31] = Action.Hit;
		actions[6][32] = Action.Hit;
		
		actions[7][0] = Action.Stand;
		actions[7][1] = Action.Stand;
		actions[7][2] = Action.Hit;
		actions[7][3] = Action.Hit;
		actions[7][4] = Action.Hit;
		actions[7][5] = Action.Hit;
		actions[7][6] = Action.Hit;
		actions[7][7] = Action.DoubleDown;
		actions[7][8] = Action.DoubleDown;
		actions[7][9] = Action.Hit;
		actions[7][10] = Action.Hit;
		actions[7][11] = Action.Hit;
		actions[7][12] = Action.Hit;
		actions[7][13] = Action.Hit;
		actions[7][14] = Action.Stand;
		actions[7][15] = Action.Stand;
		actions[7][16] = Action.Hit;
		actions[7][17] = Action.Hit;
		actions[7][18] = Action.Hit;
		actions[7][19] = Action.Hit;
		actions[7][20] = Action.Hit;
		actions[7][21] = Action.Hit;
		actions[7][22] = Action.Hit;
		actions[7][23] = Action.Split;
		actions[7][24] = Action.Stand;
		actions[7][25] = Action.Split;
		actions[7][26] = Action.Split;
		actions[7][27] = Action.Hit;
		actions[7][28] = Action.Hit;
		actions[7][29] = Action.DoubleDown;
		actions[7][30] = Action.Hit;
		actions[7][31] = Action.Hit;
		actions[7][32] = Action.Hit;
		
		
		actions[8][0] = Action.Stand;
		actions[8][1] = Action.Stand;
		actions[8][2] = Action.Hit;
		actions[8][3] = Action.Hit;
		actions[8][4] = Action.Hit;
		actions[8][5] = Action.Hit;
		actions[8][6] = Action.Hit;
		actions[8][7] = Action.DoubleDown;
		actions[8][8] = Action.Hit;
		actions[8][9] = Action.Hit;
		actions[8][10] = Action.Hit;
		actions[8][11] = Action.Hit;
		actions[8][12] = Action.Hit;
		actions[8][13] = Action.Hit;
		actions[8][14] = Action.Stand;
		actions[8][15] = Action.Stand;
		actions[8][16] = Action.Hit;
		actions[8][17] = Action.Hit;
		actions[8][18] = Action.Hit;
		actions[8][19] = Action.Hit;
		actions[8][20] = Action.Hit;
		actions[8][21] = Action.Hit;
		actions[8][22] = Action.Hit;
		actions[8][23] = Action.Split;
		actions[8][24] = Action.Stand;
		actions[8][25] = Action.Stand;
		actions[8][26] = Action.Split;
		actions[8][27] = Action.Hit;
		actions[8][28] = Action.Hit;
		actions[8][29] = Action.Hit;
		actions[8][30] = Action.Hit;
		actions[8][31] = Action.Hit;
		actions[8][32] = Action.Hit;
		
		
		actions[9][0] = Action.Stand;
		actions[9][1] = Action.Stand;
		actions[9][2] = Action.Hit;
		actions[9][3] = Action.Hit;
		actions[9][4] = Action.Hit;
		actions[9][5] = Action.Hit;
		actions[9][6] = Action.Hit;
		actions[9][7] = Action.DoubleDown;
		actions[9][8] = Action.Hit;
		actions[9][9] = Action.Hit;
		actions[9][10] = Action.Hit;
		actions[9][11] = Action.Hit;
		actions[9][12] = Action.Hit;
		actions[9][13] = Action.Hit;
		actions[9][14] = Action.Stand;
		actions[9][15] = Action.Stand;
		actions[9][16] = Action.Hit;
		actions[9][17] = Action.Hit;
		actions[9][18] = Action.Hit;
		actions[9][19] = Action.Hit;
		actions[9][20] = Action.Hit;
		actions[9][21] = Action.Hit;
		actions[9][22] = Action.Hit;
		actions[9][23] = Action.Split;
		actions[9][24] = Action.Stand;
		actions[9][25] = Action.Stand;
		actions[9][26] = Action.Split;
		actions[9][27] = Action.Hit;
		actions[9][28] = Action.Hit;
		actions[9][29] = Action.Hit;
		actions[9][30] = Action.Hit;
		actions[9][31] = Action.Hit;
		actions[9][32] = Action.Hit;
		
	}
	

	@Override
	public Action decideAction(Hand player, Card dealer) {
		int xIndex = dealer.cardNumber.getValue() - 2;
		int yIndex;
		if (player.isBlackJack()) {
			throw new IllegalStateException("black jack hands should already be paid out and removed");
		}
		if (player.isOneCard()) {
			int value = player.getValues().get(0);
			if (value < 5) {
				value = 5;
			}
			yIndex = 18 - value;
		} else if (player.isSplitable()) {
			yIndex = 34 - player.getCardOne().cardNumber.getValue();
		} else if (player.isSoft()) {
			yIndex = 34 - player.getMaxValueUnder22();
		} else if (player.getValues().get(0) >= 18) {
			yIndex = 0;
		} else {
			yIndex = 18 - player.getMaxValueUnder22();
		}
		
//			System.out.println("****Player Deciding****");
//			System.out.println("max val: " + player.getMaxValueUnder22());
//			for (int i = 0; i < player.getValues().size(); i++) {
//				System.out.println("possible val: " + player.getValues().get(i));
//			}
//			player.printCards();
//			System.out.println("Dealer shows: " + dealer.cardNumber);
//			System.out.println("****Player Decision: " + actions[xIndex][yIndex]);
		
	
		
		return actions[xIndex][yIndex];
	}

}
