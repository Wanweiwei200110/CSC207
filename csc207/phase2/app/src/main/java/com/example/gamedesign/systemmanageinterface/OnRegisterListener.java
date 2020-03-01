package com.example.gamedesign.systemmanageinterface;

/** OnRegisterListener interface for register system. */
public interface OnRegisterListener extends NotValidInput {

  /**
   * Checking validity of usernameInput and passwordInput for registration.
   *
   * @param usernameInput username input for registration
   * @param passwordInput password input for registration
   */
  void validateRegistration(String usernameInput, String passwordInput);

  /** Going to login activity after registration. */
  void goToLoginActivity();
}
