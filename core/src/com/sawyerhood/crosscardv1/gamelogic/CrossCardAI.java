package com.sawyerhood.crosscardv1.gamelogic;

import java.util.Arrays;
import java.util.Random;

import com.sawyerhood.crosscardv1.gamelogic.Helpers.AIDifficulty;
import com.sawyerhood.crosscardv1.gamelogic.Helpers.CardType;

// Needs at least 1 card in deck during simulations. Otherwise may draw into nothing, but think it
// has drawn a card.
public class CrossCardAI extends CrossCardPlayer {
  AIDifficulty difficulty;

  /**
   * Constructor for the AI
   * 
   * @param side the orientation of the AI players, vertical or horizontal
   * @param id the id of the AI player
   * @param difficulty the difficulty setting for the AI, determines computation time
   */
  public CrossCardAI(CardType side, String id, AIDifficulty difficulty) {
    super(side, id);
    this.difficulty = difficulty;
  }
  
  public static void main(String[] args) {
	  aiTest();
  }

  // This method has two parts, one if the reserve is full at the start of the
  // turn,
  // and one if the reserve is empty at the start of the turn. The code in
  // each part is similar but slightly
  // different due to the different points at which a card is drawn (or not
  // drawn).
  /**
   * Computes the actions to take during the AI's turn. Updates the board and deck with the changes
   * 
   * @param currentBoard - the active board of the game
   * @param currentDeck - the active deck of the game
   */
  public void turn(CrossCardBoard currentBoard, CrossCardDeck currentDeck) {
    int[] moves = new int[10];
    int maxIndex = 0;

    CrossCardBoard fantasyBoard = currentBoard.clone();
    CrossCardDeck fantasyDeck = currentDeck.clone();

    if (getCurrentCard() == null) {
      // draw a card for turn
      setCurrentCard(fantasyDeck.draw());
    }
    if (difficulty == AIDifficulty.EASY) {
    	boolean found = false;
    	Random rand = new Random();
    	int x,y = 0;
    	if (fantasyBoard.isBoardFull()) {
    		return;
    	}
    	while (!found) {
    		x = rand.nextInt(3);
    		y = rand.nextInt(3);
    		if (!fantasyBoard.isOccupied(x,y)) {
    			currentBoard.placeCard(x,y,getCurrentCard());
    			setCurrentCard(null);
    			currentDeck = fantasyDeck;
    			break;
    		}
    	}
    }
    
    if (difficulty == AIDifficulty.MEDIUM){
    	if (Math.random() >= .5) {
    		int[][] positions = new int[3][3];
        	int numPositions = 0;
        	boolean full = false;
        	Random rand = new Random();
        	int x,y = 0;
        	if (fantasyBoard.isBoardFull()) {
        		full = true;
        	}
        	while (!full) {
        		x = rand.nextInt(3);
        		y = rand.nextInt(3);
        		if (!fantasyBoard.isOccupied(x,y)) {
        			currentBoard.placeCard(x,y,getCurrentCard());
        			setCurrentCard(null);
        			currentDeck = fantasyDeck;
        			break;
        		}
        	}
    	}
    	
    	else {
    		// simulate playing the drawn card
    	    simulateLoop(fantasyBoard, fantasyDeck, getCurrentCard(), moves, true);
    	
    	    // find the best move
    	    maxIndex = maxIndex(moves);
    	
    	    // if reserving is the best move
    	    if (maxIndex == 9) {
    	      // reserve the card
    	      super.reserve(getCurrentCard());
    	
    	      // draw card for turn
    	      setCurrentCard(fantasyDeck.draw());
    	
    	      // rezero moves array, reserve no longer considered
    	      Arrays.fill(moves, 0);
    	      moves[9] = -1;
    	      maxIndex = 0;
    	
    	      // simulate playing the drawn card
    	      simulateLoop(fantasyBoard, fantasyDeck, getCurrentCard(), moves, false);
    	
    	      // find the best move
    	      maxIndex = maxIndex(moves);
    	    }
    	
    	    currentBoard.placeCard(maxIndex / 3, maxIndex % 3, getCurrentCard());
    	    setCurrentCard(null);
    	    currentDeck = fantasyDeck;
    	}
    }
    
    if (difficulty == AIDifficulty.HARD) { 

	    // simulate playing the drawn card
	    simulateLoop(fantasyBoard, fantasyDeck, getCurrentCard(), moves, true);
	
	    // find the best move
	    maxIndex = maxIndex(moves);
	
	    // if reserving is the best move
	    if (maxIndex == 9) {
	      // reserve the card
	      super.reserve(getCurrentCard());
	
	      // draw card for turn
	      setCurrentCard(fantasyDeck.draw());
	
	      // rezero moves array, reserve no longer considered
	      Arrays.fill(moves, 0);
	      moves[9] = -1;
	      maxIndex = 0;
	
	      // simulate playing the drawn card
	      simulateLoop(fantasyBoard, fantasyDeck, getCurrentCard(), moves, false);
	
	      // find the best move
	      maxIndex = maxIndex(moves);
	    }
	
	    currentBoard.placeCard(maxIndex / 3, maxIndex % 3, getCurrentCard());
	    setCurrentCard(null);
	    currentDeck = fantasyDeck;
    }

    if (getReserve() != null) {
      setCurrentCard(getReserve());
      reserve(null);
    }
  }

  /**
   * Simulates the remaining moves of a single game of Card Cross
   * 
   * @param board a copy of the active board
   * @param deck a copy of the active deck
   * @return the result of the game
   */
  public int simulate(CrossCardBoard board, CrossCardDeck deck) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (!board.isOccupied(i, j)) {
          board.placeCard(i, j, deck.draw());
        }
      }
    }
    // if AI is vertical and won add 1 to move score
    if (super.getSide() == CardType.VERTICAL) {
      if (board.getMaxColValue() > board.getMaxRowValue())
        return 1;
      else
        return 0;
    }
    // if AI is horizontal and won add 1 to move score
    else {
      if (board.getMaxRowValue() > board.getMaxColValue())
        return 1;
      else
        return 0;
    }
  }

  /**
   * returns the index of the maximum value of the array
   * 
   * @param array the array to compute the max index
   * @return the index of the maximum value
   */
  public int maxIndex(int[] array) {
    int maxValue = Integer.MIN_VALUE;
    int maxIndex = 0;
    for (int i = 0; i < array.length; i++) {
      if (maxValue < array[i]) {
        maxValue = array[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  /**
   * Simulates many cross card games and returns a move array whose max index represents the best
   * move
   * 
   * @param fantasyBoard a copy of the active board
   * @param fantasyDeck a copy of the active deck
   * @param currentCard the current active card
   * @param moves an array to hold the number wins each move produces
   * @param playToReserve a boolean to determine if the reserve slot is a valid move for the
   *        simulation
   */
  public void simulateLoop(CrossCardBoard fantasyBoard, CrossCardDeck fantasyDeck,
      CrossCard currentCard, int[] moves, boolean playToReserve) {
    long startTime = System.currentTimeMillis();
    while (difficulty.toTime() > System.currentTimeMillis() - startTime) {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (!fantasyBoard.isOccupied(i, j)) {
            CrossCardBoard simulateBoard = fantasyBoard.clone();
            simulateBoard.placeCard(i, j, currentCard);
            CrossCardDeck simulateDeck = fantasyDeck.clone();
            moves[i * 3 + j] += simulate(simulateBoard, simulateDeck);
          }
        }
      }

      if (playToReserve) {
        CrossCardBoard simulateBoard = fantasyBoard.clone();
        CrossCardDeck simulateDeck = fantasyDeck.clone();
        moves[9] += simulate(simulateBoard, simulateDeck);
      }
    }
  }

  /**
   * Runs a simulation of two AI's playing against each other
   */
  public static void aiTest() {
    CrossCardBoard board;
    CrossCardDeck deck;
    CrossCardAI[] ai = new CrossCardAI[6];
    CardType winner;
    int[][] wins = new int[3][3];
    ai[0] = new CrossCardAI(CardType.VERTICAL, "veasy", AIDifficulty.EASY);
    ai[1] = new CrossCardAI(CardType.HORIZONTAL, "heasy", AIDifficulty.EASY);
    ai[2] = new CrossCardAI(CardType.VERTICAL, "vmedium", AIDifficulty.MEDIUM);
    ai[3] = new CrossCardAI(CardType.HORIZONTAL, "hmedium", AIDifficulty.MEDIUM);
    ai[4] = new CrossCardAI(CardType.VERTICAL, "vhard", AIDifficulty.HARD);
    ai[5] = new CrossCardAI(CardType.HORIZONTAL, "hhard", AIDifficulty.HARD);
    
	for (int i=0; i < 99; i++) {
		board = new CrossCardBoard();
	    deck = new CrossCardDeck(Helpers.generateStandardDeck());
	    while (!board.isBoardFull()) {
	    	ai[0].turn(board, deck);
	    	ai[3].turn(board, deck);
	    }
	    winner = board.getWinner(deck);
	    if (winner == CardType.VERTICAL) {
	    	wins[0][1]++;
	    }
	    if (winner == CardType.HORIZONTAL) {
	    	wins[1][0]++;
	    }
	    
		board = new CrossCardBoard();
	    deck = new CrossCardDeck(Helpers.generateStandardDeck());
	    while (!board.isBoardFull()) {
	    	ai[0].turn(board, deck);
	    	ai[5].turn(board, deck);
	    }
	    winner = board.getWinner(deck);
	    if (winner == CardType.VERTICAL) {
	    	wins[0][2]++;
	    }
	    if (winner == CardType.HORIZONTAL) {
	    	wins[2][0]++;
	    }
	    
		board = new CrossCardBoard();
	    deck = new CrossCardDeck(Helpers.generateStandardDeck());
	    while (!board.isBoardFull()) {
	    	ai[2].turn(board, deck);
	    	ai[5].turn(board, deck);
	    }
	    winner = board.getWinner(deck);
	    if (winner == CardType.VERTICAL) {
	    	wins[1][2]++;
	    }
	    if (winner == CardType.HORIZONTAL) {
	    	wins[2][1]++;
	    }
	}
    for (int i=0; i < 3; i++) {
    	for (int j=0; j < 3; j++) {
    		System.out.print(" " + wins[i][j] + " ");
    	}
    	System.out.print("\n");
    }
  }
}
