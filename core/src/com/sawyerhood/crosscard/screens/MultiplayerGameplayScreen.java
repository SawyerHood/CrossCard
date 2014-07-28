package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.BoardClickListener;
import com.sawyerhood.crosscard.actors.LocalBoardClickListener;

public class MultiplayerGameplayScreen extends GameplayScreen {
  private Dialog nextPlayerDialog;

  public MultiplayerGameplayScreen(CrossCardGame game) {
    super(game);
    gameType = "multiplayer";

  }

  @Override
  public void initBoard(BoardClickListener boardListener) {

    super.initBoard(new LocalBoardClickListener(gameManager, this));


    nextPlayerDialog = new Dialog("Next player", uiSkin);

    nextPlayerDialog.text("Pass the phone to the next player.");

    TextButton nextPlayerButton = new TextButton("OK", uiSkin);

    nextPlayerButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        showBoard();
      }
    });

    nextPlayerDialog.button(nextPlayerButton);

  }

  public void hideBoard() {
    table.setVisible(false);
  }

  public void showBoard() {
    table.setVisible(true);
  }

  public void showDialog() {
    nextPlayerDialog.show(menuStage);

  }
  
  private void gameOver(){
	  game.setScreen(new GameOverScreen(game, this, "multiplayer"));
  }



}
