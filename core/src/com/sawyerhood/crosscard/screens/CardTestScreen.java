package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import coms.sawyerhood.crosscard.actors.CardActor;

public class CardTestScreen extends MenuScreen {

  protected Texture cardImage = new Texture("card.png");

  public CardTestScreen(Game game) {
    super(game);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        table.add(new CardActor(cardImage)).pad(15);
      }
      table.row();
    }
  }
}
