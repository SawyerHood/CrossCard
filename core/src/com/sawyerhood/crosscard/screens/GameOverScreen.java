package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;

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

    newGame.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
    	  if(gameType.equals("multiplayer")){
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
