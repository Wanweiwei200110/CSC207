package com.example.gamedesign.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamedesign.R;
import com.example.gamedesign.scoringsystem.GameScore;
import com.example.gamedesign.scoringsystem.ScoreDisplayPresenter;
import com.example.gamedesign.scoringsysteminterface.OnDisplayListener;

import java.util.ArrayList;
import java.util.List;

/** an activity for user's game score ranking */
public class RankingActivity extends CustomizableActivity
    implements View.OnClickListener, OnDisplayListener {

  /** Static strings helps initialize scoreboards of hangman game in mainActivity */
  public static final String HANGMAN_SCORE_BOARD = "hangManScoreBoard.ser";

  /** Static strings helps initialize scoreboards of jumping ball game in mainActivity */
  public static final String JUMPINGBALL_SCORE_BOARD = "ballScoreBoard.ser";

  /** Static strings helps initialize scoreboards of tile game in mainActivity */
  public static final String TILE_SCORE_BOARD = "tileScoreBoard.ser";

  /** Icon view for all top 10 records and user's own highest record */
  private ImageView icon01;

  private ImageView icon02;
  private ImageView icon03;
  private ImageView icon04;
  private ImageView icon05;
  private ImageView icon06;
  private ImageView icon07;
  private ImageView icon08;
  private ImageView icon09;
  private ImageView icon10;

  /** Name text for all top 10 record sand user's own highest record */
  private TextView name01;

  private TextView name02;
  private TextView name03;
  private TextView name04;
  private TextView name05;
  private TextView name06;
  private TextView name07;
  private TextView name08;
  private TextView name09;
  private TextView name10;

  /** Score text for all top 10 recordsand user's own highest record */
  private TextView score01;

  private TextView score02;
  private TextView score03;
  private TextView score04;
  private TextView score05;
  private TextView score06;
  private TextView score07;
  private TextView score08;
  private TextView score09;
  private TextView score10;
  private TextView userRank;

  /* ArrayLists contains all icon's view, name's textviews and score's textviews
  of all top 1o records */

  /** ArrayLists contains all icon's view of all top 10 records */
  private ArrayList<ImageView> iconList;

  /** ArrayLists contains all name's view of all top 10 records */
  private ArrayList<TextView> nameList;

  /** ArrayLists contains all score's view of all top 10 records */
  private ArrayList<TextView> scoreList;

  /** a ScoreDisplayPresenter */
  private ScoreDisplayPresenter scoreDisplayPresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTextColor();
    setBackgroudColor();
    setContentView(R.layout.activity_ranking);

    scoreDisplayPresenter = new ScoreDisplayPresenter(this, fileSaverLoader);
    Button backButton = findViewById(R.id.scoreboard_back);
    backButton.setOnClickListener(this);
    Button hangmanButton = findViewById(R.id.score_hangman);
    Button ballButton = findViewById(R.id.score_ball);
    Button blockButton = findViewById(R.id.score_tiles);

    hangmanButton.setOnClickListener(this);
    ballButton.setOnClickListener(this);
    blockButton.setOnClickListener(this);

    icon01 = findViewById(R.id.score1_icon);
    icon02 = findViewById(R.id.score2_icon);
    icon03 = findViewById(R.id.score3_icon);
    icon04 = findViewById(R.id.score4_icon);
    icon05 = findViewById(R.id.score5_icon);
    icon06 = findViewById(R.id.score6_icon);
    icon07 = findViewById(R.id.score7_icon);
    icon08 = findViewById(R.id.score8_icon);
    icon09 = findViewById(R.id.score9_icon);
    icon10 = findViewById(R.id.score10_icon);
    ImageView userIcon = findViewById(R.id.score_usericon);

    name01 = findViewById(R.id.score1_name);
    name02 = findViewById(R.id.score2_name);
    name03 = findViewById(R.id.score3_name);
    name04 = findViewById(R.id.score4_name);
    name05 = findViewById(R.id.score5_name);
    name06 = findViewById(R.id.score6_name);
    name07 = findViewById(R.id.score7_name);
    name08 = findViewById(R.id.score8_name);
    name09 = findViewById(R.id.score9_name);
    name10 = findViewById(R.id.score10_name);
    TextView userName = findViewById(R.id.score_username);

    score01 = findViewById(R.id.score1_score);
    score02 = findViewById(R.id.score2_score);
    score03 = findViewById(R.id.score3_score);
    score04 = findViewById(R.id.score4_score);
    score05 = findViewById(R.id.score5_score);
    score06 = findViewById(R.id.score6_score);
    score07 = findViewById(R.id.score7_score);
    score08 = findViewById(R.id.score8_score);
    score09 = findViewById(R.id.score9_score);
    score10 = findViewById(R.id.score10_score);
    userRank = findViewById(R.id.score_userscore);

    scoreList = getScoreList();
    iconList = getIconList();
    nameList = getNameList();

    userIcon.setImageResource(loggedUser.getIcon());
    userName.setText("Your highest score/rank:");
    userRank.setText("---");
  }

  /** The operations performed when button is clicked. */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.scoreboard_back:
        Intent intentBack = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intentBack);
        finish();
        break;

      case R.id.score_tiles:
        loadGameRank(TILE_SCORE_BOARD);
        break;

      case R.id.score_ball:
        loadGameRank(JUMPINGBALL_SCORE_BOARD);
        break;

      case R.id.score_hangman:
        loadGameRank(HANGMAN_SCORE_BOARD);
        break;
    }
  }

  /**
   * Set the scoreBoard field according to which game button is clicked.
   *
   * @param leaderBoard - the leader board of the given game
   */
  public void setScoreBoard(List<GameScore> leaderBoard) {
    resetText(scoreList);
    resetText(nameList);
    resetImage(iconList);
    for (int i = 0; i < leaderBoard.size(); i++) {
      int currentIcon = leaderBoard.get(i).getUser().getIcon();
      iconList.get(i).setImageResource(currentIcon);

      String currentName = leaderBoard.get(i).getName();
      nameList.get(i).setText(currentName);

      int currentScore = leaderBoard.get(i).getScore();
      scoreList.get(i).setText(String.valueOf(currentScore));
    }
  }

  /**
   * Set user field, show user's icon, and user's highest rank for certain game according to which
   * game's scoreboard is passed in.
   *
   * @param rank - the highest rank of current user
   */
  @SuppressLint("SetTextI18n")
  @Override
  public void setUserField(int rank, int score) {
    userRank.setText(score + "/#" + rank);
  }

  /** @return ArrayLists contains all icon's view of all top 10 records */
  private ArrayList<ImageView> getIconList() {
    ArrayList<ImageView> icons = new ArrayList<>();
    icons.add(icon01);
    icons.add(icon02);
    icons.add(icon03);
    icons.add(icon04);
    icons.add(icon05);
    icons.add(icon06);
    icons.add(icon07);
    icons.add(icon08);
    icons.add(icon09);
    icons.add(icon10);
    return icons;
  }

  /** @return ArrayLists contains all name's view of all top 10 records */
  private ArrayList<TextView> getNameList() {
    ArrayList<TextView> names = new ArrayList<>();
    names.add(name01);
    names.add(name02);
    names.add(name03);
    names.add(name04);
    names.add(name05);
    names.add(name06);
    names.add(name07);
    names.add(name08);
    names.add(name09);
    names.add(name10);
    return names;
  }

  /** @return ArrayLists contains all score's view of all top 10 records */
  private ArrayList<TextView> getScoreList() {
    ArrayList<TextView> scores = new ArrayList<>();
    scores.add(score01);
    scores.add(score02);
    scores.add(score03);
    scores.add(score04);
    scores.add(score05);
    scores.add(score06);
    scores.add(score07);
    scores.add(score08);
    scores.add(score09);
    scores.add(score10);
    return scores;
  }

  /** reset texts */
  private void resetText(ArrayList<TextView> texts) {
    for (int i = 0; i < texts.size(); i++) {
      String nullString = "";
      texts.get(i).setText(nullString);
    }
  }

  /** reset image */
  private void resetImage(ArrayList<ImageView> images) {
    for (int i = 0; i < images.size(); i++) {
      images.get(i).setImageResource(R.drawable.clear_icon);
    }
  }

  /**
   * load game score ranking
   *
   * @param scoreBoardName name for the score board
   */
  @Override
  public void loadGameRank(String scoreBoardName) {
    scoreDisplayPresenter.loadGameRank(scoreBoardName);
  }

  /** The operations performed while the activity is destroyed. */
  @Override
  public void onDestroy() {
    super.onDestroy();
    scoreDisplayPresenter = null;
  }
}
