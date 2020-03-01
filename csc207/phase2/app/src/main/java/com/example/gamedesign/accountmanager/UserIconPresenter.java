package com.example.gamedesign.accountmanager;

import com.example.gamedesign.accountmanagerinterface.OnSetIconListener;

/** Used to separate IconActivity apart from user */
public class UserIconPresenter implements OnSetIconListener {

  /** a OnSetIconListener */
  private OnSetIconListener onSetIconListener;

  /** a user */
  private User user;

  /**
   * constructing a new UserIconPresenter
   *
   * @param onSetIconListener a OnSetIconListener
   * @param user a user
   */
  public UserIconPresenter(OnSetIconListener onSetIconListener, User user) {
    this.onSetIconListener = onSetIconListener;
    this.user = user;
  }

  /**
   * get called when user clicked on one of the icon image in iconActivity
   *
   * @param icon the icon user want to change to
   */
  @Override
  public void setIcon(int icon) {
    user.setIcon(icon, this);
  }

  /** send message back to iconActivity saying the icon chosen by user is not available */
  @Override
  public void notAvailable() {
    onSetIconListener.notAvailable();
  }
}
