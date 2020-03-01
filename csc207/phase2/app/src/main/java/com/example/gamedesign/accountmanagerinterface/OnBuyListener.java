package com.example.gamedesign.accountmanagerinterface;

/**
 * An interface handling user actions of buying in store implements by UserStorePresenter,
 * StoreActivity
 */
public interface OnBuyListener {

  /**
   * add item to cart
   *
   * @param item index of item
   */
  void addItemToCart(int item);

  /** buy item */
  void buy();

  /** check bought icon */
  void checkBoughtIcon();

  /**
   * disable bought icon after user buy it
   *
   * @param icon icon user bought
   */
  void disableBoughtIcon(int icon);

  /**
   * change gold text after user buy item
   *
   * @param goldText text of gold number of user
   */
  void changeGoldText(String goldText);

  /**
   * change resurrection key text
   *
   * @param itemText String of the number of resurrection key left
   */
  void changeResurrectionKeyText(String itemText);
}
