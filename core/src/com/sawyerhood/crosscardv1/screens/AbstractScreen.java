package com.sawyerhood.crosscardv1.screens;

import com.badlogic.gdx.Screen;
import com.sawyerhood.crosscardv1.CrossCardGame;

public abstract class AbstractScreen implements Screen {
  protected CrossCardGame game;

  public AbstractScreen(final CrossCardGame game) {
    this.game = game;
  }


}
