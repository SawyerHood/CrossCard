package com.sawyerhood.crosscardv1.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.sawyerhood.crosscardv1.gamelogic.CrossCardGameManager;
import com.sawyerhood.crosscardv1.screens.MultiplayerGameplayScreen;

public class LocalBoardClickListener extends BoardClickListener {
  private MultiplayerGameplayScreen screen;

  public LocalBoardClickListener(CrossCardGameManager gm, MultiplayerGameplayScreen screen) {
    super(gm);
    this.screen = screen;
  }

  @Override
  public void clicked(InputEvent event, float x, float y) {
    super.clicked(event, x, y);
    screen.showDialog();
    screen.hideBoard();
  }


}
