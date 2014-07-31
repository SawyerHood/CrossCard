package com.sawyerhood.crosscardv1.gamelogic;


import java.util.List;

import com.sawyerhood.crosscardv1.gamelogic.interfaces.TurnManager;

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
    /* Random r = new Random(); */
    this.players = players;
    // this.currentIndex = r.nextInt(players.size());
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

  @Override
  public CrossCardPlayer getOtherPlayer() {
    int newIndex = currentIndex + 1;
    if (newIndex >= players.size()) {
      newIndex = 0;
    }
    return players.get(newIndex);
  }



}
