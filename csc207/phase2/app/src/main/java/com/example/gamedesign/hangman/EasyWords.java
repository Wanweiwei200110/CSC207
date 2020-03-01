package com.example.gamedesign.hangman;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;

/** The class contains all the easy words */
public class EasyWords implements Serializable {

  /** Create a new empty hashSet. */
  private HashSet<String> easyWords = new HashSet<>();

  /** The constructor add all the easy-level words into this hashSet. */
  EasyWords() {
    easyWords.add("pear");
    easyWords.add("haw");
    easyWords.add("fig");
    easyWords.add("peas");
    easyWords.add("bean");
    easyWords.add("beet");
    easyWords.add("corn");
    easyWords.add("cole");
    easyWords.add("crab");
    easyWords.add("lime");
    easyWords.add("taro");
  }

  /**
   * get the size of this hashset
   *
   * @return the size of this set
   */
  public int size() {
    return easyWords.size();
  }

  /**
   * choose a secret word from this set
   *
   * @return the chosen word i.e. the secret word
   */
  String chooseWord() {
    int size = easyWords.size();
    int item =
        new Random()
            .nextInt(
                size); // In real life, the Random object should be rather more shared than this
    int i = 0;
    for (String obj : easyWords) {
      if (i == item) return obj;
      i++;
    }
    return "pear";
  }
}
