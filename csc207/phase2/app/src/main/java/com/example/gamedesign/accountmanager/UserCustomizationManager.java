package com.example.gamedesign.accountmanager;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanagerinterface.OnSetIconListener;
import com.example.gamedesign.accountmanagerinterface.OnSetTextColorListener;
import com.example.gamedesign.accountmanagerinterface.OnSetThemeListener;

import java.io.Serializable;
import java.util.List;

/**
 * A class stored by user contains customizable settings, which are text colors, background color
 * and icon
 */
class UserCustomizationManager implements Serializable {

  /** the icon that the user chosen */
  private int icon;
  /** the theme that the user chosen */
  private int theme;
  /** temporary theme chosen by user in themeActivity */
  private int tempTheme;
  /** the textColor that the user used */
  private int textColor;

  /** constructing a new UserCustomizationManager, setting default theme and textColor to blue */
  UserCustomizationManager() {
    this.theme = R.color.colorBlue;
    this.textColor = R.style.Moon;
    this.icon = R.drawable.female_icon;
  }

  /**
   * set textColor to new textColor
   *
   * @param textColor new textColor
   */
  protected void setTextColor(int textColor, OnSetTextColorListener onSetTextColorListener) {
    this.textColor = textColor;
    onSetTextColorListener.goToSettingActivity();
  }

  /** @return get textColor for changing textColor in activities */
  int getTextColor() {
    return textColor;
  }

  /** @return get theme for changing background color in activities */
  int getTheme() {
    return theme;
  }

  /**
   * set this.icon to icon if the icon is owned by user
   *
   * @param icon the icon user wants to change to
   * @param iconBought icon owned by user
   * @param onSetIconListener userIconPresenter to pass information to iconActivity
   */
  protected void setIcon(int icon, List<Integer> iconBought, OnSetIconListener onSetIconListener) {
    if (iconBought.contains(Integer.valueOf(icon))
        | icon == R.drawable.female_icon
        | icon == R.drawable.male_icon) {
      this.icon = icon;
    } else {
      onSetIconListener.notAvailable();
    }
  }

  /** @return the icon chosen by user */
  protected int getIcon() {
    return icon;
  }

  /**
   * set the temporary theme to chosen theme
   *
   * @param theme chosen theme
   * @param background background image with same color as the theme
   * @param onSetThemeListener userThemePresenter for passing information to themeActivity
   */
  void setTempTheme(int theme, int background, OnSetThemeListener onSetThemeListener) {
    tempTheme = theme;
    onSetThemeListener.backgroundTransitionAnimation(theme);
    onSetThemeListener.changeBackground(background);
  }

  /**
   * apply the temporary theme to user's theme
   *
   * @param onSetThemeListener userThemePresenter for passing information to themeActivity
   */
  void saveTheme(OnSetThemeListener onSetThemeListener) {
    theme = tempTheme;
    onSetThemeListener.goToSetting();
  }
}
