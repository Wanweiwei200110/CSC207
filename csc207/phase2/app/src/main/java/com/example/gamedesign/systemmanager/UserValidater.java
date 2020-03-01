package com.example.gamedesign.systemmanager;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.systemmanageinterface.OnLoginListener;
import com.example.gamedesign.systemmanageinterface.OnRegisterListener;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

/** a UserValidater class */
public class UserValidater {

  /** a HashMap with all user data */
  private HashMap<String, User> userHashMap;

  /** a UserManager */
  private UserManager userManager;

  /** a FileSaverLoader */
  private FileSaverLoader fileSaverLoader;

  /**
   * a constructor for UserValidater
   *
   * @param fileSaverLoader a fileSaverLoader
   */
  public UserValidater(FileSaverLoader fileSaverLoader) {
    this.userManager = UserManager.getInstance();
    userHashMap = userManager.getUserHashMap();
    this.fileSaverLoader = fileSaverLoader;
  }

  /**
   * check the correctness of the username and password in data
   *
   * @param username a username
   * @param password a password
   */
  private User verification(String username, String password) {
    if (userHashMap != null) {
      if (!userHashMap.containsKey(username)) {
        throw new InputMismatchException();
      }
      if (!userHashMap.get(username).checkPassword(password)) {
        throw new InputMismatchException();
      }
      return userHashMap.get(username);
    }
    return null;
  }

  /**
   * check validity of username and password
   *
   * @param usernameInput username of a user
   * @param passwordInput password of a user
   * @param rememberMe boolean of rememberMe
   * @param onLoginListener an OnLoginListener
   */
  void validatePassword(
      String usernameInput,
      String passwordInput,
      boolean rememberMe,
      OnLoginListener onLoginListener) {
    String validateResult;
    try {
      if (usernameInput.length() >= 6 && passwordInput.length() >= 6) {
        User loggeduser = verification(usernameInput, passwordInput);
        if (rememberMe) {
          userManager.getUserHashMap().put("Last", loggeduser);
          userManager.put("check", loggeduser);
          fileSaverLoader.save(userManager.getUserHashMap(), "User.ser");
        } else {
          userManager.getUserHashMap().remove("Last");
          userManager.getUserHashMap().remove("check");
          fileSaverLoader.save(userManager.getUserHashMap(), "User.ser");
        }
        UserManager.getInstance().setUser(loggeduser);
        onLoginListener.goToMenuActivity();
      } else {
        validateResult = "Your username and password must contain at least 6 characters!";
        onLoginListener.notValidInput(validateResult);
      }
    } catch (InputMismatchException e) {
      onLoginListener.notValidInput("Your username or password is not correct!");
    }
  }

  /**
   * checking validity of usernameInput and passwordInput for registration
   *
   * @param usernameInput username input for registration
   * @param passwordInput password input for registration
   * @param onRegisterListener an OnRegisterListener
   */
  void validateRegistration(
      String usernameInput, String passwordInput, OnRegisterListener onRegisterListener) {

    if (!(namingConvention(usernameInput) && namingConvention(passwordInput))) {
      onRegisterListener.notValidInput(
          "please only use letter and number for your username and password. ");
    }
    try {
      if (userManager.containsKey(usernameInput)) {
        onRegisterListener.notValidInput("The username already exists! ");
      } else if (usernameInput.length() < 6 | passwordInput.length() < 6) {
        onRegisterListener.notValidInput(
            "Your username and password must contain at " + "least 6 characters!");
      } else {
        User newUser = new User(usernameInput, passwordInput);

        userManager.setUser(newUser);
        userManager.put(usernameInput, newUser);
        fileSaverLoader.save(userManager.getUserHashMap(), "User.ser");
        onRegisterListener.goToLoginActivity();
      }
    } catch (NullPointerException e) {
      System.out.println("NullPointException is caught");
      e.printStackTrace();
      onRegisterListener.notValidInput("NullPointException is caught");
    }
  }

  /**
   * checking all characters in the string are alphabet or number
   *
   * @param string a string of username of password
   */
  private boolean namingConvention(String string) {
    String regex = "^[a-zA-z0-9]*$";
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(string).matches();
  }

  /**
   * initialize rememberMe
   *
   * @param onLoginListener an OnLoginListener
   */
  void initRememberMe(OnLoginListener onLoginListener) {
    if (userManager.containsKey("Last") && userManager.containsKey("check")) {
      User lastUser = userManager.getUserHashMap().get("Last");
      onLoginListener.showRememberMe(lastUser.getUsername(), lastUser.getPassword());
    }
  }
}
