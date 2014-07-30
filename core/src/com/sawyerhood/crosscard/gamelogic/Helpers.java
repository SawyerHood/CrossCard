package com.sawyerhood.crosscard.gamelogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/*
 * import com.google.gson.Gson; import com.google.gson.reflect.TypeToken;
 */

/**
 * 
 * @author Sawyer Hood
 * 
 * 
 */
public class Helpers {

  /**
   * This Enum is used for determining the card attribute. Depending on the values the scoring of
   * the grid varies.
   * 
   * @author Sawyer
   * 
   */
  public static Random r = new Random();
  public static Helpers.CardType[] types = {Helpers.CardType.HORIZONTAL, Helpers.CardType.CROSS,
      Helpers.CardType.DOT, Helpers.CardType.VERTICAL};

  public enum CardType {
    HORIZONTAL, VERTICAL, CROSS, DOT, FACEDOWN;

    public String toString() {
      if (this == HORIZONTAL)
        return "_";
      else if (this == VERTICAL)
        return "|";
      else if (this == CROSS)
        return "+";
      else if (this == FACEDOWN)
        return "CC";
      else
        return "*";

    }
  }

  /**
   * Enum used during creation of the GameAI object. Determines the amount of processing time used
   * during simulations.
   * 
   * @author David
   *
   */
  public enum AIDifficulty {
    EASY, MEDIUM, HARD;

    public String toString() {
      if (this == EASY)
        return "EASY";
      else if (this == MEDIUM)
        return "MEDIUM";
      else
        return "HARD";
    }

    // converts the enum members into computational time in milliseconds.
    public long toTime() {
      if (this == EASY)
        return (long) 200;
      else if (this == MEDIUM)
        return (long) 400;
      else
        return (long) 1000;
    }
  }

  /**
   * 
   * @param json A string of json data
   * @return A collection of CrossCards generated from the json.
   */
  /*
   * public static Collection<CrossCard> loadDeck(String json) { Gson gson = new Gson(); Type
   * collectionType = new TypeToken<Collection<CrossCardDeckTemplate>>() {}.getType();
   * Collection<CrossCardDeckTemplate> cards = gson.fromJson(json, collectionType); return
   * templateToCards(cards); }
   */

  /**
   * 
   * @param filename Filename of the file to be loaded.
   * @return Returns the contents of the text file as a string.
   * @throws IOException
   */
  public static String readFile(String filename) throws IOException {
    // Read the file.
    File file = new File(filename);
    FileInputStream fis = new FileInputStream(file);
    byte[] data = new byte[(int) file.length()];
    fis.read(data);
    fis.close();
    String s = new String(data, "UTF-8");
    return s;
  }

  /**
   * 
   * @param templates An array of card templates, these contain a copy of the card object and then
   *        how many should be created when instantiating the deck.
   * @return A collection of cards built from the templates.
   */
  public static Collection<CrossCard> templateToCards(Collection<CrossCardDeckTemplate> templates) {
    Collection<CrossCard> cards = new ArrayList<CrossCard>();
    for (CrossCardDeckTemplate temp : templates) {
      if (temp != null)
        for (int i = 0; i < temp.getNumberToInit(); i++) {
          CrossCard toAdd = new CrossCard(temp.getCard());
          cards.add(toAdd);
        }
    }
    return cards;
  }

  public static Collection<CrossCard> generateStandardDeck() {
    Collection<CrossCard> cards = new ArrayList<CrossCard>();
    for (int i = 1; i < 6; i++) {
      for (int j = 0; j < 2; j++) {
        cards.add(new CrossCard(CardType.HORIZONTAL, i));
        cards.add(new CrossCard(CardType.CROSS, i));
        cards.add(new CrossCard(CardType.VERTICAL, i));
      }
    }
    cards.add(new CrossCard(CardType.DOT, 1));
    cards.add(new CrossCard(CardType.DOT, 1));
    return cards;

  }

  public static List<CrossCardPlayer> generateStandardPlayers() {
    List<CrossCardPlayer> players = new ArrayList<CrossCardPlayer>();
    players.add(new CrossCardPlayer(CardType.HORIZONTAL, "Horizontal"));
    players.add(new CrossCardPlayer(CardType.VERTICAL, "Vertical"));
    return players;
  }

  public static List<CrossCardPlayer> generateAIPlayers(AIDifficulty d) {
    List<CrossCardPlayer> players = new ArrayList<CrossCardPlayer>();
    players.add(new CrossCardPlayer(CardType.VERTICAL, "Vertical"));
    players.add(new CrossCardAI(CardType.HORIZONTAL, "Horizontal", d));
    return players;
  }

  /**
   * 
   * @return Returns a random CrossCard
   */
  public static CrossCard getRandomCard() {
    int typeIndex = r.nextInt(types.length);
    int cardVal = r.nextInt(5) + 1;
    return new CrossCard(types[typeIndex], cardVal);
  }

}
