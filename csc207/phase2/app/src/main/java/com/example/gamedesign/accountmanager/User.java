package com.example.gamedesign.accountmanager;

import com.example.gamedesign.accountmanagerinterface.OnBuyListener;
import com.example.gamedesign.accountmanagerinterface.OnSetIconListener;
import com.example.gamedesign.accountmanagerinterface.OnSetTextColorListener;
import com.example.gamedesign.accountmanagerinterface.OnSetThemeListener;
import com.example.gamedesign.donttouchwhitetiles.DontTouchWhiteTilesController;
import com.example.gamedesign.hangman.HangmanController;
import com.example.gamedesign.jumpingball.JumpingBallController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * a user who registered containing all the customization settings, game played by user and user
 * statistics
 */
public class User implements Serializable {

  /** score of the user */
  public int[] score;
  /** username of the user */
  private String username;
  /** password of the user */
  private String password;
  /** used for managing game controllers */
  private UserGameControllerManager userGameControllerManager;

  /** used for managing customizable settings chosen by user */
  private UserCustomizationManager userCustomizationManager;

  /** used for managing store */
  private UserStoreManager userStoreManager;

  /**
   * Constructing a user
   *
   * @param username username of the user
   * @param password password of the user
   */
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.score = new int[3];
    userGameControllerManager = new UserGameControllerManager();
    userCustomizationManager = new UserCustomizationManager();
    userStoreManager = new UserStoreManager(new ArrayList<>(), new ArrayList<>());
  }

  /** Reset hangman game controller */
  public void resetHangman(int level) {
    userGameControllerManager.resetHangman(level);
  }

  /** Reset jumping ball controller */
  public void resetJumpingBall(int level) {
    userGameControllerManager.resetJumpingBall(level);
  }

  /** Reset don't Touch White Tiles controller */
  public void resetBlock(int level) {
    userGameControllerManager.resetBlock(level);
  }

  /** @return * get HangmanController of the user */
  public HangmanController getHangmanController() {
    return userGameControllerManager.getHangmanController();
  }

  /** @return * get Don't touch white tiles Controller of the user */
  public DontTouchWhiteTilesController getDontTouchWhiteTilesController() {
    return userGameControllerManager.getDontTouchWhiteTilesController();
  }

  /** @return get JumpingBall Controller of the user */
  public JumpingBallController getJumpingBallController() {
    return userGameControllerManager.getJumpingBallController();
  }

  /** get password of the user */
  public String getPassword() {
    return password;
  }

  /** get username of the user */
  public String getUsername() {
    return username;
  }

  /**
   * check password of the user if the password is correct, return true, else, return false.
   *
   * @param password password for the user
   */
  public boolean checkPassword(String password) {
    if (password != null) {
      return this.password.equals(password);
    }
    return false;
  }

  /** get user's gold */
  public int getGold() {
    return userStoreManager.getGold();
  }

  /**
   * set the user's gold
   *
   * @param gold gold number of the user
   */
  public void setGold(int gold) {
    userStoreManager.setGold(gold);
  }

  /** get the number of magic item owned by the user */
  public int getResurrectionKey() {
    return userStoreManager.getResurrectionKey();
  }

  /**
   * set the user's magic item
   *
   * @param num number of magic item for the user
   */
  public void setResurrectionKey(int num) {
    userStoreManager.setResurrectionKey(num);
  }

  /** get user's theme */
  public int getTheme() {
    return userCustomizationManager.getTheme();
  }

  /** set user's icon */
  protected void setIcon(int icon, OnSetIconListener onSetIconListener) {
    userCustomizationManager.setIcon(icon, userStoreManager.getOwnedIcon(), onSetIconListener);
  }

  /** get user's icon */
  public int getIcon() {
    return userCustomizationManager.getIcon();
  }

  /**
   * set user's textColor
   *
   * @param textColor text color
   * @param onSetTextColorListener a OnSetTextColorListener
   */
  protected void setTextColor(int textColor, OnSetTextColorListener onSetTextColorListener) {
    userCustomizationManager.setTextColor(textColor, onSetTextColorListener);
  }

  /** get user's textColor */
  public int getTextColor() {
    return userCustomizationManager.getTextColor();
  }

  /** buy item on store page */
  void buy(OnBuyListener onBuyListener, int cost, List<Integer> cart) {
    userStoreManager.buy(onBuyListener, cost, cart);
  }

  /**
   * check bought icon
   *
   * @param onBuyListener a OnBuyListener
   */
  void checkBoughtIcon(OnBuyListener onBuyListener) {
    userStoreManager.checkBoughtIcon(onBuyListener);
  }

  /**
   * set temp theme
   *
   * @param theme user's theme
   * @param background user's background
   * @param onSetThemeListener a OnSetThemeListener
   */
  void setTempTheme(int theme, int background, OnSetThemeListener onSetThemeListener) {
    userCustomizationManager.setTempTheme(theme, background, onSetThemeListener);
  }

  /**
   * save user's theme
   *
   * @param onSetThemeListener a OnSetThemeListener
   */
  void saveTheme(OnSetThemeListener onSetThemeListener) {
    userCustomizationManager.saveTheme(onSetThemeListener);
  }
}
