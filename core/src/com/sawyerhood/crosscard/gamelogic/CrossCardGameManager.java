package com.sawyerhood.crosscard.gamelogic;

import com.sawyerhood.crosscard.gamelogic.interfaces.TurnManager;

/**
 * Manages the game state and its end state.
 * 
 * @author Sawyer Hood
 * @author Matt Hansen
 * 
 */
public class CrossCardGameManager {

  private TurnManager turnManager;
  private CrossCardDeck deck;
  private CrossCardBoard board;

  /**
   * Default constructor
   */
  public CrossCardGameManager() {

    this(new CrossCardTurnManager(Helpers.generateStandardPlayers()), new CrossCardDeck(
        Helpers.generateStandardDeck()), new CrossCardBoard());

  }

  /**
   * Instantiates the CrossCardGameManager with the following parameters.
   * 
   * @param turnManager the turn manager
   * @param deck the deck of cards
   * @param board the game board
   */
  public CrossCardGameManager(TurnManager turnManager, CrossCardDeck deck, CrossCardBoard board) {
    this.turnManager = turnManager;
    this.deck = deck;
    this.board = board;
    nextTurn();
  }

  /**
   * Instantiates the CrossCardGameManager with only a TurnManager.
   * 
   * @param turnManager the game's turn manager
   */
  public CrossCardGameManager(TurnManager turnManager) {
    this(turnManager, new CrossCardDeck(Helpers.generateStandardDeck()), new CrossCardBoard());
  }

  /**
   * Returns true if the game is over, false otherwise.
   * 
   * @return true if board is full, false otherwise
   */
  public boolean isGameOver() {
    return board.isBoardFull();
  }

  /**
   * Used to play cards.
   * 
   * @param row the row to play the card in
   * @param col the column to play the card in
   * @return true if card played
   */
  public boolean playCard(int row, int col) {
    return board.placeCard(row, col, turnManager.getCurrentPlayer().popCard());
  }

  /**
   * Used to reserve cards.
   * 
   * @return true if card was reserved, false otherwise
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
   * @return the card on top of the deck
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
   * @return the current player
   */
  public CrossCardPlayer getCurrentPlayer() {
    return turnManager.getCurrentPlayer();
  }

  /**
   * Gets the current board.
   * 
   * @return the game board
   */
  public CrossCardBoard getBoard() {
    return board;
  }

  /**
   * Return the winner of the game.
   * 
   * @return the player who won
   */
  public CrossCardPlayer getWinner() {
    if (turnManager.getCurrentPlayer().getSide() == board.getWinner(deck)) {
      return turnManager.getCurrentPlayer();
    }
    return turnManager.getOtherPlayer();
  }

  /**
   * Return the card deck.
   * 
   * @return the card deck
   */
  public CrossCardDeck getDeck() {
    return deck;
  }
}
