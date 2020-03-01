package com.example.gamedesign.systemmanageinterface;

/** OnLoginListener interface for login system. */
public interface OnLoginListener extends NotValidInput {

  /** initialize remember me on login page */
  void initRememberMe();

  /**
   * showing remember me on login page
   *
   * @param username username of user
   * @param password password of user
   */
  void showRememberMe(String username, String password);

  /**
   * checking validity of passwordInput for the usernameInput
   *
   * @param usernameInput username input for login
   * @param passwordInput password input for login
   * @param rememberMe boolean for whether remember me is ticked
   */
  void validatePassword(String usernameInput, String passwordInput, boolean rememberMe);

  /** going to Menu activity after login. */
  void goToMenuActivity();
}
