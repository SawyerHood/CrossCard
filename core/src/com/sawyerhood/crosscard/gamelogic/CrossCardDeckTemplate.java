package com.sawyerhood.crosscard.gamelogic;

/**
 * Used to generate a set of cards.
 * 
 * @author sawyer
 * 
 */
public class CrossCardDeckTemplate {
  private CrossCard card;
  private int numberToInit;

  public CrossCardDeckTemplate(CrossCard card, int numberToInit) {
    this.card = card;
    this.numberToInit = numberToInit;
  }

  public CrossCard getCard() {
    return card;
  }

  public void setCard(CrossCard card) {
    this.card = card;
  }

  public int getNumberToInit() {
    return numberToInit;
  }

  public void setNumberToInit(int numberToInit) {
    this.numberToInit = numberToInit;
  }
}
