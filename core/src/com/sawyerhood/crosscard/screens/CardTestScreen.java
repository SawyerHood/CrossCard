package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.CardActor;
import com.sawyerhood.crosscard.gamelogic.CrossCard;
import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;

public class CardTestScreen extends MenuScreen {

  protected Texture cardImage;
  protected BitmapFont font;

  public CardTestScreen(CrossCardGame game) {
    super(game);
    cardImage = game.getAssetManger().get("card.png", Texture.class);
    font = game.getAssetManger().get("default.fnt", BitmapFont.class);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        table.add(new CardActor(cardImage, font, new CrossCard(CardType.VERTICAL, 2))).pad(15);
      }
      table.row();
    }
  }
}
