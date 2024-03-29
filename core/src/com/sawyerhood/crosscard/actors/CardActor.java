package com.sawyerhood.crosscard.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sawyerhood.crosscard.gamelogic.CrossCard;
import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;

public class CardActor extends Image {
  private BitmapFont font;
  private CrossCard card;
  public int row;
  public int col;


  public CardActor(Texture texture, BitmapFont font, CrossCard card) {
    super(texture);
    this.font = font;
    this.card = card;
    font.setScale(2f);

  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    font.setColor(0, 0, 0, 1);
    super.draw(batch, parentAlpha);
    if (card != null) {
      if (card.getCardType() != CardType.FACEDOWN && card.getCardType() != CardType.DOT) {
        font.draw(batch, Integer.toString(card.getValue()), this.getX()
            + (this.getImageWidth() - 20) / 2, this.getY() + this.getImageHeight() - 20);
      }
      font.draw(batch, card.getCardType().toString(),
          this.getX() + (this.getImageWidth() - 20) / 2, this.getY() + this.getImageHeight() - 70);
    }
  }

  public void setCard(CrossCard card) {
    this.card = card;
  }

}
