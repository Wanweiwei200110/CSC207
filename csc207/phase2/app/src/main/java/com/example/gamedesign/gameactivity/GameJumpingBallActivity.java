package com.example.gamedesign.gameactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.gamestartactivity.JumpingBallActivityStart;
import com.example.gamedesign.gameviews.JumpingBallView;
import com.example.gamedesign.jumpingball.JumpingBallController;
import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserManager;

import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/** Activity of jumping ball game. */
public class GameJumpingBallActivity extends GameActivity implements View.OnTouchListener {

  /** The display of the game. */
  private JumpingBallView gameView;

  /** The saver and loader of the game. */
  private FileSaverLoader fileSaverLoader;

  /** Hashmap matching name and user. */
  private HashMap<String, User> userMap;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    User loggedUser = UserManager.getInstance().getUser();

    fullscreen();

    userMap = UserManager.getInstance().getUserHashMap();
    // try to load game
    JumpingBallController game = loggedUser.getJumpingBallController();
    fileSaverLoader = new FileSaverLoader(getApplicationContext());
    gameView = new JumpingBallView(this, loggedUser, game, fileSaverLoader);

    int pauseButtonId = 12345;

    Button pause = createPauseButton(pauseButtonId);
    RelativeLayout myRelativeLayout = createRLayout(pause);

    FrameLayout myFrameLayout = combineRelativeAndSurface(myRelativeLayout, gameView);
    gameView.setOnTouchListener(this);

    setContentView(myFrameLayout);
  }

  /**
   * Operations performed when the screen is touched.
   *
   * @param v the game view
   * @param event touch event
   * @return true
   */
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    gameView.onTouch(event);
    return true;
  }

  /**
   * Operations performed when the pause button is clicked.
   *
   * @param v the game view
   */
  @Override
  public void onClick(View v) {
    gameView.pause();
    Intent i = new Intent(this, JumpingBallActivityStart.class);
    fileSaverLoader.save(userMap, "User.ser");
    i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
    startActivity(i);
  }
}
