package com.sawyerhood.crosscard.gamelogic.interfaces;

import com.sawyerhood.crosscard.gamelogic.CrossCardPlayer;

public interface TurnManager {

  public abstract void nextTurn();

  public abstract CrossCardPlayer getCurrentPlayer();

  public abstract CrossCardPlayer getOtherPlayer();
}
