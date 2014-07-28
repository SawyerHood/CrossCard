package com.sawyerhood.crosscard.gamelogic;

import java.util.ArrayList;
import java.util.Collections;

import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;

/**
 * This class is responsible for representing the board as well as the board state. It also does all
 * calculations related to scoring the board.
 * 
 * @author Sawyer Hood
 * 
 */
public class CrossCardBoard {

  private CrossCard[][] grid;

  /**
   * Initializes a grid with a 3x3 playing field.
   */
  public CrossCardBoard() {
    initGrid(3, 3);
  }

  public CrossCardBoard(CrossCard[][] grid) {
    this.grid = grid;
  }

  /**
   * Responsible for initializing the grid with the given number of rows and columns.
   * 
   * @param rowNum
   * @param colNum
   */
  private void initGrid(int rowNum, int colNum) {
    grid = new CrossCard[rowNum][colNum];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = null;
      }
    }

    grid[1][1] = new CrossCard(CardType.FACEDOWN, 0); // Makes the middle card facedown.
  }

  /**
   * Returns true if the given location on the board contains a card, false otherwise.
   * 
   * @param row
   * @param col
   * @return
   */
  public boolean isOccupied(int row, int col) {
    if (row >= grid.length || col >= grid[row].length)
      return true;
    return grid[row][col] != null;
  }

  /**
   * Returns true if the board is full, signaling the game is over.
   * 
   * @return
   */
  public boolean isBoardFull() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (!isOccupied(i, j))
          return false;
      }
    }
    return true;
  }

  /**
   * Returns the integer value representing the score of a given row.
   * 
   * @param row
   * @return
   */
  public int getRowValue(int row) {
    int total = 0;
    for (int i = 0; i < grid[row].length; i++) {
      if (!isOccupied(row, i)) {
        continue;
      } else if (grid[row][i].getCardType() == CardType.CROSS
          || grid[row][i].getCardType() == CardType.HORIZONTAL) {
        total += grid[row][i].getValue();
      } else if (grid[row][i].getCardType() == CardType.DOT) {
        return 0;
      }
    }
    return total;
  }

  /**
   * Returns an integer value representing the score of a given column.
   * 
   * @param col
   * @return
   */
  public int getColValue(int col) {
    int total = 0;
    for (int i = 0; i < grid.length; i++) {
      if (!isOccupied(i, col)) {
        continue;
      } else if (grid[i][col].getCardType() == CardType.CROSS
          || grid[i][col].getCardType() == CardType.VERTICAL) {
        total += grid[i][col].getValue();
      } else if (grid[i][col].getCardType() == CardType.DOT) {
        return 0;
      }
    }
    return total;
  }

  /**
   * Returns the max row value
   * 
   * @return
   */
  public int getMaxRowValue() {
    ArrayList<Integer> values = new ArrayList<Integer>();
    for (int i = 0; i < grid.length; i++) {
      values.add(getRowValue(i));
    }
    return Collections.max(values);
  }

  /**
   * Returns the max column value
   * 
   * @return
   */
  public int getMaxColValue() {
    ArrayList<Integer> values = new ArrayList<Integer>();
    for (int i = 0; i < grid[0].length; i++) {
      values.add(getColValue(i));
    }
    return Collections.max(values);
  }

  public ArrayList<Integer> getSortedColValues() {
    ArrayList<Integer> values = new ArrayList<Integer>();
    for (int i = 0; i < grid[0].length; i++) {
      values.add(getColValue(i));
    }
    Collections.sort(values);
    return values;
  }

  public ArrayList<Integer> getSortedRowValues() {
    ArrayList<Integer> values = new ArrayList<Integer>();
    for (int i = 0; i < grid.length; i++) {
      values.add(getRowValue(i));
    }
    Collections.sort(values);
    return values;
  }



  /**
   * Used only for the text version of the game.
   */
  public String toString() {
    String toReturn = "";
    for (int i = 0; i < grid[0].length; i++) {
      toReturn += String.format("%20d", getColValue(i));
    }
    toReturn += "\n";

    for (int i = 0; i < grid[0].length; i++) {
      toReturn += "------------------------";
    }

    toReturn += "\n";

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == null)
          toReturn += String.format("|%20s  |", "X");
        else
          toReturn += String.format("|%20s  |", grid[i][j].getCardType());
      }

      toReturn += "\n";

      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == null)
          toReturn += String.format("|%20s  |", "X");
        else
          toReturn += String.format("|%20d  |", grid[i][j].getValue());
      }
      toReturn += String.format("%5d", getRowValue(i));
      toReturn += "\n";

      for (int j = 0; j < grid[0].length; j++) {
        toReturn += "------------------------";
      }

      toReturn += "\n";

    }


    return toReturn;
  }

  /**
   * Tries to place a card at the specified location. Returns true if sucessful and flase otherwise.
   * 
   * @param row
   * @param col
   * @param card
   * @return
   */
  public boolean placeCard(int row, int col, CrossCard card) {
    if (isOccupied(row, col))
      return false;
    grid[row][col] = card;
    return true;
  }

  public CrossCard getCard(int row, int col) {
    return grid[row][col];
  }

  public CrossCardBoard clone() {
    CrossCard[][] cloneGrid = new CrossCard[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (this.isOccupied(i, j)) {
          CardType cloneType = this.grid[i][j].getCardType();
          int cloneValue = this.grid[i][j].getValue();
          cloneGrid[i][j] = new CrossCard(cloneType, cloneValue);
        } else
          cloneGrid[i][j] = null;
      }
    }
    return new CrossCardBoard(cloneGrid);
  }

  public CardType getWinner(CrossCardDeck deck) {
    if (isBoardFull()) {
      if (grid[1][1].getCardType() == CardType.FACEDOWN)
        grid[1][1] = deck.draw();
      ArrayList<Integer> rowVals = getSortedRowValues();
      ArrayList<Integer> colVals = getSortedColValues();
      for (int i = rowVals.size() - 1; i >= 0; i--) {
        if (rowVals.get(i) > colVals.get(i))
          return CardType.HORIZONTAL;
        if (colVals.get(i) > rowVals.get(i))
          return CardType.VERTICAL;

      }
    }
    return null;

  }
}
