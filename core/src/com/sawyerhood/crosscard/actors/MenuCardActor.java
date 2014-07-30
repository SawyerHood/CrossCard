package com.sawyerhood.crosscard.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sawyerhood.crosscard.gamelogic.CrossCard;

public class MenuCardActor extends CardActor {
  private float speed;
  public static float distFromSide = 150;

  public MenuCardActor(Texture texture, BitmapFont font, CrossCard card, float speed) {
    super(texture, font, card);
    this.speed = speed;
    this.setX(distFromSide);
    this.setY(-290);

  }

  @Override
  public void act(float delta) {
    super.act(delta);
    this.setY(getY() + delta * speed);
    if (getY() > 800) {
      setY(800);
      setX(Gdx.graphics.getWidth() - this.getImageWidth() - distFromSide);
      speed = -speed;
    } else if (getY() < -290) {
      setY(-260);
      setX(distFromSide);
      speed = -speed;
    }

  }

}
