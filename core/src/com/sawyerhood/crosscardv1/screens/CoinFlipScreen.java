package com.sawyerhood.crosscardv1.screens;



import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sawyerhood.crosscardv1.CrossCardGame;

public class CoinFlipScreen extends BaseScreen {
  private Label firstPlayer;
  private GameplayScreen nextScreen;
  private float timeOnScreen = 0.0f;

  public CoinFlipScreen(final CrossCardGame game, String mode) {
    super(game);
    if (mode.equals("multiplayer")) {
      nextScreen = new MultiplayerGameplayScreen(game);
    } else {
      nextScreen = new SingleplayerGameplayScreen(game);
    }

    firstPlayer =
        new Label(nextScreen.getGameManager().getCurrentPlayer() + " player won the coin toss!",
            uiSkin);
    firstPlayer.setFontScale(fontScale);


    table.add(firstPlayer).pad(15);
    table.row();



  }

  public void render(float delta) {
    super.render(delta);
    timeOnScreen += delta;
    if (timeOnScreen > 3.0f)
      game.setScreen(nextScreen);

  }
}
