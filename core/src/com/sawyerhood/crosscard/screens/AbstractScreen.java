package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.Screen;
import com.sawyerhood.crosscard.CrossCardGame;

public abstract class AbstractScreen implements Screen {
  protected CrossCardGame game;

  public AbstractScreen(final CrossCardGame game) {
    this.game = game;
  }


}
