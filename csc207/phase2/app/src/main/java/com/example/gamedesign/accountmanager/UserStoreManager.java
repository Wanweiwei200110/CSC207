package com.example.gamedesign.accountmanager;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanagerinterface.OnBuyListener;

import java.io.Serializable;
import java.util.List;

/**
 * A class stored by user managing all the item user can buy from store response to the action of
 * changing gold and buying stuff
 */
class UserStoreManager implements Serializable {

  /** gold of the user */
  private int gold;

  /** resurrectionKey of the user */
  private int resurrectionKey;

  /** bought icon of the user */
  private List<Integer> iconBought;

  /** owned icon of the user */
  private List<Integer> ownedIcon;

  /**
   * constructing a new UserStoreManager by setting default icon, number of magical item to 3 and
   * gold to 500
   *
   * @param iconBought list of bought icon of the user
   * @param ownedIcon list of owned icon of the user
   */
  UserStoreManager(List<Integer> iconBought, List<Integer> ownedIcon) {
    resurrectionKey = 3;
    gold = 500;
    this.iconBought = iconBought;
    this.ownedIcon = ownedIcon;
  }

  /**
   * buy item
   *
   * @param onBuyListener a OnBuyListener
   * @param cost golf cost for items in cart
   * @param cart items in cart
   */
  void buy(OnBuyListener onBuyListener, int cost, List<Integer> cart) {
    int moneyLeft;
    if (getGold() >= cost) {
      moneyLeft = getGold() - cost;
      buyItem(cart);
      setGold(moneyLeft);
    }
    onBuyListener.changeGoldText("Gold:" + getGold());
    onBuyListener.changeResurrectionKeyText("Magical Item:" + getResurrectionKey());
    onBuyListener.checkBoughtIcon();
  }

  /**
   * buy item in cart
   *
   * @param cart items in cart
   */
  private void buyItem(List<Integer> cart) {
    for (int item : cart) {
      if (Integer.valueOf(item).equals(R.id.store_magical_item)) {
        setResurrectionKey(getResurrectionKey() + 1);
      } else {
        iconBought.add(item);
        addToOwnedIcon(item);
      }
    }
  }

  /**
   * add owned icon after user buy it
   *
   * @param item item user bought
   */
  private void addToOwnedIcon(int item) {
    switch (item) {
      case (R.id.store_icon1):
        ownedIcon.add(R.drawable.cat_icon);
        break;
      case (R.id.store_icon2):
        ownedIcon.add(R.drawable.rabbit_icon);
        break;
      case (R.id.store_icon3):
        ownedIcon.add(R.drawable.wolf_icon);
        break;
      case (R.id.store_icon4):
        ownedIcon.add(R.drawable.panda_icon);
        break;
    }
  }

  /** get the number of magic item owned by the user */
  int getResurrectionKey() {
    return resurrectionKey;
  }

  /**
   * set the user's magic item
   *
   * @param num number of magic item for the user
   */
  void setResurrectionKey(int num) {
    this.resurrectionKey = num;
  }

  /** get the amount of gold owned by the user */
  protected int getGold() {
    return gold;
  }

  /**
   * set the user's gold
   *
   * @param gold gold number of the user
   */
  protected void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * get owned icon
   *
   * @return list of owned icon
   */
  List<Integer> getOwnedIcon() {
    return ownedIcon;
  }

  /**
   * check bought icon
   *
   * @param onBuyListener a OnBuyListener
   */
  void checkBoughtIcon(OnBuyListener onBuyListener) {
    for (int boughtIcon : iconBought) {
      onBuyListener.disableBoughtIcon(boughtIcon);
    }
  }
}
