package com.example.gamedesign.hangman;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;

/** This class contains all the medium-level words to be guessed. */
public class MediumWords implements Serializable {

  /** Create a new empty hashset. */
  private HashSet<String> mediumWords = new HashSet<>();

  /** The constructor add all the medium-level words into this hashset. */
  MediumWords() {
    mediumWords.add("almond");
    mediumWords.add("apple");
    mediumWords.add("avocado");
    mediumWords.add("banana");
    mediumWords.add("cherry");
    mediumWords.add("coconut");
    mediumWords.add("grape");
    mediumWords.add("lemon");
    mediumWords.add("mango");
    mediumWords.add("orange");
    mediumWords.add("peach");
    mediumWords.add("peanut");
    mediumWords.add("pear");
  }

  /**
   * get the size of this hashset
   *
   * @return the size of this set
   */
  public int size() {
    return mediumWords.size();
  }

  /**
   * choose a secret word from this set
   *
   * @return the chosen word i.e. the secret word
   */
  String chooseWord() {
    int size = mediumWords.size();
    int item =
        new Random()
            .nextInt(
                size); // In real life, the Random object should be rather more shared than this
    int i = 0;
    for (String obj : mediumWords) {
      if (i == item) return obj;
      i++;
    }
    return "orange";
  }
}
