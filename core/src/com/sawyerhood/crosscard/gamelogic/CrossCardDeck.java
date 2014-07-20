package com.sawyerhood.crosscard.gamelogic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The deck class used to store a virtual deck of cards.
 * 
 * @author Sawyer Hood
 * 
 */
public class CrossCardDeck {

  private List<CrossCard> cards;

  /**
   * Creates a new CrossCardDeck and shuffles it.
   * 
   * @param cards
   */
  public CrossCardDeck(Collection<CrossCard> cards) {
    this.cards = (List) cards;
    shuffle();
  }

  /**
   * Shuffles the deck.
   */
  public void shuffle() {
    Collections.shuffle((List<?>) cards);
  }

  /**
   * Draws a card.
   * 
   * @return
   */
  public CrossCard draw() {
    if (cards.size() == 0)
      return null;
    return cards.remove(cards.size() - 1);
  }

  /**
   * Clones a deck.
   */
  public CrossCardDeck clone() {
    return new CrossCardDeck(this.cards);
  }

}
