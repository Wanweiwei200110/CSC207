package com.example.gamedesign.settingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanager.UserTextColorPresenter;
import com.example.gamedesign.accountmanagerinterface.OnSetTextColorListener;
import com.example.gamedesign.activities.CustomizableActivity;
import com.example.gamedesign.activities.SettingActivity;
import com.example.gamedesign.systemmanager.FileSaverLoader;

/** The activity displayed when set text color. */
public class TextColorActivity extends CustomizableActivity
    implements View.OnClickListener, OnSetTextColorListener {

  /** a UserTextColorPresenter */
  private UserTextColorPresenter userTextColorPresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTextColor();
    setContentView(R.layout.activity_text_color);
    setBackgroudColor();
    initButtons();
    fileSaverLoader = new FileSaverLoader(getApplicationContext());
    userTextColorPresenter = new UserTextColorPresenter(this, loggedUser);
  }

  /** Initialize all the button of this activity and set all buttons' onClickListeners */
  public void initButtons() {
    Button moonButton = findViewById(R.id.moon_b);
    Button minkButton = findViewById(R.id.mink_b);
    Button peachButton = findViewById(R.id.peach_b);
    Button orangeButton = findViewById(R.id.orange_b);
    Button backButton = findViewById(R.id.back_textcolor);
    moonButton.setOnClickListener(this);
    minkButton.setOnClickListener(this);
    peachButton.setOnClickListener(this);
    orangeButton.setOnClickListener(this);
    backButton.setOnClickListener(this);
  }

  /** The operations performed when button is clicked. */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.moon_b:
        setTextColor(R.style.Moon);
        break;
      case R.id.mink_b:
        setTextColor(R.style.Mink);
        break;
      case R.id.peach_b:
        setTextColor(R.style.Peach);
        break;
      case R.id.orange_b:
        setTextColor(R.style.Orange);
        break;
      case R.id.back_textcolor:
        save();
        goToSettingActivity();
        break;
    }
  }

  /** Use intent to go to setting activity */
  @Override
  public void goToSettingActivity() {
    Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
    startActivity(intent);
    finish();
  }

  /** set text color */
  @Override
  public void setTextColor(int textColor) {
    userTextColorPresenter.setTextColor(textColor);
  }

  /** The operations performed while the activity is destroy. */
  @Override
  public void onDestroy() {
    super.onDestroy();
    userTextColorPresenter = null;
  }
}
