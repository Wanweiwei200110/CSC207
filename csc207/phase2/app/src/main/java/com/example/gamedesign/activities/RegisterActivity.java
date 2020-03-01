package com.example.gamedesign.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamedesign.MainActivity;
import com.example.gamedesign.R;
import com.example.gamedesign.systemmanageinterface.OnRegisterListener;
import com.example.gamedesign.systemmanager.FileSaverLoader;
import com.example.gamedesign.systemmanager.UserRegisterPresenter;
import com.example.gamedesign.systemmanager.UserValidater;

import java.io.Serializable;

/** The activity displayed when user register with username and password. */
public class RegisterActivity extends AppCompatActivity
    implements View.OnClickListener, Serializable, OnRegisterListener {

  /** a string tag */
  private static final String TAG = "RegisterActivity";

  /** an edit text for input username */
  private EditText username;

  /** an edit text for input password */
  private EditText password;

  /** a UserRegisterPresenter */
  private UserRegisterPresenter userRegisterPresenter;

  /**
   * The operations performed while the activity is created.
   *
   * @param savedInstanceState instance state saved.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    Button registerButton = findViewById(R.id.register_r);
    Button backButton = findViewById(R.id.register_back);
    username = findViewById(R.id.register_enter_name);
    password = findViewById(R.id.register_enter_pw);
    ImageView rotated1 = findViewById(R.id.gear_register1);
    ImageView rotated2 = findViewById(R.id.gear_register2);
    rotated1.startAnimation(
        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation_slow));
    rotated2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation));

    backButton.setOnClickListener(this);
    registerButton.setOnClickListener(this);
    FileSaverLoader fileSaverLoader = new FileSaverLoader(getApplicationContext());

    UserValidater userValidater = new UserValidater(fileSaverLoader);
    userRegisterPresenter = new UserRegisterPresenter(this, userValidater);
    // firstUser();
  }

  /** The operations performed when button is clicked. */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.register_r:
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        validateRegistration(usernameInput, passwordInput);
        break;
      case R.id.register_back:
        Log.d(TAG, "onClick: register_back");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
        break;
    }
  }

  /**
   * check validate of username and password for registration
   *
   * @param usernameInput user's username
   * @param passwordInput user's password
   */
  @Override
  public void validateRegistration(String usernameInput, String passwordInput) {
    userRegisterPresenter.validateRegistration(usernameInput, passwordInput);
  }

  /** go to login activity */
  @Override
  public void goToLoginActivity() {
    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
    startActivity(intent);
    finish();
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

  /** The operations performed while the activity is destroyed. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    userRegisterPresenter = null;
  }
}
