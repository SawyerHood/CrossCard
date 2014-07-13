package com.sawyerhood.crosscard.gamelogic;

import com.sawyerhood.crosscard.gamelogic.Helpers;
import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;

/**
 * 
 * @author sawyer
 * 
 */
public class CrossCard {

  private CardType cardType;
  private int value;


  public CrossCard() {
    this(CardType.CROSS, 1);
  }

  public CrossCard(CardType type, int value) {
    this.setCardType(type);
    this.setValue(value);
  }

  /**
   * A typical copy constructor
   * 
   * @param other another card instance
   */
  public CrossCard(CrossCard other) {
    this.cardType = other.cardType;
    this.value = other.value;
  }

  public CardType getCardType() {
    return cardType;
  }

  public void setCardType(CardType cardType) {
    this.cardType = cardType;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String toString() {
    return "Type: " + this.cardType + ", Value: " + this.value;
  }

}
