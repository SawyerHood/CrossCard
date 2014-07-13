package com.sawyerhood.crosscard.gamelogic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class CrossCardDeck {

  private List<CrossCard> cards;

  public CrossCardDeck(Collection<CrossCard> cards) {
    this.cards = (List) cards;
    shuffle();
  }

  public void shuffle() {
    Collections.shuffle((List<?>) cards);
  }

  public CrossCard draw() {
    if (cards.size() == 0)
      return null;
    return cards.remove(cards.size() - 1);
  }

  public CrossCardDeck clone() {
    return null;

  }

}
