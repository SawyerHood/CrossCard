package com.sawyerhood.crosscard.actors;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sawyerhood.crosscard.gamelogic.CrossCard;

public class MenuCardActor extends CardActor {
  private float speed;
  public static float distFromSide = getSideDist();
  public static float verticalStart = 280;

  public MenuCardActor(Texture texture, BitmapFont font, CrossCard card, float speed) {
    super(texture, font, card);
    this.speed = speed;
    this.setX(distFromSide);
    this.setY(-verticalStart);

  }

  @Override
  public void act(float delta) {
    super.act(delta);
    this.setY(getY() + delta * speed);
    if (getY() > Gdx.graphics.getHeight() + verticalStart) {
      setY(Gdx.graphics.getHeight() + verticalStart);
      setX(Gdx.graphics.getWidth() - this.getImageWidth() - distFromSide);
      speed = -speed;
    } else if (getY() < -verticalStart) {
      setY(-verticalStart);
      setX(distFromSide);
      speed = -speed;
    }

  }

  public static float getSideDist() {
    if (Gdx.app.getType() == Application.ApplicationType.Android) {
      return 400;
    }
    return 150;
  }

}
