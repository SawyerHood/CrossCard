package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.gamelogic.CrossCardPlayer;

public class GameOverScreen extends BaseScreen {

  private GameplayScreen screen;

  public GameOverScreen(final CrossCardGame game, final GameplayScreen screen, final String gameType) {
    super(game);
    TextButton newGame = new TextButton("Play again!", uiSkin);
    TextButton mainMenu = new TextButton("Main Menu", uiSkin);
    this.screen = screen;
    table.add(new Label(screen.getGameManager().getWinner() + " player wins!", uiSkin)).pad(15);
    table.row();
    table.add(newGame).pad(15);
    table.row();
    table.add(mainMenu).pad(15);

    // only increment wins when the player won
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
    table.add(new Label("Wins: " + numWins, uiSkin)).pad(15);
    table.row();
    table.add(new Label("Games Played: " + numGames, uiSkin)).pad(15);
    table.row();
    table.add(new Label("W/L Ratio: " + (float)numWins/numGames, uiSkin)).pad(15);

    newGame.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        if (gameType.equals("multiplayer")) {
          game.setScreen(new MultiplayerGameplayScreen(game));
        } else {
          game.setScreen(new SingleplayerGameplayScreen(game));
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
