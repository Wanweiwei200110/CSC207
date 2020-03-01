package com.example.gamedesign.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamedesign.MainActivity;
import com.example.gamedesign.R;
import com.example.gamedesign.gamestartactivity.HangmanActivityStart;
import com.example.gamedesign.gamestartactivity.JumpingBallActivityStart;
import com.example.gamedesign.gamestartactivity.TilesActivityStart;

/** The activity displayed after user is logged in. */
public class MenuActivity extends CustomizableActivity implements View.OnClickListener {

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
    setContentView(R.layout.activity_menu);

    Button hangmanButton = findViewById(R.id.menu_hangman);
    Button jumpingballButton = findViewById(R.id.menu_jumpingball);
    Button tilesButton = findViewById(R.id.menu_tiles);
    Button rankButton = findViewById(R.id.menu_rank);
    Button storeButton = findViewById(R.id.menu_store);
    Button settingButton = findViewById(R.id.menu_setting);
    Button logoutButton = findViewById(R.id.menu_logout);

    ImageView icon = findViewById(R.id.my_icon);
    TextView myGold = findViewById(R.id.gold_menu);
    TextView myItem = findViewById(R.id.magicalitem_menu);

    hangmanButton.setOnClickListener(this);
    jumpingballButton.setOnClickListener(this);
    tilesButton.setOnClickListener(this);
    rankButton.setOnClickListener(this);
    storeButton.setOnClickListener(this);
    settingButton.setOnClickListener(this);
    logoutButton.setOnClickListener(this);

    myGold.setText("Gold:" + loggedUser.getGold());
    myItem.setText("Resurrection Key:" + loggedUser.getResurrectionKey());

    setIcon(icon);
    setBackgroudColor();
  }

  /** The operations performed when button is clicked. */
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.menu_hangman:
        Intent intent_hangman = new Intent(getApplicationContext(), HangmanActivityStart.class);
        intent_hangman.putExtra("TYPE", 1);
        startActivity(intent_hangman);
        //                finish();
        break;
      case R.id.menu_jumpingball:
        Intent intent_jumpingball =
            new Intent(getApplicationContext(), JumpingBallActivityStart.class);
        intent_jumpingball.putExtra("TYPE", 2);
        startActivity(intent_jumpingball);
        //                finish();
        break;
      case R.id.menu_tiles:
        Intent intentTile = new Intent(getApplicationContext(), TilesActivityStart.class);
        intentTile.putExtra("TYPE", 3);
        startActivity(intentTile);
        //                finish();
        break;
      case R.id.menu_rank:
        Intent intentRank = new Intent(getApplicationContext(), RankingActivity.class);
        startActivity(intentRank);
        break;
      case R.id.menu_store:
        Intent intentStore = new Intent(getApplicationContext(), StoreActivity.class);
        startActivity(intentStore);
        break;
      case R.id.menu_setting:
        Intent intentSetting = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intentSetting);
        finish();
        break;
      case R.id.menu_logout:
        Intent intentLogout = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentLogout);
        finish();
        break;
    }
  }
}
