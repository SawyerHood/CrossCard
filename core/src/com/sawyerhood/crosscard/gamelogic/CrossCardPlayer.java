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

  public boolean reserve() {
    if (reserve == null && currentCard != null) {
      reserve = currentCard;
      currentCard = null;
      return true;
    }
    return false;
  }
  
  //used for the AI
  public boolean reserve(CrossCard card) {
	  this.reserve = card;
	  return true;
  }

  public CardType getSide() {
    return side;
  }

  public boolean giveCard(CrossCard card) {
    if (currentCard == null) {
      currentCard = card;
      return true;
    }
    return false;

  }

  public CrossCard popCard() {
    CrossCard toReturn = currentCard;
    currentCard = reserve;
    reserve = null;
    return toReturn;
  }

  public CrossCard getReserve() {
    return reserve;
  }

  public CrossCard getCurrentCard() {
    return currentCard;
  }
  public void setCurrentCard(CrossCard currentCard) {
	  this.currentCard = currentCard;
  }
  public String toString() {
    return id;
  }


}
