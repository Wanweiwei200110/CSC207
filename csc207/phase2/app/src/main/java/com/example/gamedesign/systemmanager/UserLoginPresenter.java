package com.example.gamedesign.systemmanager;

import com.example.gamedesign.systemmanageinterface.OnLoginListener;

/** a user login presenter class */
public class UserLoginPresenter implements OnLoginListener {

  /** a OnLoginListener */
  private OnLoginListener onLoginListener;

  /** a userValidater */
  private UserValidater userValidater;

  /**
   * initialize UserLoginPresenter.
   *
   * @param onLoginListener a OnLoginListener
   * @param userValidater a userValidater
   */
  public UserLoginPresenter(OnLoginListener onLoginListener, UserValidater userValidater) {
    this.onLoginListener = onLoginListener;
    this.userValidater = userValidater;
  }

  /** initialize remember me on login page */
  @Override
  public void initRememberMe() {
    userValidater.initRememberMe(this);
  }

  /**
   * showing remember me on login page
   *
   * @param username username of user
   * @param password password of user
   */
  @Override
  public void showRememberMe(String username, String password) {
    onLoginListener.showRememberMe(username, password);
  }

  /**
   * checking validity of passwordInput for the usernameInput
   *
   * @param usernameInput username input for login
   * @param passwordInput password input for login
   * @param rememberMe boolean for whether remember me is ticked
   */
  @Override
  public void validatePassword(String usernameInput, String passwordInput, boolean rememberMe) {
    userValidater.validatePassword(usernameInput, passwordInput, rememberMe, this);
  }

  /** going to Menu activity after login. */
  @Override
  public void goToMenuActivity() {
    onLoginListener.goToMenuActivity();
  }

  /**
   * checking the input
   * @param validateResult a string of validate result
   */
  @Override
  public void notValidInput(String validateResult) {
    onLoginListener.notValidInput(validateResult);
  }
}
