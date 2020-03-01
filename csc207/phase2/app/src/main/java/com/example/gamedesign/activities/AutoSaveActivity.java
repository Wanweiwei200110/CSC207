package com.example.gamedesign.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserManager;

/** The activity displayed for saving user data */
public abstract class AutoSaveActivity extends AppCompatActivity {

  /** a file saver loader */
  protected FileSaverLoader fileSaverLoader;

  /** a user saver */
  protected UserManager userManager;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    fileSaverLoader = new FileSaverLoader(getApplicationContext());
    userManager = UserManager.getInstance();
  }

  /** The Method is for saving hash map containing all users. */
  public void save() {
    fileSaverLoader.save(userManager.getUserHashMap(), "User.ser");
  }

  /** The operations performed while the activity is destroyed. */
  @Override
  public void onDestroy() {
    super.onDestroy();
    save();
  }
}
