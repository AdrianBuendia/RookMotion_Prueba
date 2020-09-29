package com.example.sdkfacebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

    }

    private void initViews() {
        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);
        //loginButton.setPublishPermissions();
        //loginButton.setPublishPermissions("pages_manage_posts");
        //LoginManager.getInstance().logInWithPublishPermissions(
          //      this,
           //     Arrays.asList("publish_actions"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                irActivityHome();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Operación cancelada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void irActivityHome() {
        startActivity(
                new Intent(this, MainActivity.class)
        );
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}