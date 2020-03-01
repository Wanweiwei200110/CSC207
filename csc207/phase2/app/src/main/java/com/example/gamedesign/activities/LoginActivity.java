package com.example.gamedesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamedesign.R;
import com.example.gamedesign.accountmanager.User;
import com.example.gamedesign.systemmanageinterface.OnLoginListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserLoginPresenter;
import com.example.gamedesign.systemmanager.UserValidater;

/** The activity displayed when user login with username and password */
public class LoginActivity extends AppCompatActivity
    implements View.OnClickListener, OnLoginListener {

  /** a string tag */
  private static final String TAG = "LoginActivity";
  /** a user */
  public User user;
  /** an edit text for input username */
  private EditText username;
  /** an edit text for input password */
  private EditText password;
  /** a checkbox for remember the user's username and password */
  private CheckBox rememberMe;

  /** a UserLoginPresenter */
  private UserLoginPresenter userLoginPresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    Button loginButton = findViewById(R.id.login_l);
    Button backButton = findViewById(R.id.login_back);
    username = findViewById(R.id.login_enter_name);
    password = findViewById(R.id.login_enter_pw);
    rememberMe = findViewById(R.id.login_checkBox);
    ImageView rotated1 = findViewById(R.id.gear_login1);
    ImageView rotated2 = findViewById(R.id.gear_login2);
    rotated1.startAnimation(
        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation_slow));
    rotated2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation));

    backButton.setOnClickListener(this);
    loginButton.setOnClickListener(this);

    FileSaverLoader fileSaverLoader = new FileSaverLoader(getApplicationContext());

    UserValidater userValidater = new UserValidater(fileSaverLoader);
    userLoginPresenter = new UserLoginPresenter(this, userValidater);
    initRememberMe();
  }

  /** The operations performed while the activity is destroyed. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    userLoginPresenter = null;
  }

  /** show username and password after check the remember me box */
  @Override
  public void showRememberMe(String username, String password) {
    this.username.append(username);
    this.password.append(password);
    rememberMe.setChecked(true);
  }

  /** The operations performed when button is clicked. */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.login_l:
        Log.d(TAG, "onClick: login_l");
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        validatePassword(usernameInput, passwordInput, rememberMe.isChecked());
        break;
      case R.id.login_back:
        Log.d(TAG, "onClick: login_back");
        finish();
        break;
    }
  }

  /** initialize remember me */
  @Override
  public void initRememberMe() {
    userLoginPresenter.initRememberMe();
  }

  /**
   * check validate of password
   *
   * @param usernameInput user's username
   * @param passwordInput user's password
   * @param rememberMe boolean for remember username and password
   */
  @Override
  public void validatePassword(String usernameInput, String passwordInput, boolean rememberMe) {
    userLoginPresenter.validatePassword(usernameInput, passwordInput, rememberMe);
  }

  /** go to menu activity */
  @Override
  public void goToMenuActivity() {
    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
    startActivity(intent);
  }

  /**
   * check the input is valid or not
   *
   * @param validateResult a String validateResult
   */
  @Override
  public void notValidInput(String validateResult) {
    new AlertDialog.Builder(this)
        .setTitle("Oops!")
        .setMessage(validateResult)
        .setPositiveButton(android.R.string.yes, null)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
  }
}
