package com.example.gamedesign.gamestartactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gamedesign.gameactivity.GameJumpingBallActivity;

/** The activity displayed before the jumping ball game starts. */
public class JumpingBallActivityStart extends GameStartActivity implements View.OnClickListener {

  /**
   * Operations performed when the activity is created.
   *
   * @param savedInstanceState the instance state saved
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /** reset the corresponding game controller stored in user class for starting a new game */
  @Override
  protected void resetGame(int level) {
    loggedUser.resetJumpingBall(level);
  }

  /** jump to the corresponding activity of the selected game */
  @Override
  protected void goToGame() {
    Intent intent_n = new Intent(getApplicationContext(), GameJumpingBallActivity.class);
    startActivity(intent_n);
  }
}
