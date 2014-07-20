package com.sawyerhood.crosscard.screens;

import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;

public class GameplayScreen extends MenuScreen {

  public GameplayScreen(CrossCardGame game) {
    super(game);
    gameManager = new CrossCardGameManager();
  }

  private CrossCardGameManager gameManager;

  @Override
  public void render(float delta) {
    // TODO Auto-generated method stub

  }

  @Override
  public void resize(int width, int height) {
    // TODO Auto-generated method stub

  }

  @Override
  public void show() {
    // TODO Auto-generated method stub

  }

  @Override
  public void hide() {
    // TODO Auto-generated method stub

  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub

  }

  @Override
  public void resume() {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

}
