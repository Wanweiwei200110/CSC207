package com.example.gamedesign.accountmanager;

import com.example.gamedesign.accountmanagerinterface.OnSetThemeListener;

/** Presenter class used to separate user and themeActivity */
public class UserThemePresenter implements OnSetThemeListener {

  /** themeActivity which implemntes onSetThmeListener */
  private OnSetThemeListener onSetThemeListener;

  /** current logged in user */
  private User user;

  /**
   * constructing a new UserThemePresenter
   *
   * @param onSetThemeListener a OnSetThemeListener
   * @param user a user
   */
  public UserThemePresenter(OnSetThemeListener onSetThemeListener, User user) {
    this.onSetThemeListener = onSetThemeListener;
    this.user = user;
  }

  /**
   * pass information to user class
   *
   * @param theme theme chosen by user
   * @param background corresponding background to the theme
   */
  @Override
  public void setTempTheme(int theme, int background) {
    user.setTempTheme(theme, background, this);
  }

  /**
   * change background in user class
   *
   * @param backgroundId indicating which background change to
   */
  @Override
  public void changeBackground(int backgroundId) {
    onSetThemeListener.changeBackground(backgroundId);
  }

  /**
   * do an animation in themeActivity
   *
   * @param color corresponding to theme
   */
  @Override
  public void backgroundTransitionAnimation(int color) {
    onSetThemeListener.backgroundTransitionAnimation(color);
  }

  /** save the change of theme */
  @Override
  public void saveTheme() {
    user.saveTheme(this);
  }

  /** navigate to setting activity */
  @Override
  public void goToSetting() {
    onSetThemeListener.goToSetting();
  }
}
