package com.sawyerhood.crosscard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sawyerhood.crosscard.screens.MainMenuScreen;

public class CrossCardGame extends Game {
  protected SpriteBatch batch;
  protected Texture img;
  private AssetManager assetManager = null;

  @Override
  public void create() {
    batch = new SpriteBatch();
    // setScreen(new MainMenuScreen(this));
    initManager();


  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if (assetManager.update()) {
      if (getScreen() == null)
        setScreen(new MainMenuScreen(this));
      super.render();
    }



  }

  @Override
  public void setScreen(Screen screen) {
    if (getScreen() != null)
      getScreen().dispose(); // Get rid of old screen assets.
    super.setScreen(screen);
  }


  public AssetManager getAssetManger() {
    return assetManager;
  }

  private void initManager() {
    assetManager = new AssetManager();
    assetManager.setLoader(Skin.class, new SkinLoader(new InternalFileHandleResolver()));
    assetManager.load("uiskin.json", Skin.class);
    assetManager.load("default.fnt", BitmapFont.class);
    assetManager.load("card.png", Texture.class);

  }
}
