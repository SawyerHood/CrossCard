package com.sawyerhood.crosscard.gamelogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

  public enum CardType {
    HORIZONTAL, VERTICAL, CROSS, DOT;

    public String toString() {
      if (this == HORIZONTAL)
        return "_";
      else if (this == VERTICAL)
        return "|";
      else if (this == CROSS)
        return "+";
      else
        return "*";

    }
  }



  /**
   * 
   * @param json A string of json data
   * @return A collection of CrossCards generated from the json.
   */
  public static Collection<CrossCard> loadDeck(String json) {
    Gson gson = new Gson();
    Type collectionType = new TypeToken<Collection<CrossCardDeckTemplate>>() {}.getType();
    Collection<CrossCardDeckTemplate> cards = gson.fromJson(json, collectionType);
    return templateToCards(cards);
  }

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



}
