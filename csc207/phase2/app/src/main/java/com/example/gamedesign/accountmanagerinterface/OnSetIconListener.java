package com.example.gamedesign.accountmanagerinterface;

/**
 * An interface handling actions of user changing their icon in setting implements by
 * UserIconPresenter, IconActivity
 */
public interface OnSetIconListener {

  /**
   * set icon
   *
   * @param icon icon of user
   */
  void setIcon(int icon);

  /** show the icon is not available */
  void notAvailable();
}
