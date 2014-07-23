package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;

public class MainMenuScreen extends MenuScreen {

  public MainMenuScreen(final CrossCardGame game) {
    super(game);

    Label title = new Label("Card Cross!", uiSkin) {
      private float timeSinceFlash = (float) 0.0;

      @Override
      public void act(float delta) {
        super.act(delta);
        if (timeSinceFlash >= 2) {
          if (getColor().a >= 1.0)
            getColor().a = (float) 0.0;
          else
            getColor().a = (float) 1.0;
          timeSinceFlash = (float) 0.0;
        }
        timeSinceFlash += delta;

      }
    };

    title.setFontScale((float) 3.0);
    TextButton singlePlayerButton = new TextButton("Single Player", uiSkin);
    TextButton multiPlayerButton = new TextButton("Multiplayer", uiSkin);
    TextButton optionsButton = new TextButton("Options", uiSkin);
    TextButton creditsButton = new TextButton("Credits", uiSkin);
    TextButton exitButton = new TextButton("Exit", uiSkin);

    optionsButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new OptionsScreen(game));

      }
    });

    exitButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        Gdx.app.exit();

      }
    });

    singlePlayerButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new GameplayScreen(game));
      }
    });
    
    multiPlayerButton.addListener(new ChangeListener() {

        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new CardTestScreen(game));
        }
      });
      
    



    table.add(title).pad(25);
    table.row();
    table.add(singlePlayerButton).width(300).height(50).pad(12);
    table.row();
    table.add(multiPlayerButton).width(300).height(50).pad(12);
    table.row();
    table.add(optionsButton).width(300).height(50).pad(12);
    table.row();
    table.add(creditsButton).width(300).height(50).pad(12);
    table.row();
    table.add(exitButton).width(300).height(50).pad(12);

  }
}
