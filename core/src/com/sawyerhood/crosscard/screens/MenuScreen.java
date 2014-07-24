package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sawyerhood.crosscard.CrossCardGame;

public abstract class MenuScreen extends AbstractScreen {

  protected Stage menuStage;
  protected Skin uiSkin;
  protected Table table;

  public MenuScreen(CrossCardGame game) {
    super(game);
    menuStage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(menuStage);
    table = new Table();
    table.setFillParent(true);
    menuStage.addActor(table);
    uiSkin = game.getAssetManger().get("uiskin.json", Skin.class);


  }

  @Override
  public void render(float delta) {

    menuStage.act(delta);
    menuStage.draw();
  }

  @Override
  public void resize(int width, int height) {
    menuStage.getViewport().update(width, height, true);

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
    // uiSkin.dispose();

  }

}
