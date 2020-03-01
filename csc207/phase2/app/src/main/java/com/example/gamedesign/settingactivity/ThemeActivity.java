package com.example.gamedesign.settingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanager.UserThemePresenter;
import com.example.gamedesign.accountmanagerinterface.OnSetThemeListener;
import com.example.gamedesign.activities.CustomizableActivity;
import com.example.gamedesign.activities.SettingActivity;

/**
 * Theme Reference to https://www.youtube.com/watch?v=UwfZjwPPG3I The activity displayed when set
 * theme.
 */
public class ThemeActivity extends CustomizableActivity
    implements View.OnClickListener, OnSetThemeListener {

  /** a default background view and a new background view */
  private View defaultBackground, newBackground;

  /** a UserThemePresenter */
  private UserThemePresenter userThemePresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setBackgroudColor();
    setTextColor();
    setContentView(R.layout.activity_theme);
    initButtons();
    defaultBackground = findViewById(R.id.default_background);
    newBackground = findViewById(R.id.new_background);
    userThemePresenter = new UserThemePresenter(this, loggedUser);
  }

  /** Initialize all the button of this activity and set all buttons' onClickListeners */
  private void initButtons() {
    Button saveTheme = findViewById(R.id.theme_save);
    Button green = findViewById(R.id.background_green);
    Button blue = findViewById(R.id.background_blue);
    Button pink = findViewById(R.id.background_pink);
    Button purple = findViewById(R.id.background_purple);
    Button backButton = findViewById(R.id.theme_back);
    saveTheme.setOnClickListener(this);
    green.setOnClickListener(this);
    blue.setOnClickListener(this);
    pink.setOnClickListener(this);
    purple.setOnClickListener(this);
    backButton.setOnClickListener(this);
  }

  /** The operations performed when button is clicked. */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.theme_back:
        goToSetting();
        break;
      case R.id.theme_save:
        saveTheme();
        break;
      case R.id.background_blue:
        setTempTheme(R.color.colorBlue, R.drawable.blue);

        break;
      case R.id.background_green:
        setTempTheme(R.color.colorGreen, R.drawable.green);

        break;
      case R.id.background_pink:
        setTempTheme(R.color.colorPink, R.drawable.pink);

        break;
      case R.id.background_purple:
        setTempTheme(R.color.colorPurple, R.drawable.purple);

        break;
    }
  }

  /** set the theme */
  @Override
  public void setTempTheme(int theme, int background) {
    userThemePresenter.setTempTheme(theme, background);
  }

  /** go to setting activity */
  @Override
  public void goToSetting() {
    Intent goToSetting = new Intent(getApplicationContext(), SettingActivity.class);
    startActivity(goToSetting);
    finish();
  }

  /** save the theme for the user */
  @Override
  public void saveTheme() {
    userThemePresenter.saveTheme();
  }

  /** changing the background color for this activity and all the other activities */
  @Override
  public void changeBackground(int backgroundId) {
    newBackground.setBackgroundResource(backgroundId);
    newBackground.animate().translationY(0).scaleX(3).scaleY(3).setDuration(800).start();
  }

  /** animation for changing background */
  @Override
  public void backgroundTransitionAnimation(int color) {
    defaultBackground.setScaleX(3);
    defaultBackground.setScaleX(3);
    defaultBackground.setBackgroundResource(color);

    newBackground.setScaleX(0);
    newBackground.setScaleY(0);
  }

  /** The operations performed while the activity is destroy. */
  @Override
  public void onDestroy() {
    super.onDestroy();
    userThemePresenter = null;
  }
}
