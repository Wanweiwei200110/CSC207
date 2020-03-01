package com.example.gamedesign.hangman;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;

/** This class contains all the hard words to be guessed. */
public class HardWords implements Serializable {

  /** Create a new empty hashset. */
  private HashSet<String> hardWords = new HashSet<>();

  /** The constructor all the hard-level words into this hashset. */
  HardWords() {
    hardWords.add("watermelon");
    hardWords.add("blueberry");
    hardWords.add("chestnut");
    hardWords.add("grapefruit");
    hardWords.add("kiwifruit");
    hardWords.add("raspberry");
    hardWords.add("strawberry");
    hardWords.add("sugercane");
    hardWords.add("pineapple");
    hardWords.add("bilberry");
  }

  /**
   * get the size of this hashset
   *
   * @return the size of this set
   */
  public int size() {
    return hardWords.size();
  }

  /**
   * choose a secret word from this set
   *
   * @return the chosen word i.e. the secret word
   */
  String chooseWord() {
    int size = hardWords.size();
    int item =
        new Random()
            .nextInt(
                size); // In real life, the Random object should be rather more shared than this
    int i = 0;
    for (String obj : hardWords) {
      if (i == item) return obj;
      i++;
    }
    return "watermelon";
  }
}
