package com.example.ray2017.maproutes;

import android.content.Intent;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class FacebookActivity extends AppCompatActivity {
    private LoginButton fbLoginBtn;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn){
            Toast.makeText(FacebookActivity.this, "You are Logged in", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(FacebookActivity.this, "You are not Logged in", Toast.LENGTH_LONG).show();
        }
        fbLoginBtn = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        fbLoginBtn.setReadPermissions(Arrays.asList(EMAIL));
        fbLoginBtn.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        setResult(RESULT_CANCELED);
                        finish();
                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
/*
    public void logoutFb(View view) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn) {
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(FacebookActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(FacebookActivity.this, "You are not logged in, please continue with Facebook to login", Toast.LENGTH_LONG).show();
        }
    }*/
}
