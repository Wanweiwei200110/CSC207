package com.example.gamedesign.accountmanager;

import com.example.gamedesign.accountmanagerinterface.OnSetTextColorListener;

/** Presenter class used to separate user and textColorActivity */
public class UserTextColorPresenter implements OnSetTextColorListener {

  /** textColorActivity which implements onSetTextColorListener */
  private OnSetTextColorListener onSetTextColorListener;

  /** current logged in user */
  private User user;

  /**
   * constructing a new UserTextColorPresenter
   *
   * @param onSetTextColorListener a OnSetTextColorListener
   * @param user a user
   */
  public UserTextColorPresenter(OnSetTextColorListener onSetTextColorListener, User user) {
    this.onSetTextColorListener = onSetTextColorListener;
    this.user = user;
  }

  /** navigates to settingActivity */
  @Override
  public void goToSettingActivity() {
    onSetTextColorListener.goToSettingActivity();
  }

  /**
   * change text color in user
   *
   * @param textColor textColor chosen by user
   */
  @Override
  public void setTextColor(int textColor) {
    user.setTextColor(textColor, this);
  }
}
