package com.sawyerhood.crosscard.gamelogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.sawyerhood.crosscard.CrossCardGame;
import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;

/**
 * The deck class used to store a virtual deck of cards.
 * 
 * @author Sawyer Hood
 * @author Matt Hansen
 * 
 */
public class CrossCardDeck {

  private List<CrossCard> cards;

  /**
   * Creates a new CrossCardDeck and shuffles it.
   * 
   * @param cards the collection of cards
   */
  public CrossCardDeck(Collection<CrossCard> cards) {
    this.cards = (List<CrossCard>) cards;
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
   * @return the next card in the deck
   */
  public CrossCard draw() {
    if (cards.size() == 0) {
      return null;
    }
    return cards.remove(cards.size() - 1);
  }

  /**
   * Clones a deck.
   */
  public CrossCardDeck clone() {
	  List<CrossCard> cloneDeck = new ArrayList<CrossCard>(this.cards.size());
	  for (int i=0; i < this.cards.size(); i++) {
		  CardType cloneType = this.cards.get(i).getCardType();
		  int cloneValue = this.cards.get(i).getValue();
		  cloneDeck.add(new CrossCard(cloneType, cloneValue));
	  }
	  return new CrossCardDeck(cloneDeck);
  }

}
