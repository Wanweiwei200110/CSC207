package com.example.gamedesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.activities.LoginActivity;
import com.example.gamedesign.activities.RankingActivity;
import com.example.gamedesign.activities.RegisterActivity;
import com.example.gamedesign.scoringsystem.ScoreInitializePresenter;
import com.example.gamedesign.scoringsysteminterface.OnInitializeListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserManager;

import java.util.HashMap;

/** The activity displayed when the app is run. */
public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, OnInitializeListener {

  /** a file save loader for saving and loading data */
  private FileSaverLoader fileSaverLoader;

  private ScoreInitializePresenter scoreInitializePresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button loginButton = findViewById(R.id.main_login);
    Button registerButton = findViewById(R.id.main_register);
    ImageView rotated1 = findViewById(R.id.rotated1);
    ImageView rotated2 = findViewById(R.id.rotated2);
    ImageView rotated3 = findViewById(R.id.rotated3);
    rotated1.startAnimation(
        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation_slow));
    rotated2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation));
    rotated3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation));

    loginButton.setOnClickListener(this);
    registerButton.setOnClickListener(this);

    fileSaverLoader = new FileSaverLoader(getApplicationContext());
    initUserMap();
    scoreInitializePresenter = new ScoreInitializePresenter(fileSaverLoader);
    initScoreBoard(RankingActivity.HANGMAN_SCORE_BOARD);
    initScoreBoard(RankingActivity.JUMPINGBALL_SCORE_BOARD);
    initScoreBoard(RankingActivity.TILE_SCORE_BOARD);
  }

  /** The operations performed when button is clicked. */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.main_login:
        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentLogin);
        break;
      case R.id.main_register:
        Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intentRegister);
        break;
    }
  }

  /** The method is for initialize user map. */
  private void initUserMap() {
    UserManager userManager = UserManager.getInstance();

    Object o = fileSaverLoader.load("User.ser");
    if (o == null) {
      userManager.setUserMap(new HashMap<String, User>());
      fileSaverLoader.save(userManager.getUserHashMap(), "User.ser");
    } else if (o instanceof HashMap) {
      userManager.setUserMap((HashMap<String, User>) o);
    }
  }

  /** initialize score board */
  @Override
  public void initScoreBoard(String scoreBoardName) {
    scoreInitializePresenter.initScoreBoard(scoreBoardName);
  }
}
