package com.sawyerhood.crosscard.gamelogic;

import java.util.Arrays;

import com.sawyerhood.crosscard.gamelogic.Helpers.AIDifficulty;
import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;

//Needs at least 1 card in deck during simulations. Otherwise may draw into nothing, but think it has drawn a card.
public class CrossCardAI extends CrossCardPlayer{
	AIDifficulty difficulty;
	
	public CrossCardAI(CardType side, String id, AIDifficulty difficulty) {
		super(side, id);
		this.difficulty = difficulty;
	}

	
	//This method has two parts, one if the reserve is full at the start of the turn,
	//and one if the reserve is empty at the start of the turn. The code in each part is similar but slightly
	//different due to the different points at which a card is drawn (or not drawn).
	public void turn(CrossCardBoard currentBoard, CrossCardDeck currentDeck) {
		int[] moves = new int[10];
		int maxIndex = 0;
		
		CrossCardBoard fantasyBoard = currentBoard.clone();
		CrossCardDeck fantasyDeck = currentDeck.clone();
		CrossCard cardToPlay = null;
		
		if (getReserve() != null) {			
			//simulate playing from the reserve
			simulateLoop(fantasyBoard, fantasyDeck, getReserve(), moves, true);
			
			//find the move with the most wins
			maxIndex = maxIndex(moves);
			
			
			//if keep in reserve best move
			if (maxIndex == 9) {
				//draw a card for turn
				CrossCard currentCard = fantasyDeck.draw();
				
				//rezero moves array, reserve no longer considered
				Arrays.fill(moves, 0);
				moves[9] = -1;
				maxIndex = 0;
				
				//simulate playing the drawn card
				simulateLoop(fantasyBoard, fantasyDeck, currentCard, moves, false);

				//find the best move
				maxIndex = maxIndex(moves);
				
				//play the drawn card
				cardToPlay = currentCard;
			}
			
			//if playing from reserve best move
			else {
				//play the reserve card
				cardToPlay = getReserve();
				//remove card from reserve
				super.reserve(null);
			}
			
		}
		
		if (getReserve() == null) {			
			//draw a card for turn
			CrossCard currentCard = fantasyDeck.draw();
			
			//simulate playing the drawn card
			simulateLoop(fantasyBoard, fantasyDeck, currentCard, moves, true);
			
			//find the best move
			maxIndex = maxIndex(moves);
			
			//if reserving is the best move
			if (maxIndex == 9) {
				//reserve the card
				super.reserve(currentCard);
				
				//draw card for turn
				currentCard = fantasyDeck.draw();
				
				//rezero moves array, reserve no longer considered
				Arrays.fill(moves, 0);
				moves[9] = -1;
				maxIndex = 0;
				
				//simulate playing the drawn card
				simulateLoop(fantasyBoard, fantasyDeck, currentCard, moves, false);

				//find the best move
				maxIndex = maxIndex(moves);
				
				//play the card
				cardToPlay = currentCard;
			}
			//if reserving not the best move
			else {
				//rezero moves array, reserve no long considered
				Arrays.fill(moves, 0);
				moves[9] = -1;
				maxIndex = 0;
				
				//simulate playing the drawn card
				simulateLoop(fantasyBoard, fantasyDeck, currentCard, moves, false);
				
				//find the best move
				maxIndex = maxIndex(moves);
				
				//play the card
				cardToPlay = currentCard;
			}
		}
		
		currentBoard.placeCard(maxIndex/3, maxIndex%3, cardToPlay);
		currentDeck = fantasyDeck;
	}
	
	public int simulate(CrossCardBoard board, CrossCardDeck deck) {
		for (int i=0; i < 3; i++) {
			for (int j=0; j < 3; j++) {
				if (!board.isOccupied(i,j)) {
					board.placeCard(i,j, deck.draw());
				}
			}
		}
		//if AI is vertical and won add 1 to move score
		if (super.getSide() == CardType.VERTICAL) {
			if (board.getMaxColValue() > board.getMaxRowValue())
				return 1;
			else
				return 0;
		}
		//if AI is horizontal and won add 1 to move score
		else {
			if (board.getMaxRowValue() > board.getMaxColValue())
				return 1;
			else
				return 0;
		}
	}
	
	public int maxIndex(int[] array) {
		int maxValue = Integer.MIN_VALUE;
		int maxIndex = 0;
		for (int i=0; i < array.length; i++) {
			if (maxValue < array[i]) {
				maxValue = array[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public void simulateLoop(CrossCardBoard fantasyBoard, CrossCardDeck fantasyDeck, CrossCard currentCard, int[] moves, boolean playReserve) {
		long startTime = System.currentTimeMillis();
		while (difficulty.toTime() > System.currentTimeMillis() - startTime) {
			for (int i=0; i < 3; i++) {
				for (int j=0; j < 3; j++) {
					if (!fantasyBoard.isOccupied(i, j)) {
						CrossCardBoard simulateBoard = fantasyBoard.clone();
						simulateBoard.placeCard(i, j, currentCard);
						CrossCardDeck simulateDeck = fantasyDeck.clone();
						moves[i*3 + j] += simulate(simulateBoard, simulateDeck);
					}
				}
			}
			
			if (playReserve) {
				CrossCardBoard simulateBoard = fantasyBoard.clone();
				CrossCardDeck simulateDeck = fantasyDeck.clone();
				moves[9] += simulate(simulateBoard, simulateDeck);
			}
		}
	}
	
	public static void aiTest() {
		CrossCardBoard board = new CrossCardBoard();
		CrossCardDeck deck = new CrossCardDeck(Helpers.generateStandardDeck());
		CrossCardAI ai1 = new CrossCardAI(CardType.VERTICAL, "player1", AIDifficulty.HARD);
		CrossCardAI ai2 = new CrossCardAI(CardType.HORIZONTAL, "player2", AIDifficulty.EASY);
		while (!board.isBoardFull()) {
			System.out.println("Vertical");
			ai1.turn(board, deck);
			System.out.println(board);
			System.out.println("Horizontal");
			ai2.turn(board, deck);
			System.out.println(board);
		}
		System.out.println(board.getWinner());
	}
}