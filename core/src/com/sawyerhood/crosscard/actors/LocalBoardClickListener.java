package com.sawyerhood.crosscard.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;
import com.sawyerhood.crosscard.screens.MultiplayerGameplayScreen;

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
