package com.sawyerhood.crosscard.screens;

import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.gamelogic.CrossCardAI;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;
import com.sawyerhood.crosscard.gamelogic.CrossCardTurnManager;
import com.sawyerhood.crosscard.gamelogic.Helpers;

/**
 * The gameplay screen for a single player match.
 * 
 * @author Sawyer Hood
 *
 */
public class SingleplayerGameplayScreen extends GameplayScreen {

  /**
   * Create a new single player gameplay screen.
   * 
   * @param game the game
   */
  public SingleplayerGameplayScreen(CrossCardGame game) {
    super(game);
    gameType = "singleplayer";
  }

  /**
   * Manage the turn state.
   */
  @Override
  protected void turnHandle() {
    if (gameManager.getCurrentPlayer() instanceof CrossCardAI) {
      ((CrossCardAI) gameManager.getCurrentPlayer()).turn(gameManager.getBoard(),
          gameManager.getDeck());
      gameManager.nextTurn();
    }
  }

  /**
   * Initialize the game manager.
   */
  @Override
  protected void initGameManager() {
    gameManager =
        new CrossCardGameManager(new CrossCardTurnManager(Helpers.generateAIPlayers(game
            .getDifficulty())));
  }



}
