package com.sawyerhood.crosscard.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.MenuCardActor;
import com.sawyerhood.crosscard.gamelogic.Helpers;

/**
 * The game's main menu.
 * 
 * @author Sawyer Hood
 * @author Matt Hansen
 * 
 */
public class MainMenuScreen extends BaseScreen {


  private ArrayList<MenuCardActor> cardList;
  private float timeSinceLastSpawn = 0f;
  private Texture cardImage = ((CrossCardGame) game).getAssetManger()
      .get("card.png", Texture.class);
  private BitmapFont font = ((CrossCardGame) game).getAssetManger().get("default.fnt",
      BitmapFont.class);
  public static int number_of_cards = 20;


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

    cardList = new ArrayList<MenuCardActor>();

    title.setFontScale(5.0f);

    // Single player button
    TextButton singlePlayerButton = new TextButton("Single Player", uiSkin);
    singlePlayerButton.getLabel().setFontScale(fontScale);
    singlePlayerButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new CoinFlipScreen(game, "singleplayer"));
      }
    });

    // Multiplayer button
    TextButton multiPlayerButton = new TextButton("Multiplayer", uiSkin);
    multiPlayerButton.getLabel().setFontScale(fontScale);
    multiPlayerButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(new CoinFlipScreen(game, "multiplayer"));
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

  /**
   * Specifically overriden to allow for ultra cool card flying functionality.
   */
  @Override
  public void render(float delta) {
    super.render(delta);
    timeSinceLastSpawn += delta;
    if (timeSinceLastSpawn >= 1.5f && cardList.size() < number_of_cards) {
      MenuCardActor actor = new MenuCardActor(cardImage, font, Helpers.getRandomCard(), 110);
      cardList.add(actor);
      menuStage.addActor(actor);
      timeSinceLastSpawn = 0.0f;
    }

  }


}
