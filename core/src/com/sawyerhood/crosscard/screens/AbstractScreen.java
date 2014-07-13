package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
  protected Game game;

  public AbstractScreen(final Game game) {
    this.game = game;
  }


}
