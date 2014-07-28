package com.sawyerhood.crosscard.screens;

import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.gamelogic.CrossCardAI;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;
import com.sawyerhood.crosscard.gamelogic.CrossCardTurnManager;
import com.sawyerhood.crosscard.gamelogic.Helpers;

public class SingleplayerGameplayScreen extends GameplayScreen {

  public SingleplayerGameplayScreen(CrossCardGame game) {
    super(game);
    // TODO Auto-generated constructor stub
    gameType = "singleplayer";
  }

  @Override
  protected void turnHandle() {
    if (gameManager.getCurrentPlayer() instanceof CrossCardAI) {
      ((CrossCardAI) gameManager.getCurrentPlayer()).turn(gameManager.getBoard(),
          gameManager.getDeck());
      gameManager.nextTurn();
    }
  }

  @Override
  protected void initGameManager() {
    gameManager =
        new CrossCardGameManager(new CrossCardTurnManager(Helpers.generateAIPlayers(game
            .getDifficulty())));
  }



}
