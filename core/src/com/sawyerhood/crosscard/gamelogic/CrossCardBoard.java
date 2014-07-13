package com.sawyerhood.crosscard.gamelogic;

import java.util.ArrayList;
import java.util.Collections;

import com.sawyerhood.crosscard.gamelogic.Helpers.CardType;


public class CrossCardBoard {

  private CrossCard[][] grid;

  public CrossCardBoard() {
    initGrid(3, 3);
  }

  private void initGrid(int rowNum, int colNum) {
    grid = new CrossCard[rowNum][colNum];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = null;
      }
    }
  }

  public boolean isOccupied(int row, int col) {
    if (row >= grid.length || col >= grid[row].length)
      return true;
    return grid[row][col] != null;
  }

  public boolean isBoardFull() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (!isOccupied(i, j))
          return false;
      }
    }
    return true;
  }

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

  public int getMaxRowValue() {
    ArrayList<Integer> values = new ArrayList<Integer>();
    for (int i = 0; i < grid.length; i++) {
      values.add(getRowValue(i));
    }
    return Collections.max(values);
  }

  public int getMaxColValue() {
    ArrayList<Integer> values = new ArrayList<Integer>();
    for (int i = 0; i < grid[0].length; i++) {
      values.add(getColValue(i));
    }
    return Collections.max(values);
  }

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

  public boolean placeCard(int row, int col, CrossCard card) {
    if (isOccupied(row, col))
      return false;
    grid[row][col] = card;
    return true;
  }
}
