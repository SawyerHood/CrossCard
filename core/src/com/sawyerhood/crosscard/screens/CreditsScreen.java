package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;

public class CreditsScreen extends BaseScreen {

  public CreditsScreen(final CrossCardGame game) {
    super(game);
    Label people =
        new Label("Sawyer Hood\n\nDavid Hill\n\nKyle McCleary\n\nBrock Stoops\n\nMatt Hansen",
            uiSkin);
    TextButton backButton = new TextButton("Back", uiSkin);
    backButton.addCaptureListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new MainMenuScreen(game));

      }

    });

    backButton.getLabel().setFontScale(2.0f);
    people.setFontScale(2.0f);
    table.add(people);
    table.row();
    table.add(backButton).pad(20);
  }
}
