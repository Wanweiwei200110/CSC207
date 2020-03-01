package com.example.gamedesign.activities;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.systemmanager.UserManager;

/** The activity displayed for user's text color, theme, and icon. */
public abstract class CustomizableActivity extends AutoSaveActivity {

  /** the user currently logged in */
  protected User loggedUser;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    loggedUser = UserManager.getInstance().getUser();
  }

  /** The method is for setting the currently logged in user's text color. */
  public void setTextColor() {
    int recorderColor = loggedUser.getTextColor();
    setTheme(recorderColor);
  }

  /** The method is for setting the currently logged in user's background color. */
  public void setBackgroudColor() {
    int recorderBackground = loggedUser.getTheme();
    getWindow().getDecorView().setBackgroundColor(getResources().getColor(recorderBackground));
  }

  /** The method is for setting the currently logged in user's icon. */
  public void setIcon(ImageView icon) {
    int recordedIcon = loggedUser.getIcon();
    icon.setImageResource(recordedIcon);
  }
}
