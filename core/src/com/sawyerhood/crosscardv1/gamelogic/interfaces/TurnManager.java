package com.sawyerhood.crosscardv1.gamelogic.interfaces;

import com.sawyerhood.crosscardv1.gamelogic.CrossCardPlayer;

public interface TurnManager {

  public abstract void nextTurn();

  public abstract CrossCardPlayer getCurrentPlayer();

  public abstract CrossCardPlayer getOtherPlayer();
}
