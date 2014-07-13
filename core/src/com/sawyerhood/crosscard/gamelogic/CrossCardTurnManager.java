package com.sawyerhood.crosscard.gamelogic;


import com.sawyerhood.crosscard.gamelogic.interfaces.TurnManager;

import java.util.List;

public class CrossCardTurnManager implements TurnManager {

  private List<CrossCardPlayer> players;
  private int currentIndex;

  public CrossCardTurnManager(List<CrossCardPlayer> players) {
    this.players = players;
    this.currentIndex = 0;
  }

  @Override
  public void nextTurn() {
    currentIndex++;
    if (currentIndex >= players.size())
      currentIndex = 0;
  }

  public CrossCardPlayer getCurrentPlayer() {
    return players.get(currentIndex);
  }



}
