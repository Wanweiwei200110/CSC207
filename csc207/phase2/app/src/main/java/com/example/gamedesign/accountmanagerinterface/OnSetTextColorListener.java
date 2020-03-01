package com.example.gamedesign.accountmanagerinterface;

/**
 * An interface handling actions of user changing their textcolor in setting implements by
 * UserTextColorPresenter, TextColorActivity
 */
public interface OnSetTextColorListener {

  /** go to setting activity */
  void goToSettingActivity();

  /**
   * set user's text color
   *
   * @param textColor user's text color
   */
  void setTextColor(int textColor);
}
