package com.sawyerhood.crosscard.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.sawyerhood.crosscard.gamelogic.CrossCard;

public class MenuCardActor extends CardActor implements Poolable {
  private float speed;

  public MenuCardActor(Texture texture, BitmapFont font, CrossCard card, float speed) {
    super(texture, font, card);
    this.speed = speed;
    this.setX(150);
    this.setY(-299);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void reset() {

    setY(-299);
    setX(150);
    speed = -speed;

  }

  @Override
  public void act(float delta) {
    super.act(delta);
    this.setY(getY() + delta * speed);
    if (getY() > 800) {
      setY(800);
      setX(1030);
      speed = -speed;
    }
  }

}
