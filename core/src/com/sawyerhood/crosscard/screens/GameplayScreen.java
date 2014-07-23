package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.CardActor;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;

public class GameplayScreen extends MenuScreen {

  private CrossCardGameManager gameManager;
  private CardActor[][] cardGrid;
  private Label playerLabel;
  private Table cardGameTable;

  public GameplayScreen(CrossCardGame game) {
	  
    super(game);
    gameManager = new CrossCardGameManager();
    Texture cardImage = game.getAssetManger().get("card.png", Texture.class);
    BitmapFont font = game.getAssetManger().get("default.fnt", BitmapFont.class);
    playerLabel = new Label(gameManager.getCurrentPlayer().toString(), uiSkin);
    cardGrid = new CardActor[3][3];
    table.add(playerLabel).pad(15).center();
    
    table.row();
    cardGameTable = new Table();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        CardActor card = new CardActor(cardImage, font, null);
        
        card.addCaptureListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
              
              System.out.println(event.getListenerActor());

            }
          });
        
        cardGameTable.add(card).pad(15);
        cardGrid[i][j] = card;
      }
      cardGameTable.row();
    }
    table.add(cardGameTable);
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    if (gameManager.isGameOver()) {
      // TODO Implement what to do if the game is over.
    }
    updateCards();
    playerLabel.setText(gameManager.getCurrentPlayer().toString() + "'s Turn");

  }

  private void updateCards() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        cardGrid[i][j].setCard(gameManager.getBoard().getCard(i, j));
      }
    }
  }
}
