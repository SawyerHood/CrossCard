package com.sawyerhood.crosscard.gamelogic;

import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;

public class CrossCardPlayer {
  private CrossCard reserve;
  private CrossCard currentCard;
  private CardType side;
  private String id;

  public CrossCardPlayer(CardType side, String id) {
    this.side = side;
    reserve = null;
    currentCard = null;
    this.id = id;
  }

  /**
   * Reserve a card.
   * 
   * @return true if reserved, false otherwise
   */
  public boolean reserve() {
    if (reserve == null && currentCard != null) {
      reserve = currentCard;
      currentCard = null;
      return true;
    }
    return false;
  }

  /**
   * Reserve the card provided.
   * 
   * @param card the card to reserve
   * @return true when a card is reserved
   */
  public boolean reserve(CrossCard card) {
    if (reserve == null) {
      this.reserve = card;
      return true;
    }
    return false;
  }

  /**
   * Return the card type (vertical or horizontal).
   * 
   * @return the card type the player is playing as
   */
  public CardType getSide() {
    return side;
  }

  /**
   * Give the player a card.
   * 
   * @param card the card to give
   * @return true if card granted, false otherwise
   */
  public boolean giveCard(CrossCard card) {
    if (currentCard == null) {
      currentCard = card;
      return true;
    }
    return false;
  }

  /**
   * Pop card of the stack and return it.
   * 
   * @return the top card
   */
  public CrossCard popCard() {
    CrossCard toReturn = currentCard;
    currentCard = reserve;
    reserve = null;
    return toReturn;
  }

  /**
   * Return the reserved card.
   * 
   * @return the reserved card
   */
  public CrossCard getReserve() {
    return reserve;
  }

  /**
   * Return the current card.
   * 
   * @return the current card
   */
  public CrossCard getCurrentCard() {
    return currentCard;
  }
  
  /**
   * Set the player's current card.
   * 
   * @param currentCard the card to set as the player's current card
   */
  public void setCurrentCard(CrossCard currentCard) {
	  this.currentCard = currentCard;
  }

  /**
   * Return the player's id
   */
  public String toString() {
    return id;
  }
}
