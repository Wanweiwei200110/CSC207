package com.example.gamedesign.gamestartactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.example.gamedesign.R;
import com.example.gamedesign.activities.CustomizableActivity;
import com.example.gamedesign.activities.MenuActivity;

/** The activity displayed before the game starts. */
abstract class GameStartActivity extends CustomizableActivity implements View.OnClickListener {

  /** The button to new game. */
  protected Button level1, level2, level3;

  /** The button to resume. */
  protected Button resumeGameButton;

  /** The button to go back. */
  protected Button backButton;

  /**
   * Operations performed when the activity is created.
   *
   * @param savedInstanceState the instance state saved
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTextColor();
    setContentView(R.layout.activity_game_page);

    level1 = findViewById(R.id.level1_button);
    level2 = findViewById(R.id.level2_button);
    level3 = findViewById(R.id.level3_button);

    resumeGameButton = findViewById(R.id.game_page_resume);
    backButton = findViewById(R.id.game_page_back);

    setBackgroudColor();

    level1.setOnClickListener(this);
    level2.setOnClickListener(this);
    level3.setOnClickListener(this);
    resumeGameButton.setOnClickListener(this);
    backButton.setOnClickListener(this);
  }

  /**
   * Operations performed when the pause button is clicked.
   *
   * @param view the view of the game
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.level1_button:
        createNewGameDiaglogue(1);
        break;
      case R.id.level2_button:
        createNewGameDiaglogue(2);
        break;
      case R.id.level3_button:
        createNewGameDiaglogue(3);
        break;
      case R.id.game_page_resume:
        goToGame();
        break;
      case R.id.game_page_back:
        Intent intent_b = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent_b);
        break;
    }
  }

  /** reset the corresponding game controller stored in user class for starting a new game */
  protected abstract void resetGame(int level);

  /** jump to the corresponding activity of the selected game */
  protected abstract void goToGame();

  /**
   * create a dialogue asking if to start a new game
   *
   * @param level the level of the new game
   */
  private void createNewGameDiaglogue(int level) {
    AlertDialog alertDialog =
        new AlertDialog.Builder(this)
            .setTitle("NEW GAME")
            .setMessage("Do you want to start a new game of level " + level + "?")
            .setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    resetGame(level);
                    goToGame();
                  }
                })
            .setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {}
                })
            .create();
    alertDialog.setCanceledOnTouchOutside(false);
    alertDialog.show();
  }
}
