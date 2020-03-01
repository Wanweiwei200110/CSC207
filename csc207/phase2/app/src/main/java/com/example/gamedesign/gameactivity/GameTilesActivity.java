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
import com.example.gamedesign.donttouchwhitetiles.DontTouchWhiteTilesController;
import com.example.gamedesign.gamestartactivity.TilesActivityStart;
import com.example.gamedesign.gameviews.DontTouchWhiteTilesView;
import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserManager;

import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/** Activity of DoNotTouchWhiteTiles game. */
public class GameTilesActivity extends GameActivity implements View.OnTouchListener {

  /** view which the game displays on */
  private DontTouchWhiteTilesView gameView;

  /** used to save and load file */
  private FileSaverLoader fileSaverLoader;
  /** used to be saved */
  private HashMap<String, User> userMap;

  /**
   * Operations performed when the activity is created.
   *
   * @param savedInstanceState the instance state saved
   */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    User loggedUser = UserManager.getInstance().getUser();
    userMap = UserManager.getInstance().getUserHashMap();
    // try to load game
    DontTouchWhiteTilesController game = loggedUser.getDontTouchWhiteTilesController();
    fileSaverLoader = new FileSaverLoader(getApplicationContext());
    gameView = new DontTouchWhiteTilesView(this, loggedUser, game, fileSaverLoader);
    fullscreen();
    int pauseButtonId = 123456123;
    Button pause = createPauseButton(pauseButtonId);
    RelativeLayout myRelativeLayout = createRLayout(pause);

    FrameLayout myFrameLayout = combineRelativeAndSurface(myRelativeLayout, gameView);
    gameView.setOnTouchListener(this);

    setContentView(myFrameLayout);
  }

  /**
   * action after user clicks pause button
   *
   * @param v view
   */
  @Override
  public void onClick(View v) {
    gameView.pause();
    Intent i = new Intent(this, TilesActivityStart.class);
    fileSaverLoader.save(userMap, "User.ser");
    i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
    startActivity(i);
  }

  /**
   * action after user taps screen
   *
   * @param v view
   * @param event motion event
   */
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    gameView.onTouch(event);
    return true;
  }
}
