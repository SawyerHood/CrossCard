package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.gamelogic.Helpers.AIDifficulty;

public class OptionsScreen extends MenuScreen {

  private TextButton toggleSoundButton;

  public OptionsScreen(final CrossCardGame game) {
    super(game);
    final Music music = ((CrossCardGame) game).getMusic();
    Label title = new Label("Options!", uiSkin);
    title.setScale((float) 2.0);
    toggleSoundButton =
        new TextButton(music.isPlaying() ? "Toggle Sound: ON" : "Toggle Sound: OFF", uiSkin);
    TextButton difficultyButton =
        new TextButton("Difficulty : " + game.getDifficulty().toString(), uiSkin);
    TextButton saveSettingsButton = new TextButton("Save and Quit", uiSkin);
    table.add(title).pad(50);
    table.row();
    table.add(toggleSoundButton).width(300).height(50).pad(12);
    table.row();
    table.add(difficultyButton).width(300).height(50).pad(12);
    table.row();
    table.add(saveSettingsButton).width(300).height(50).pad(12);

    toggleSoundButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        toggleSound();

      }

    });

    difficultyButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        TextButton button = (TextButton) actor;
        if (game.getDifficulty() == AIDifficulty.EASY) {
          setAIDifficulty(AIDifficulty.MEDIUM);
        } else if (game.getDifficulty() == AIDifficulty.MEDIUM) {
          setAIDifficulty(AIDifficulty.HARD);
        } else if (game.getDifficulty() == AIDifficulty.HARD) {
          setAIDifficulty(AIDifficulty.EASY);
        }

        button.setText(("Difficulty : " + game.getDifficulty().toString()));

      }

    });

    saveSettingsButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {

        game.getPrefs().putBoolean("sound", music.isPlaying());
        game.getPrefs().flush();
        game.setScreen(new MainMenuScreen(game));

      }

    });



  }

  private void toggleSound() {
    Music music = ((CrossCardGame) game).getMusic();
    if (music.isPlaying()) {
      music.pause();
      toggleSoundButton.setText("Toggle Sound: OFF");
    } else {
      music.play();
      toggleSoundButton.setText("Toggle Sound: ON");
    }
  }

  private void setAIDifficulty(AIDifficulty difficulty) {
    game.setDifficulty(difficulty);
    game.getPrefs().putString("difficulty", difficulty.toString());
  }

}
