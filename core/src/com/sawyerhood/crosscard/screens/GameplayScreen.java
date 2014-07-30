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

/**
 * The screen that contains the main gameplay.
 * 
 * @author Sawyer Hood
 *
 */
public class GameplayScreen extends BaseScreen {

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
  private TextButton continueButton;

  /**
   * Creates a new gameplay screen.
   * 
   * @param game the game
   */
  public GameplayScreen(CrossCardGame game) {
    super(game);
    initGameManager();
    initBoard(new BoardClickListener(gameManager));
    gameType = "";

  }

  /**
   * Renders the players turn and the state of the game board.
   */
  @Override
  public void render(float delta) {
    super.render(delta);

    if (gameManager.isGameOver()) {
      continueButton.setVisible(true);
      playerLabel.setText(gameManager.getWinner().toString() + " won!");
      playerCards.setVisible(false);
    }

    else {
      playerLabel.setText(gameManager.getCurrentPlayer().toString() + "'s Turn");
      turnHandle();
    }
    updateCards();
    playerLabel.setFontScale(2f);
  }

  /**
   * Initialize the game board
   * 
   * @param boardListener the board's click listener
   */
  public void initBoard(BoardClickListener boardListener) {

    Texture cardImage = ((CrossCardGame) game).getAssetManger().get("card.png", Texture.class);
    BitmapFont font = ((CrossCardGame) game).getAssetManger().get("default.fnt", BitmapFont.class);
    Table reserveTable = new Table();
    TextButton reserveButton = new TextButton("Reserve", uiSkin);
    continueButton = new TextButton("Continue", uiSkin);
    cardGameTable = new Table();
    playerCards = new Table();
    currentCard = new CardActor(cardImage, font, null);
    reserveCard = new CardActor(cardImage, font, null);
    playerLabel = new Label(gameManager.getCurrentPlayer().toString(), uiSkin);
    cardGrid = new CardActor[3][3];
    vertScores = new Label[3];
    horiScores = new Label[3];

    reserveButton.getLabel().setFontScale(fontScale);

    continueButton.getLabel().setFontScale(fontScale);
    continueButton.setVisible(false);
    continueButton.addListener(new ChangeListener() {


      @Override
      public void changed(ChangeEvent event, Actor actor) {
        gameOver();
      }

    });

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
    table.row();
    table.add(continueButton);
  }

  /**
   * Update the cards on the board and their scores.
   */
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

  /**
   * Returns the game manager.
   * 
   * @return the game manager
   */
  public CrossCardGameManager getGameManager() {
    return gameManager;
  }

  /**
   * Instantiate a new CrossCardGameManager
   */
  public void clear() {
    gameManager = new CrossCardGameManager();
  }

  /**
   * Transition to the GameOverScreen.
   */
  private void gameOver() {
    game.setScreen(new GameOverScreen(game, this, gameType));
  }

  protected void turnHandle() {
    // Sound cardDrawSound = ((CrossCardGame)
    // game).getAssetManger().getAssetFileName("card_draw.wav");
  }

  /**
   * Instantiate a new CrossCardGameManager
   */
  protected void initGameManager() {
    gameManager = new CrossCardGameManager();
  }

}
