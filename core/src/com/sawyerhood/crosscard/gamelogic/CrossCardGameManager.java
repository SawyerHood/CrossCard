package com.sawyerhood.crosscard.gamelogic;


import com.sawyerhood.crosscard.gamelogic.interfaces.TurnManager;

public class CrossCardGameManager {

  private TurnManager turnManager;
  private CrossCardDeck deck;
  private CrossCardBoard board;

  public CrossCardGameManager(TurnManager turnManager, CrossCardDeck deck, CrossCardBoard board) {
    this.turnManager = turnManager;
    this.deck = deck;
    this.board = board;
  }

  public boolean isGameOver() {
    return board.isBoardFull();
  }

  public boolean playCard(int row, int col) {
    return true;
  }

  public boolean reserveCard() {
    return true;
  }

  public void nextTurn() {

  }

  public CrossCard drawCard() {
    return null;
  }

  public CrossCardPlayer getCurrentPlayer() {
    return null;
  }

  public CrossCardBoard getBoard() {
    return null;
  }


}
