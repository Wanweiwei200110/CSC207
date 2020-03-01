package com.example.gamedesign.gameactivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.activities.RankingActivity;
import com.example.gamedesign.gamestartactivity.HangmanActivityStart;
import com.example.gamedesign.hangman.HangmanController;
import com.example.gamedesign.hangman.HangmanPresenter;
import com.example.gamedesign.scoringsystem.ScoreRecordPresenter;
import com.example.gamedesign.scoringsysteminterface.OnRecordListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserManager;

/** Activity of hangman game */
public class GameHangManActivity extends AppCompatActivity implements OnRecordListener {

  /** text for hint */
  private TextView hintText;

  /** a HangmanPresenter */
  private HangmanPresenter hangmanPresenter;

  /** a ScoreRecordPresenter */
  private ScoreRecordPresenter scoreRecordPresenter;

  private User loggedUser;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_hangman_starts);
    FileSaverLoader fileSaverLoader = new FileSaverLoader(getApplicationContext());
    hintText = findViewById(R.id.guessHint);
    loggedUser = UserManager.getInstance().getUser();
    HangmanController hangmanController = loggedUser.getHangmanController();
    hangmanPresenter = new HangmanPresenter(this, hangmanController, loggedUser);
    scoreRecordPresenter = new ScoreRecordPresenter(this, fileSaverLoader);
    hangmanPresenter.setHint(hangmanPresenter.newHint());
    hangmanPresenter.setImageView(hangmanPresenter.getErrorLeft());
    setGuessBtn();
    setPauseBtn();
    setHelpBtn();
    updateGoldAndScore(0, 0);
    hintText.setText(setInitialHint());
  }

  /** @return a string of hint */
  private String setInitialHint() {
    return hangmanPresenter.setInitialHint();
  }

  /** set hint for hangman game */
  public void setHint(String s) {
    hintText.setText(s);
  }

  /** update gold and score for hangman game */
  @SuppressLint("SetTextI18n")
  public void updateGoldAndScore(int gold, int score) {
    TextView goldText = findViewById(R.id.Gold);
    TextView scoreText = findViewById(R.id.Score);
    scoreText.setText("Score:" + score);
    goldText.setText("Gold:" + gold);
  }

  /** set image view for hangman game */
  public void setImageView(int errorLeft) {
    ImageView hangManView = findViewById(R.id.hangman);
    switch (errorLeft) {
      case 6:
        hangManView.setImageResource(R.drawable.hangman1);
        break;
      case 5:
        hangManView.setImageResource(R.drawable.hangman2);
        break;
      case 4:
        hangManView.setImageResource(R.drawable.hangman3);
        break;
      case 3:
        hangManView.setImageResource(R.drawable.hangman4);
        break;
      case 2:
        hangManView.setImageResource(R.drawable.hangman5);
        break;
      case 1:
        hangManView.setImageResource(R.drawable.hangman6);
        break;
      case 0:
        hangManView.setImageResource(R.drawable.hangman7);
        break;
    }
  }

  /**
   * Reminds the player how many attempts remaining and the hint
   *
   * @param errorLeft int for number of attempts
   */
  @SuppressLint("SetTextI18n")
  public void setText(int errorLeft) {
    TextView prompt = findViewById(R.id.guessPromptRemains);
    prompt.setText("You have " + errorLeft + " times remaining");
  }

  /** set dialog alert for hangman game */
  public void setAlert(String s) {
    new AlertDialog.Builder(this)
        .setTitle(s)
        .setPositiveButton(android.R.string.yes, null)
        .setIcon(android.R.drawable.ic_dialog_info)
        .show();
  }

  /** show winning after user guess the right answer */
  public void wins() {
    final EditText editText = new EditText(this);
    editText.setSingleLine();
    editText.setHint("your name here");
    new AlertDialog.Builder(this)
        .setTitle("You win!!")
        .setMessage("Would you like to upload your score? ")
        .setView(editText)
        .setPositiveButton(
            android.R.string.yes,
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                String name = editText.getText().toString();
                recordGameScore(name, hangmanPresenter.getScore());
                navigateToStartGameActivity();
              }
            })
        .setNegativeButton(
            "Cancel",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                navigateToStartGameActivity();
              }
            })
        .setIcon(android.R.drawable.ic_dialog_info)
        .show();
  }

  /** set guess button for trying answer */
  private void setGuessBtn() {
    findViewById(R.id.guessBtn)
        .setOnClickListener(
            v -> {
              EditText guessCharacter = findViewById(R.id.guessCharacter);
              try {
                char guess = guessCharacter.getText().toString().charAt(0);
                guessCharacter.getText().clear();
                hangmanPresenter.guess(guess);
              } catch (StringIndexOutOfBoundsException e) {
                setAlert("You need to enter a letter!!");
              }
            });
  }

  /** When the player press the pause button. */
  private void setPauseBtn() {
    findViewById(R.id.pauseGameBtn)
        .setOnClickListener(
            v -> {
              Intent intent = new Intent(getApplicationContext(), HangmanActivityStart.class);
              startActivity(intent);
            });
  }

  /** The operations performed while the activity is destroyed. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    hangmanPresenter = null;
    scoreRecordPresenter = null;
  }

  /** set help button for hangman game */
  private void setHelpBtn() {
    findViewById(R.id.help)
        .setOnClickListener(
            v -> {
              hangmanPresenter.help();
            });
  }

  /** set invisible guess button */
  public void setInvisibleGuess() {
    findViewById(R.id.guessBtn).setVisibility(View.INVISIBLE);
  }

  /**
   * record game score
   *
   * @param name a name
   * @param score game score
   */
  @Override
  public void recordGameScore(String name, int score) {
    scoreRecordPresenter.recordGameScore(name, score);
  }

  /** get score board name for hangman game */
  @Override
  public String getScoreBoardName() {
    return RankingActivity.HANGMAN_SCORE_BOARD;
  }

  /** start game activity. */
  private void navigateToStartGameActivity() {
    hangmanPresenter.resetGame();
    Intent intent = new Intent(getApplicationContext(), HangmanActivityStart.class);
    startActivity(intent);
  }

  /**
   * help adding the gold to user
   *
   * @param new_gold number of gold need to be added
   */
  public void setNewGoldToUser(int new_gold) {
    loggedUser.setGold(loggedUser.getGold() + new_gold);
  }
}
