package com.example.gamedesign.hangman;

import java.io.Serializable;
import java.util.HashSet;

/** This class contains all the judging logic part of the game */
public class JudgingWords implements Serializable {

  /** This is the word need to be guessed. */
  private String secretWord;

  /** create easy words to be guessed */
  private EasyWords easyWords;

  /** create medium words to be guessed */
  private MediumWords mediumWords;

  /** create hard words to be guessed */
  private HardWords hardWords;

  /** The characters the user has been guessed */
  private HashSet<Character> guessedCharacters;

  /** All the characters the secret word contains */
  private HashSet<Character> correctCharacters;

  /** Constructing a new judging words. */
  JudgingWords() {
    easyWords = new EasyWords();
    mediumWords = new MediumWords();
    hardWords = new HardWords();
    guessedCharacters = new HashSet<>();
    correctCharacters = new HashSet<>();
  }

  /**
   * get the length of the secret word
   *
   * @return length of the secret word
   */
  private int wordLength() {
    return secretWord.length();
  }

  /**
   * get the secret word
   *
   * @return the secret word
   */
  String getSecret() {
    return secretWord;
  }

  /**
   * choosing the secret word according to the level selected
   *
   * @param level the level that the user selected
   */
  public void setLevel(int level) {
    switch (level) {
      case 1:
        secretWord = easyWords.chooseWord();

        for (int i = 0; i < wordLength(); i++) {
          correctCharacters.add(secretWord.charAt(i));
        }

        break;
      case 2:
        secretWord = mediumWords.chooseWord();
        for (int i = 0; i < wordLength(); i++) {
          correctCharacters.add(secretWord.charAt(i));
        }
        break;
      case 3:
        secretWord = hardWords.chooseWord();
        for (int i = 0; i < wordLength(); i++) {
          correctCharacters.add(secretWord.charAt(i));
        }
        break;
    }
  }

  /**
   * Check if the letter user guessed is right
   *
   * @param c the letter that the user guessed
   * @return if the user is right
   */
  boolean ifCorrectCharacter(char c) {
    guessedCharacters.add(c);
    return secretWord.contains(String.valueOf(c));
  }

  /**
   * Check if the letter user has guessed before
   *
   * @param c the letter that the user guessed
   * @return if the user guessed it before
   */
  boolean ifHaveGuessed(char c) {
    return guessedCharacters.contains(c);
  }

  /**
   * Check if the user guesses the whole word
   *
   * @return if the user wins
   */
  boolean ifWin() {
    for (Character c : correctCharacters) {
      if (!guessedCharacters.contains(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Display the hint
   *
   * @return the hint
   */
  String setInitialHint() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < wordLength(); i++) {
      s.append("_ ");
    }
    return s.toString();
  }

  /**
   * Check if the secret word contains this letter
   *
   * @param c the letter to be checked
   * @return if the letter is right
   */
  private boolean ifCorrectContains(char c) {
    return guessedCharacters.contains(c);
  }

  /**
   * get the letter at position i of the secret word
   *
   * @param index which position is needed
   * @return the letter on that position
   */
  private char letterAtIndex(int index) {
    return secretWord.charAt(index);
  }

  /**
   * Display new hints
   *
   * @return the hint to be displayed
   */
  String updateHint() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < wordLength(); i++) {
      if (ifCorrectContains(letterAtIndex(i))) {
        s.append(letterAtIndex(i));
        s.append(" ");
      } else {
        s.append("_ ");
      }
    }
    return s.toString();
  }

  /** Force to guessed out the whole secret word */
  void correctAtOnce() {
    guessedCharacters.addAll(correctCharacters);
  }
}
