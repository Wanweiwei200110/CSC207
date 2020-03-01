package com.example.gamedesign.gamestartactivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gamedesign.gameactivity.GameTilesActivity;

/** The activity displayed before the don't tap white tile game starts. */
public class TilesActivityStart extends GameStartActivity {

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
    loggedUser.resetBlock(level);
  }

  /** jump to the corresponding activity of the selected game */
  @Override
  protected void goToGame() {
    Intent intent_n = new Intent(getApplicationContext(), GameTilesActivity.class);
    startActivity(intent_n);
  }
}
