package com.example.gamedesign.systemmanager;

import com.example.gamedesign.accountmanager.User;

import java.io.Serializable;
import java.util.HashMap;

/**
 * an singleton class used to pass user information around activities credit to
 * https://www.callicoder.com/java-singleton-design-pattern-example/
 */
public class UserManager implements Serializable {

  /** a static instance of UserManager */
  private static UserManager userManager;

  /** currently logged in user */
  private User user;

  /** a user Hash map containing all the users */
  private HashMap<String, User> userHashMap;

  /** an empty constructor */
  private UserManager() {}

  /** get an instance of Usersaver */
  public static UserManager getInstance() {
    if (userManager == null) {
      userManager = new UserManager();
    }
    return userManager;
  }

  /** get user */
  public User getUser() {
    return user;
  }

  /**
   * set user
   *
   * @param newUser new user
   */
  public void setUser(User newUser) {
    user = newUser;
  }

  /**
   * set user hash map
   *
   * @param newMap new hash map of user
   */
  public void setUserMap(HashMap<String, User> newMap) {
    userHashMap = newMap;
  }

  /** a get user hash map */
  public HashMap<String, User> getUserHashMap() {
    return userHashMap;
  }

  /**
   * put user to hash map
   *
   * @param username username of the user
   * @param user the user with the username
   */
  void put(String username, User user) {
    userHashMap.put(username, user);
  }

  /**
   * check the existence of the username in data
   *
   * @param usernameInput a username
   */
  boolean containsKey(String usernameInput) {
    if (userHashMap != null) {
      return userHashMap.containsKey(usernameInput);
    }
    return false;
  }
}
