package com.sawyerhood.crosscard.actors;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;

public class BoardClickListener extends ClickListener {
  private CrossCardGameManager gm;
  private static Sound cardDrawSound = Gdx.audio.newSound(Gdx.files.internal("card_play.wav"));
  
  public BoardClickListener(CrossCardGameManager gm) {
    this.gm = gm;

  }

  public void clicked(InputEvent event, float x, float y) {
    CardActor thisCard = ((CardActor) event.getListenerActor());
    if (gm.getBoard().placeCard(thisCard.row, thisCard.col,
        gm.getCurrentPlayer().getCurrentCard())) {
      gm.getCurrentPlayer().popCard();
      gm.nextTurn();
      cardDrawSound.play();
    }

  }
}
