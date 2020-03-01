package com.example.gamedesign.gameactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserManager;

/** Activity of the three games. */
public abstract class GameActivity extends AppCompatActivity implements View.OnClickListener {

  /** used to save and load file */
  protected FileSaverLoader fileSaverLoader;

  /**
   * Operations performed when the activity is created.
   *
   * @param savedInstanceState the instance state saved
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    fileSaverLoader = new FileSaverLoader(getApplicationContext());
  }

  /** Operations performed when the activity is stopped. */
  @Override
  public void onStop() {
    super.onStop();
    fileSaverLoader.save(UserManager.getInstance().getUserHashMap(), "User.ser");
  }

  /** Operations performed when the activity is destroyed. */
  @Override
  public void onDestroy() {
    super.onDestroy();
    fileSaverLoader.save(UserManager.getInstance().getUserHashMap(), "User.ser");
  }

  /** Operations performed when the activity is resumed. */
  public void onResume() {
    super.onResume();
    fileSaverLoader.load("User.ser");
  }

  /**
   * Created a Button with text "Pause" on the right top corner with given id and an onclicklistener
   * to this activity
   *
   * @param id id of the button
   */
  @SuppressLint("SetTextI18n")
  protected Button createPauseButton(int id) {
    Button button = new Button(this);
    button.setText("pause");
    button.setId(id);
    // parameters for the button
    RelativeLayout.LayoutParams param_for_button =
        new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    // make button align top right corner
    param_for_button.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
    param_for_button.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
    button.setLayoutParams(param_for_button);

    button.setOnClickListener(this);
    return button;
  }

  /**
   * create a relative layout to contain a button
   *
   * @return pauseButtonLayout
   */
  protected RelativeLayout createRLayout(Button button) {
    RelativeLayout pauseButtonLayout = new RelativeLayout(this);

    // parameters for the relativelayout
    RelativeLayout.LayoutParams params_for_layout =
        new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    // make button align top right corner

    pauseButtonLayout.setLayoutParams(params_for_layout);
    pauseButtonLayout.addView(button);

    return pauseButtonLayout;
  }

  /** set this activity to fullscreen */
  protected void fullscreen() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }

  /**
   * combine a relativelayout and a surfaceview to one framelayout
   *
   * @param relativeLayout a relativelayout
   * @param surfaceView a surfaceview for animation of the game
   * @return a framelayout
   */
  protected FrameLayout combineRelativeAndSurface(
      RelativeLayout relativeLayout, SurfaceView surfaceView) {
    FrameLayout frameLayout = new FrameLayout(this);
    frameLayout.addView(surfaceView);
    frameLayout.addView(relativeLayout);

    return frameLayout;
  }

  /**
   * Operations performed when the pause button is clicked.
   *
   * @param v the view of the game
   */
  public void onClick(View v) {}
}
