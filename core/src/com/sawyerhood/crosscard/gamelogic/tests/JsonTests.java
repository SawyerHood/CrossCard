package com.sawyerhood.crosscard.gamelogic.tests;

import java.io.IOException;
import java.util.Collection;

import com.sawyerhood.crosscard.gamelogic.CrossCard;
import com.sawyerhood.crosscard.gamelogic.CrossCardBoard;
import com.sawyerhood.crosscard.gamelogic.CrossCardDeck;
import com.sawyerhood.crosscard.gamelogic.Helpers;

public class JsonTests {

 /* public static void testJsonLoad() {
    try {
      String json = Helpers.readFile("cards.json");
      Collection<CrossCard> cards = Helpers.loadDeck(json);
      for (CrossCard card : cards) {
        System.out.println(card);
      }
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  public static void testGrid() {
    try {
      String json = Helpers.readFile("cards.json");
      Collection<CrossCard> cards = Helpers.loadDeck(json);
      CrossCardDeck deck = new CrossCardDeck(cards);
      CrossCardBoard grid = new CrossCardBoard();
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          grid.placeCard(i, j, deck.draw());
        }
      }
      System.out.println(grid);
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    testGrid();
  }*/

}
