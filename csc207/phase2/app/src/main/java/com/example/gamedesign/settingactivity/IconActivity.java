package com.example.gamedesign.settingactivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanager.UserIconPresenter;
import com.example.gamedesign.accountmanagerinterface.OnSetIconListener;
import com.example.gamedesign.activities.CustomizableActivity;
import com.example.gamedesign.activities.SettingActivity;

/** The activity displayed for setting icon. */
public class IconActivity extends CustomizableActivity
    implements View.OnClickListener, OnSetIconListener {

  /** a UserIconPresenter */
  private UserIconPresenter userIconPresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTextColor();
    setBackgroudColor();
    setContentView(R.layout.activity_icon);
    initButtons();
  }

  /** Initialize all the button of this activity and set all buttons' onClickListeners */
  public void initButtons() {
    Button icon1 = findViewById(R.id.i1);
    Button icon2 = findViewById(R.id.i2);
    Button icon3 = findViewById(R.id.i3);
    Button icon4 = findViewById(R.id.i4);
    Button female = findViewById(R.id.female_default);
    Button male = findViewById(R.id.male_default);
    Button backIcon = findViewById(R.id.icon_back);

    icon1.setOnClickListener(this);
    icon2.setOnClickListener(this);
    icon3.setOnClickListener(this);
    icon4.setOnClickListener(this);
    female.setOnClickListener(this);
    male.setOnClickListener(this);
    backIcon.setOnClickListener(this);
    userIconPresenter = new UserIconPresenter(this, loggedUser);
  }

  /** The operations performed when button is clicked. */
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.i1:
        setIcon(R.drawable.cat_icon);
        break;
      case R.id.i2:
        setIcon(R.drawable.rabbit_icon);
        break;
      case R.id.i3:
        setIcon(R.drawable.wolf_icon);
        break;
      case R.id.i4:
        setIcon(R.drawable.panda_icon);
        break;
      case R.id.female_default:
        setIcon(R.drawable.female_icon);
        break;
      case R.id.male_default:
        setIcon(R.drawable.male_icon);
        break;
      case R.id.icon_back:
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
        finish();
    }
  }

  /** set icon */
  @Override
  public void setIcon(int icon) {
    userIconPresenter.setIcon(icon);
  }

  /** check the availability of the icon */
  @Override
  public void notAvailable() {
    new AlertDialog.Builder(this)
        .setTitle("You didn't buy this icon. Please buy it in the Store first!")
        .setPositiveButton(android.R.string.yes, null)
        .setIcon(android.R.drawable.ic_dialog_info)
        .show();
  }

  /** The operations performed while the activity is destroy. */
  @Override
  public void onDestroy() {
    super.onDestroy();
    userIconPresenter = null;
  }
}
