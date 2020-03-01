package com.example.gamedesign.gamestartactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gamedesign.gameactivity.GameHangManActivity;

/** The activity displayed before the hangman game starts. */
public class HangmanActivityStart extends GameStartActivity implements View.OnClickListener {

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
    loggedUser.resetHangman(level);
  }

  /** jump to the corresponding activity of the selected game */
  @Override
  protected void goToGame() {
    Intent intent_n = new Intent(getApplicationContext(), GameHangManActivity.class);
    startActivity(intent_n);
  }
}
