package com.example.gamedesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gamedesign.R;
import com.example.gamedesign.settingactivity.IconActivity;
import com.example.gamedesign.settingactivity.TextColorActivity;
import com.example.gamedesign.settingactivity.ThemeActivity;

/** The activity displayed when entering setting layout. */
public class SettingActivity extends CustomizableActivity implements View.OnClickListener {

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTextColor();

    setContentView(R.layout.activity_setting);
    Button themeButton = findViewById(R.id.set_theme);
    Button iconButton = findViewById(R.id.set_icon);
    Button textColorButton = findViewById(R.id.set_textcolor);
    Button backButton = findViewById(R.id.set_back);

    themeButton.setOnClickListener(this);
    iconButton.setOnClickListener(this);
    textColorButton.setOnClickListener(this);
    backButton.setOnClickListener(this);
    setBackgroudColor();
  }

  /** The operations performed when button is clicked. */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.set_theme:
        Intent intentTheme = new Intent(getApplicationContext(), ThemeActivity.class);
        startActivity(intentTheme);
        finish();
        break;
      case R.id.set_icon:
        Intent intentIcon = new Intent(getApplicationContext(), IconActivity.class);
        startActivity(intentIcon);
        finish();
        break;
      case R.id.set_textcolor:
        Intent intentTextColor = new Intent(getApplicationContext(), TextColorActivity.class);
        startActivity(intentTextColor);
        finish();
        break;
      case R.id.set_back:
        Intent intentBack = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intentBack);
        finish();
        break;
    }
  }
}
