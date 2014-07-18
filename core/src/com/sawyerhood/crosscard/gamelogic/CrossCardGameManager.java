package com.sawyerhood.crosscard.gamelogic;


import com.sawyerhood.crosscard.gamelogic.interfaces.TurnManager;

/**
 * 
 * @author Sawyer Hood
 * 
 */
public class CrossCardGameManager {
  // TODO implement this class.
  private TurnManager turnManager;
  private CrossCardDeck deck;
  private CrossCardBoard board;

  public CrossCardGameManager(TurnManager turnManager, CrossCardDeck deck, CrossCardBoard board) {
    this.turnManager = turnManager;
    this.deck = deck;
    this.board = board;
  }

  /**
   * Returns true if the game is over, false otherwise.
   * 
   * @return
   */
  public boolean isGameOver() {
    return board.isBoardFull();
  }

  /**
   * Used to play cards.
   * 
   * @param row
   * @param col
   * @return
   */
  public boolean playCard(int row, int col) {
    return true;
  }

  /**
   * Used to reserve cards.
   * 
   * @return
   */
  public boolean reserveCard() {
    return true;
  }

  /**
   * Moves on to the next turn.
   */
  public void nextTurn() {

  }

  /**
   * Draws a card.
   * 
   * @return
   */
  public CrossCard drawCard() {
    return null;
  }

  /**
   * Gets the current player
   * 
   * @return
   */
  public CrossCardPlayer getCurrentPlayer() {
    return null;
  }

  /**
   * Gets the current board.
   * 
   * @return
   */
  public CrossCardBoard getBoard() {
    return null;
  }


}
