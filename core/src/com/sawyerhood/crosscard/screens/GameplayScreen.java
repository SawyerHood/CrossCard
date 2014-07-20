package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.CardActor;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;

public class GameplayScreen extends MenuScreen {

  private CrossCardGameManager gameManager;
  private CardActor[][] cardGrid;

  public GameplayScreen(CrossCardGame game) {
    super(game);
    gameManager = new CrossCardGameManager();
    Texture cardImage = game.getAssetManger().get("card.png", Texture.class);
    BitmapFont font = game.getAssetManger().get("default.fnt", BitmapFont.class);
    cardGrid = new CardActor[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        CardActor card = new CardActor(cardImage, font, null);
        table.add(card).pad(15);
        cardGrid[i][j] = card;
      }
      table.row();
    }
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    if (gameManager.isGameOver()) {
      // TODO Implement what to do if the game is over.
    }
    updateCards();

  }

  private void updateCards() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        cardGrid[i][j].setCard(gameManager.getBoard().getCard(i, j));
      }
    }
  }
}
