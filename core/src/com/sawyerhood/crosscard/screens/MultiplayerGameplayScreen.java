package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.BoardClickListener;
import com.sawyerhood.crosscard.actors.LocalBoardClickListener;

/**
 * The multiplayer gameplay screen.
 * 
 * @author Sawyer Hood
 * 
 */
public class MultiplayerGameplayScreen extends GameplayScreen {
  private Dialog nextPlayerDialog;

  /**
   * Creates a new multiplayer screen.
   * 
   * @param game the game
   */
  public MultiplayerGameplayScreen(CrossCardGame game) {
    super(game);
    gameType = "multiplayer";

  }

  /**
   * Initializes the game board.
   * 
   * @param boardListener the board click listener
   */
  @Override
  public void initBoard(BoardClickListener boardListener) {

    super.initBoard(new LocalBoardClickListener(gameManager, this));

    nextPlayerDialog = new Dialog("Next player", uiSkin);

    nextPlayerDialog.setScale(2.0f);

    nextPlayerDialog.text("Pass the device to the next player.");



    TextButton nextPlayerButton = new TextButton("OK", uiSkin);

    nextPlayerButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        showBoard();
      }
    });

    nextPlayerDialog.button(nextPlayerButton);

  }

  /**
   * Set's the board's visibility to false.
   */
  public void hideBoard() {
    table.setVisible(false);
  }

  /**
   * Set's the board's visibility to true.
   */
  public void showBoard() {
    table.setVisible(true);
  }

  public void showDialog() {
    nextPlayerDialog.show(menuStage);
    nextPlayerDialog.setX(nextPlayerDialog.getX() - 150);
  }

  private void gameOver() {
    game.setScreen(new GameOverScreen(game, this, "multiplayer"));
  }

}
