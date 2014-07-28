package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;

/**
 * The game's main menu.
 * 
 * @author Sawyer Hood
 * @author Matt Hansen
 * 
 */
public class MainMenuScreen extends MenuScreen {

  /**
   * Instantiates a MainMenuScreen by populating a table full of buttons and labels.
   * 
   * @param game the game that will contain this screen
   */
  public MainMenuScreen(final CrossCardGame game) {
    super(game);

    Label title = new Label("Card Cross!", uiSkin) {
      private float timeSinceFlash = (float) 0.0;

      @Override
      public void act(float delta) {
        super.act(delta);
        if (timeSinceFlash >= 1) {
          if (getColor().a >= 1.0)
            getColor().a = (float) 0.0;
          else
            getColor().a = (float) 1.0;
          timeSinceFlash = (float) 0.0;
        }
        timeSinceFlash += delta;

      }
    };

    float fontScale = 2.0f;
    title.setFontScale(5.0f);

    // Single player button
    TextButton singlePlayerButton = new TextButton("Single Player", uiSkin);
    singlePlayerButton.getLabel().setFontScale(fontScale);
    singlePlayerButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new SingleplayerGameplayScreen(game));
      }
    });

    // Multiplayer button
    TextButton multiPlayerButton = new TextButton("Multiplayer", uiSkin);
    multiPlayerButton.getLabel().setFontScale(fontScale);
    multiPlayerButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new MultiplayerGameplayScreen(game));
      }
    });

    // Options button
    TextButton optionsButton = new TextButton("Options", uiSkin);
    optionsButton.getLabel().setFontScale(fontScale);
    optionsButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new OptionsScreen(game));
      }
    });

    // Credits button
    TextButton creditsButton = new TextButton("Credits", uiSkin);
    creditsButton.getLabel().setFontScale(fontScale);
    creditsButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new CreditsScreen(game));
      }
    });

    // Exit button
    TextButton exitButton = new TextButton("Exit", uiSkin);
    exitButton.getLabel().setFontScale(fontScale);
    exitButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        Gdx.app.exit();
      }
    });

    // table width, height, and padding
    int w = 400; // previously 300
    int h = 75; // previously 50
    int p = 14; // previously 12

    table.add(title).pad(25);
    table.row();
    table.add(singlePlayerButton).width(w).height(h).pad(p);
    table.row();
    table.add(multiPlayerButton).width(w).height(h).pad(p);
    table.row();
    table.add(optionsButton).width(w).height(h).pad(p);
    table.row();
    table.add(creditsButton).width(w).height(h).pad(p);
    table.row();
    table.add(exitButton).width(w).height(h).pad(p);

  }
}
