package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.CardActor;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;

public class GameplayScreen extends MenuScreen {

  private CrossCardGameManager gameManager;
  private CardActor[][] cardGrid;
  private Label playerLabel;
  private Table cardGameTable;
  private Table playerCards;
  private CardActor currentCard;
  private CardActor reserveCard;
  private Label[] vertScores;
  private Label[] horiScores;

  public GameplayScreen(CrossCardGame game) {

    super(game);
    vertScores = new Label[3];
    horiScores = new Label[3];

    for (int i = 0; i < 3; i++) {
      vertScores[i] = new Label(null, uiSkin);
      horiScores[i] = new Label(null, uiSkin);
    }


    gameManager = new CrossCardGameManager();
    gameManager.nextTurn();
    Texture cardImage = game.getAssetManger().get("card.png", Texture.class);
    BitmapFont font = game.getAssetManger().get("default.fnt", BitmapFont.class);
    playerLabel = new Label(gameManager.getCurrentPlayer().toString(), uiSkin);
    cardGrid = new CardActor[3][3];
    table.add(playerLabel).pad(15).center();
    table.row();
    cardGameTable = new Table();
    playerCards = new Table();
    currentCard = new CardActor(cardImage, font, null);
    reserveCard = new CardActor(cardImage, font, null);
    playerCards.add(currentCard).pad(15);
    playerCards.row();
    playerCards.add(reserveCard).pad(15);
    cardGameTable.add(vertScores[0]).pad(15);
    cardGameTable.add(vertScores[1]).pad(15);
    cardGameTable.add(vertScores[2]).pad(15);
    cardGameTable.row();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        CardActor card = new CardActor(cardImage, font, null);

        card.addCaptureListener(new ClickListener() {

          public void clicked(InputEvent event, float x, float y) {

            System.out.println(event.getListenerActor());

          }
        });

        cardGameTable.add(card).pad(15);
        cardGrid[i][j] = card;
      }
      cardGameTable.add(horiScores[i]).pad(15);
      cardGameTable.row();
    }
    table.add(cardGameTable);
    table.add(playerCards);
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    if (gameManager.isGameOver()) {
      // TODO Implement what to do if the game is over.
    }
    updateCards();
    playerLabel.setText(gameManager.getCurrentPlayer().toString() + "'s Turn");

  }

  private void updateCards() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        cardGrid[i][j].setCard(gameManager.getBoard().getCard(i, j));
      }
      horiScores[i].setText(Integer.toString(gameManager.getBoard().getRowValue(i)));
      vertScores[i].setText(Integer.toString(gameManager.getBoard().getColValue(i)));
    }
    currentCard.setCard(gameManager.getCurrentPlayer().getCurrentCard());
    reserveCard.setCard(gameManager.getCurrentPlayer().getReserve());

  }
}
