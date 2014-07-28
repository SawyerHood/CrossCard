package com.sawyerhood.crosscard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sawyerhood.crosscard.gamelogic.Helpers.AIDifficulty;
import com.sawyerhood.crosscard.screens.MainMenuScreen;

/**
 * The game class containing the initialization of resources and rendering method.
 * 
 * @Author Sawyer Hood
 * @author Matt Hansen
 * 
 */
public class CrossCardGame extends Game {
  protected SpriteBatch batch;
  protected Texture img;
  private AssetManager assetManager = null;
  private Music music;
  private Preferences prefs;
  private AIDifficulty diff;

  @Override
  public void create() {
    batch = new SpriteBatch();
    // setScreen(new MainMenuScreen(this));
    initManager();
    prefs = Gdx.app.getPreferences("CrossCardPrefs");
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    if (assetManager.update()) {
      if (getScreen() == null) {
        setScreen(new MainMenuScreen(this));
      }
      if (music == null) {
        music = assetManager.get("music.mp3");
        music.play();
        music.setLooping(true);
        loadPrefs();
      }
      super.render();
    }
  }

  @Override
  public void setScreen(Screen screen) {
    if (getScreen() != null) {
      getScreen().dispose(); // Get rid of old screen assets.
    }
    super.setScreen(screen);
  }

  /**
   * Loads resources into the AssetManager.
   */
  private void initManager() {
    assetManager = new AssetManager();
    assetManager.setLoader(Skin.class, new SkinLoader(new InternalFileHandleResolver()));
    assetManager.load("uiskin.json", Skin.class);
    assetManager.load("default.fnt", BitmapFont.class);
    assetManager.load("card.png", Texture.class);
    assetManager.load("music.mp3", Music.class);
    assetManager.load("card_draw.wav", Sound.class);
  }

  /**
   * Returns the asset manager.
   * 
   * @return the asset manager
   */
  public AssetManager getAssetManger() {
    return assetManager;
  }

  /**
   * Get the background music.
   * 
   * @return the background music
   */
  public Music getMusic() {
    return music;
  }

  public Preferences getPrefs() {
    return prefs;
  }

  public void loadPrefs() {
    if (prefs.getBoolean("sound", true))
      music.play();
    else
      music.pause();
    String difficulty = prefs.getString("difficulty", "EASY");
    setDifficultyFromString(difficulty);
  }

  public AIDifficulty getDifficulty() {
    return diff;
  }

  public void setDifficulty(AIDifficulty d) {
    diff = d;
  }

  public void setDifficultyFromString(String s) {
    if (s.equals("EASY"))
      diff = AIDifficulty.EASY;
    else if (s.equals("MEDIUM"))
      diff = AIDifficulty.MEDIUM;
    else
      diff = AIDifficulty.HARD;
  }
}
