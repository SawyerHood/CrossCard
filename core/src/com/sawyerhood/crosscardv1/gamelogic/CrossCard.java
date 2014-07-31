package com.sawyerhood.crosscardv1.gamelogic;

import com.sawyerhood.crosscardv1.gamelogic.Helpers.CardType;

/**
 * This class is the primary data structure for storing the value of a card.
 * 
 * @author Sawyer Hood
 * 
 */
public class CrossCard {

  private CardType cardType;
  private int value;

  /**
   * The default empty constructor. Creates a Cross Card with a value of 1.
   */
  public CrossCard() {
    this(CardType.CROSS, 1);
  }

  /**
   * 
   * @param type A Cardtype enum that determines the behavior of the card.
   * @param value The value of the card that is used to determine scoring.
   */
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

  /**
   * 
   * @return Returns the CardType of the card.
   */
  public CardType getCardType() {
    return cardType;
  }

  /**
   * 
   * @param cardType Sets the CardType of the card.
   */
  public void setCardType(CardType cardType) {
    this.cardType = cardType;
  }

  /**
   * 
   * @return Returns the value of the card.
   */
  public int getValue() {
    return value;
  }

  /**
   * 
   * @param value Sets the value of the card.
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Used primarily for the text version of the game.
   */
  public String toString() {
    return "Type: " + this.cardType + ", Value: " + this.value;
  }

}
