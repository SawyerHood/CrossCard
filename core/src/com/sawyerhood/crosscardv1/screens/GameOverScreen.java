package com.sawyerhood.crosscardv1.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscardv1.CrossCardGame;
import com.sawyerhood.crosscardv1.gamelogic.CrossCardPlayer;

/**
 * Screen that is displayed after a match has ended.
 * 
 * @author Sawyer Hood
 * @author Matt Hansen
 * 
 */
public class GameOverScreen extends BaseScreen {

  private GameplayScreen screen;

  /**
   * Instantiates a GameOverScreen.
   * 
   * @param game the game
   * @param screen the game play screen
   * @param gameType the type of game being played
   */
  public GameOverScreen(final CrossCardGame game, final GameplayScreen screen, final String gameType) {
    super(game);
    TextButton newGame = new TextButton("Play again!", uiSkin);
    TextButton mainMenu = new TextButton("Main Menu", uiSkin);
    newGame.getLabel().setFontScale(fontScale);
    mainMenu.getLabel().setFontScale(fontScale);
    Label winnerLabel = new Label(screen.getGameManager().getWinner() + " player wins!", uiSkin);
    winnerLabel.setFontScale(3.0f);
    this.screen = screen;
    table.add(winnerLabel).pad(15);
    table.row();
    table.add(newGame).pad(15);
    table.row();
    table.add(mainMenu).pad(15);

    // only modify/display statistics on a single player game
    if (gameType.equals("singleplayer")) {
      // increment wins for the player
      int numWins = game.getPrefs().getInteger("numWins");
      int numGames = game.getPrefs().getInteger("numGames");
      numGames++;
      if (screen.getGameManager().getWinner().getClass() == CrossCardPlayer.class) {
        numWins++;
        game.getPrefs().putInteger("numWins", numWins);
      }
      game.getPrefs().putInteger("numGames", numGames);
      game.getPrefs().flush();
      table.row();
      /*
       * table.add( new Label("Your Player Statistics\n------------------------------\nWins: " +
       * numWins + "\nGames Played: " + numGames + "\nW/L Ratio: " + (float) numWins / numGames,
       * uiSkin)).pad(15);
       */
    }

    newGame.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        if (gameType.equals("singleplayer")) {
          game.setScreen(new SingleplayerGameplayScreen(game));
        } else {
          game.setScreen(new MultiplayerGameplayScreen(game));
        }


      }

    });

    mainMenu.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        screen.clear();
        game.setScreen(new MainMenuScreen(game));

      }

    });
  }
}
