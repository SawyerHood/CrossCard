package com.sawyerhood.crosscard.gamelogic;


import java.util.List;

import com.sawyerhood.crosscard.gamelogic.interfaces.TurnManager;

/**
 * Keeps track of whose turn it is.
 * 
 * @author Sawyer Hood
 * 
 */
public class CrossCardTurnManager implements TurnManager {

  private List<CrossCardPlayer> players;
  private int currentIndex;

  public CrossCardTurnManager(List<CrossCardPlayer> players) {
    this.players = players;
    this.currentIndex = 0;
  }

  /**
   * Moves to the next player's turn
   */
  @Override
  public void nextTurn() {
    currentIndex++;
    if (currentIndex >= players.size())
      currentIndex = 0;
  }

  /**
   * Returns the current player.
   */
  public CrossCardPlayer getCurrentPlayer() {
    return players.get(currentIndex);
  }



}
