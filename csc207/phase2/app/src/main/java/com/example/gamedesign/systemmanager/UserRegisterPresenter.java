package com.example.gamedesign.systemmanager;

import com.example.gamedesign.systemmanageinterface.OnRegisterListener;

/** a user register presenter class */
public class UserRegisterPresenter implements OnRegisterListener {

  /** a OnRegisterListener */
  private OnRegisterListener onRegisterListener;

  /** a UserValidater */
  private UserValidater userValidater;

  /**
   * initialize UserRegisterPresenter
   *
   * @param onRegisterListener a OnRegisterListener
   * @param userValidater a UserValidater
   */
  public UserRegisterPresenter(OnRegisterListener onRegisterListener, UserValidater userValidater) {
    this.onRegisterListener = onRegisterListener;
    this.userValidater = userValidater;
  }

  /**
   * checking validity of usernameInput and passwordInput for registration
   *
   * @param usernameInput username input for registration
   * @param passwordInput password input for registration
   */
  @Override
  public void validateRegistration(String usernameInput, String passwordInput) {
    userValidater.validateRegistration(usernameInput, passwordInput, onRegisterListener);
  }

  /** Going to login activity after registration. */
  @Override
  public void goToLoginActivity() {
    onRegisterListener.goToLoginActivity();
  }

  /**
   * checking the input
   *
   * @param validateResult a string of validate result
   */
  @Override
  public void notValidInput(String validateResult) {
    onRegisterListener.notValidInput(validateResult);
  }
}
