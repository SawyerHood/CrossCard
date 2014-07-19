package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;

public class OptionsScreen extends MenuScreen {

  public OptionsScreen(final CrossCardGame game) {
    super(game);
    Label title = new Label("Options!", uiSkin);
    title.setScale((float) 2.0);
    TextButton toggleSoundButton = new TextButton("Toggle Sound", uiSkin);
    TextButton difficultyButton = new TextButton("Difficulty", uiSkin);
    TextButton saveSettingsButton = new TextButton("Save and Quit", uiSkin);

    table.add(title).pad(50);
    table.row();
    table.add(toggleSoundButton).width(300).height(50).pad(12);
    table.row();
    table.add(difficultyButton).width(300).height(50).pad(12);
    table.row();
    table.add(saveSettingsButton).width(300).height(50).pad(12);

    table.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new MainMenuScreen(game));

      }

    });



  }


  private void toggleSound() {

  }

  private void setAIDifficulty(String difficulty) {

  }

}
