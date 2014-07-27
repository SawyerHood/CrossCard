package com.sawyerhood.crosscard.gamelogic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.sawyerhood.crosscard.CrossCardGame;

/**
 * The deck class used to store a virtual deck of cards.
 * 
 * @author Sawyer Hood
 * @author Matt Hansen
 * 
 */
public class CrossCardDeck {

  private List<CrossCard> cards;
  private Sound cardDrawSound;

  /**
   * Creates a new CrossCardDeck and shuffles it.
   * 
   * @param cards the collection of cards
   */
  public CrossCardDeck(Collection<CrossCard> cards) {
    this.cards = (List) cards;
    cardDrawSound = Gdx.audio.newSound(Gdx.files.internal("card_draw.wav"));
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
    cardDrawSound.play();
    return cards.remove(cards.size() - 1);
  }

  /**
   * Clones a deck.
   */
  public CrossCardDeck clone() {
    return new CrossCardDeck(this.cards);
  }

}
