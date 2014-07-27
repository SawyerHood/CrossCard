package com.sawyerhood.crosscard.gamelogic;

import com.sawyerhood.crosscard.gamelogic.Helpers.AIDifficulty;

public class CrossCardAI {
	AIDifficulty difficulty;
	public CrossCardAI(AIDifficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public void decideReserve(CrossCardBoard currentBoard, CrossCardDeck currentDeck, CrossCard currentCard, CrossCard currentReserve) {
		int[][] moves = new int[3][3];
		if (currentReserve == null) {
			//simulate reserving card
			int[][] reserveMoves = new int[3][3];
			long startTime = System.currentTimeMillis();
			while (difficulty.toTime() > System.currentTimeMillis() - startTime) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (!currentBoard.isOccupied(i, j)) {
							CrossCardBoard fantasyBoard = currentBoard.clone();							
							CrossCardDeck fantasyDeck = currentDeck.clone();
							reserveMoves[i][j] += simulate(fantasyBoard, fantasyDeck);
						}
					}
				}
			}
			
			//simulate playing card
			startTime = System.currentTimeMillis();
			while (difficulty.toTime() > System.currentTimeMillis() - startTime) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (!currentBoard.isOccupied(i, j)) {
							CrossCardBoard fantasyBoard = currentBoard.clone();	
							fantasyBoard.placeCard(i,j, currentCard);
							CrossCardDeck fantasyDeck = currentDeck.clone();
							moves[i][j] += simulate(fantasyBoard, fantasyDeck);
						}
					}
				}
			}
		}
		if (currentReserve != null) {
			//simulate playing reserve card
			long startTime = System.currentTimeMillis();
			while (difficulty.toTime() > System.currentTimeMillis() - startTime) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (!currentBoard.isOccupied(i, j)) {
							CrossCardBoard fantasyBoard = currentBoard.clone();	
							fantasyBoard.placeCard(i,j, currentReserve);
							CrossCardDeck fantasyDeck = currentDeck.clone();
							moves[i][j] += simulate(fantasyBoard, fantasyDeck);
						}
					}
				}
			}
			//simulate playing from deck
			int[][] reserveMoves = new int[3][3];
			startTime = System.currentTimeMillis();
			while (difficulty.toTime() > System.currentTimeMillis() - startTime) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (!currentBoard.isOccupied(i, j)) {
							CrossCardBoard fantasyBoard = currentBoard.clone();							
							CrossCardDeck fantasyDeck = currentDeck.clone();
							reserveMoves[i][j] += simulate(fantasyBoard, fantasyDeck);
						}
					}
				}
			}
		}
	}
	
	//needs to know which orientation it is for scoring
	public void decideTurn(CrossCardBoard currentBoard, CrossCardDeck currentDeck, CrossCard currentCard) {
		int[][] moves = new int[3][3];
		long startTime = System.currentTimeMillis();
		while (difficulty.toTime() > System.currentTimeMillis() - startTime) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (!currentBoard.isOccupied(i, j)) {
						CrossCardBoard fantasyBoard = currentBoard.clone();
						fantasyBoard.placeCard(i,j, currentCard);
						CrossCardDeck fantasyDeck = currentDeck.clone();
						moves[i][j] += simulate(fantasyBoard, fantasyDeck);
					}
				}
			}
		}
	}
	
	//assume playing vertical
	public int simulate(CrossCardBoard board, CrossCardDeck deck) {
		for (int i=0; i < 3; i++) {
			for (int j=0; j < 3; j++) {
				if (!board.isOccupied(i,j)) {
					board.placeCard(i,j, deck.draw());
				}
			}
		}
		if (board.getMaxColValue() > board.getMaxRowValue())
			return 1;
		else
			return 0;
	}
	
	
}
