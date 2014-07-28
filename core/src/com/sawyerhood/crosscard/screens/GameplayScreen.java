package com.sawyerhood.crosscard.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.actors.BoardClickListener;
import com.sawyerhood.crosscard.actors.CardActor;
import com.sawyerhood.crosscard.gamelogic.CrossCardGameManager;

public class GameplayScreen extends MenuScreen {

  protected CrossCardGameManager gameManager;
  private CardActor[][] cardGrid;
  private Label playerLabel;
  private Table cardGameTable;
  private Table playerCards;
  private CardActor currentCard;
  private CardActor reserveCard;
  private Label[] vertScores;
  private Label[] horiScores;
  private boolean passingPhone = false;
  protected String gameType;

  public GameplayScreen(CrossCardGame game) {
    super(game);
    gameManager = new CrossCardGameManager();
    initBoard(new BoardClickListener(gameManager));
    gameType = "";

  }



  @Override
  public void render(float delta) {
    super.render(delta);
    if (passingPhone) {

    }
    if (gameManager.isGameOver()) {
      // TODO Implement what to do if the game is over.
      gameOver();
    }
    updateCards();
    playerLabel.setText(gameManager.getCurrentPlayer().toString() + "'s Turn");
    playerLabel.setFontScale(2f);


  }


  public void initBoard(BoardClickListener boardListener) {

    Texture cardImage = ((CrossCardGame) game).getAssetManger().get("card.png", Texture.class);
    BitmapFont font = ((CrossCardGame) game).getAssetManger().get("default.fnt", BitmapFont.class);
    Table reserveTable = new Table();
    TextButton reserveButton = new TextButton("Reserve", uiSkin);
    cardGameTable = new Table();
    playerCards = new Table();
    currentCard = new CardActor(cardImage, font, null);
    reserveCard = new CardActor(cardImage, font, null);
    playerLabel = new Label(gameManager.getCurrentPlayer().toString(), uiSkin);
    cardGrid = new CardActor[3][3];
    vertScores = new Label[3];
    horiScores = new Label[3];



    for (int i = 0; i < 3; i++) {
      vertScores[i] = new Label(null, uiSkin);
      horiScores[i] = new Label(null, uiSkin);
    }


    gameManager.nextTurn();


    reserveButton.addListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent event, Actor actor) {
        gameManager.reserveCard();
      }
    });

    reserveTable.add(reserveCard).padBottom(15);
    reserveTable.row();
    reserveTable.add(reserveButton);
    table.add(playerLabel).pad(15).center();
    table.row();
    // Add Vertical scores and player card slots to table.
    playerCards.add(currentCard).pad(15);
    playerCards.row();
    playerCards.add(reserveTable).pad(15);
    cardGameTable.add(vertScores[0]).pad(15);
    cardGameTable.add(vertScores[1]).pad(15);
    cardGameTable.add(vertScores[2]).pad(15);
    cardGameTable.row();


    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        CardActor card = new CardActor(cardImage, font, null);
        card.row = i;
        card.col = j;
        card.addCaptureListener(boardListener);

        cardGameTable.add(card).pad(15);
        cardGrid[i][j] = card;
      }
      cardGameTable.add(horiScores[i]).pad(15);
      cardGameTable.row();
    }
    table.add(cardGameTable);
    table.add(playerCards);
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

  public CrossCardGameManager getGameManager() {

    return gameManager;
  }

  public void clear() {
    gameManager = new CrossCardGameManager();
  }
  
  private void gameOver(){
	  game.setScreen(new GameOverScreen(game, this, gameType));
  }


}
