package com.sawyerhood.crosscard.gamelogic;


import com.sawyerhood.crosscard.gamelogic.interfaces.TurnManager;

/**
 * 
 * @author Sawyer Hood
 * 
 */
public class CrossCardGameManager {

  private TurnManager turnManager;
  private CrossCardDeck deck;
  private CrossCardBoard board;

  public CrossCardGameManager() {

    this(new CrossCardTurnManager(Helpers.generateStandardPlayers()), new CrossCardDeck(
        Helpers.generateStandardDeck()), new CrossCardBoard());
  }

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
    return board.placeCard(row, col, turnManager.getCurrentPlayer().popCard());
  }

  /**
   * Used to reserve cards.
   * 
   * @return
   */
  public boolean reserveCard() {
    boolean reserve = turnManager.getCurrentPlayer().reserve();
    if (reserve)
      drawCard();
    return reserve;
  }

  /**
   * Moves on to the next turn.
   */
  public void nextTurn() {
    turnManager.nextTurn();
    if (getCurrentPlayer().getCurrentCard() == null)
      getCurrentPlayer().giveCard(deck.draw());

  }

  /**
   * Draws a card.
   * 
   * @return
   */
  public CrossCard drawCard() {
    CrossCard card = deck.draw();
    boolean success = turnManager.getCurrentPlayer().giveCard(card);
    if (success)
      return card;
    return null;

  }

  /**
   * Gets the current player
   * 
   * @return
   */
  public CrossCardPlayer getCurrentPlayer() {
    return turnManager.getCurrentPlayer();
  }

  /**
   * Gets the current board.
   * 
   * @return
   */
  public CrossCardBoard getBoard() {
    return board;
  }


}
