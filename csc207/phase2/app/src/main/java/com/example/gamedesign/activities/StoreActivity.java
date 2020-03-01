package com.example.gamedesign.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanager.UserStorePresenter;
import com.example.gamedesign.accountmanagerinterface.OnBuyListener;

import java.util.ArrayList;

/** The activity displayed after entering store. */
public class StoreActivity extends CustomizableActivity
    implements View.OnClickListener, OnBuyListener {

  /** current user's gold */
  private TextView goldText;

  /** current user's magic item number. */
  private TextView itemText;

  /** a UserStorePresenter */
  private UserStorePresenter userStorePresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    userStorePresenter = new UserStorePresenter(this, loggedUser, new ArrayList<>());
    setTextColor();
    setBackgroudColor();
    setContentView(R.layout.activity_store);
    checkBoughtIcon();

    goldText = findViewById(R.id.your_gold);
    itemText = findViewById(R.id.your_item);

    goldText.setText("Gold:" + loggedUser.getGold());
    itemText.setText("Resurrection Key:" + loggedUser.getResurrectionKey());

    Button backButton = findViewById(R.id.shop_back);
    Button buyButton = findViewById(R.id.store_buy);

    backButton.setOnClickListener(this);
    buyButton.setOnClickListener(this);
  }

  /** check the box is clicked and buy icon. */
  public void onCheckboxClicked(View view) {
    // Is the view now checked?

    // Check which checkbox was clicked
    switch (view.getId()) {
      case R.id.store_icon1:
        addItemToCart(R.id.store_icon1);
        break;
      case R.id.store_icon2:
        addItemToCart(R.id.store_icon2);
        break;
      case R.id.store_icon3:
        addItemToCart(R.id.store_icon3);
        break;
      case R.id.store_icon4:
        addItemToCart(R.id.store_icon4);
        break;
      case R.id.store_magical_item:
        addItemToCart(R.id.store_magical_item);
        break;
    }
  }

  /** The operations performed when button is clicked. */
  @SuppressLint("SetTextI18n")
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.shop_back:
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
        break;
      case R.id.store_buy:
        buy();
        break;
    }
  }

  /**
   * add item to cart
   *
   * @param item index of item
   */
  @Override
  public void addItemToCart(int item) {
    userStorePresenter.addItemToCart(item);
  }

  /** buy item */
  @Override
  public void buy() {
    userStorePresenter.buy();
  }

  /** check bought icon */
  @Override
  public void checkBoughtIcon() {
    userStorePresenter.checkBoughtIcon();
  }

  /**
   * disable bought icon after user buy it
   *
   * @param icon icon user bought
   */
  @Override
  public void disableBoughtIcon(int icon) {
    findViewById(icon).setEnabled(false);
  }

  /**
   * change gold text after user buy item
   *
   * @param goldText text of gold number of user
   */
  @Override
  public void changeGoldText(String goldText) {
    this.goldText.setText(goldText);
  }

  /**
   * change resurrection key text
   *
   * @param itemText String of the number of resurrection key left
   */
  @Override
  public void changeResurrectionKeyText(String itemText) {
    this.itemText.setText(itemText);
  }

  /** The operations performed while the activity is destroyed. */
  @Override
  public void onDestroy() {
    super.onDestroy();
    userStorePresenter = null;
  }
}
