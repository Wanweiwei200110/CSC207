package com.example.gamedesign.accountmanagerinterface;

/**
 * An interface handling actions of user changing their theme in setting implements by
 * UserThemePresenter, ThemeActivity
 */
public interface OnSetThemeListener {

  /**
   * set temp theme after user click theme
   *
   * @param background user's background
   * @param theme user's theme
   */
  void setTempTheme(int theme, int background);

  /**
   * reset user's background
   *
   * @param backgroundId background's id
   */
  void changeBackground(int backgroundId);

  /**
   * show background transition animation
   *
   * @param color a color
   */
  void backgroundTransitionAnimation(int color);

  /** save the theme for the user */
  void saveTheme();

  /** go to setting activity */
  void goToSetting();
}
