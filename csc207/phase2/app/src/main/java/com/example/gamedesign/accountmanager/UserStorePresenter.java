package com.example.gamedesign.accountmanager;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanagerinterface.OnBuyListener;

import java.util.List;

/** Presenter class used to separate user and storeActivity */
public class UserStorePresenter implements OnBuyListener {

  /** currently logged in user */
  private User user;

  /** frontend activity implemented onBuyListener */
  private OnBuyListener onBuyListener;

  /** total cost of item in cart */
  private int cost;

  /** items checked by user in storeActivity */
  private List<Integer> cart;

  /**
   * constructing a new UserStorePresenter
   *
   * @param onBuyListener a OnBuyListener
   * @param user a user
   * @param cart cart of the user
   */
  public UserStorePresenter(OnBuyListener onBuyListener, User user, List<Integer> cart) {
    this.user = user;
    this.onBuyListener = onBuyListener;
    this.cart = cart;
    this.cost = 0;
  }

  /**
   * put item into cart and add or subtract corresponding price
   *
   * @param item item checked by user in storeActivity
   */
  @Override
  public void addItemToCart(int item) {
    int price;
    if (item == R.id.store_magical_item) {
      price = 100;
    } else {
      price = 300;
    }
    if (cart.contains(Integer.valueOf(item))) {
      cart.remove(Integer.valueOf(item));
      cost -= price;
    } else {
      cart.add(item);
      cost += price;
    }
  }

  /** called when user clicked on buy button in storeActivity passing information to user class */
  @Override
  public void buy() {
    user.buy(this, cost, cart);
  }

  /** checking if icon is already bought by user */
  @Override
  public void checkBoughtIcon() {
    user.checkBoughtIcon(this);
  }

  /**
   * disable checkbox for those bought icon
   *
   * @param icon bought icon
   */
  @Override
  public void disableBoughtIcon(int icon) {
    onBuyListener.disableBoughtIcon(icon);
  }

  /**
   * increment the number of magical item displayed in storeActivity
   *
   * @param itemText number of magical item
   */
  @Override
  public void changeResurrectionKeyText(String itemText) {
    onBuyListener.changeResurrectionKeyText(itemText);
  }

  /**
   * change the number of gold displayed in storeActivity
   *
   * @param goldText gold text
   */
  @Override
  public void changeGoldText(String goldText) {
    onBuyListener.changeGoldText(goldText);
  }
}
