package com.sawyerhood.crosscard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sawyerhood.crosscard.screens.MainMenuScreen;

public class CrossCardGame extends Game {
  protected SpriteBatch batch;
  protected Texture img;
  protected AssetManager assetManager;


  @Override
  public void create() {
    batch = new SpriteBatch();
    img = new Texture("badlogic.jpg");
    setScreen(new MainMenuScreen(this));

  }

  @Override
  public void render() {
    super.render();

  }

  @Override
  public void setScreen(Screen screen) {
    if (getScreen() != null)
      getScreen().dispose(); // Get rid of old screen assets.
    super.setScreen(screen);
  }
}
