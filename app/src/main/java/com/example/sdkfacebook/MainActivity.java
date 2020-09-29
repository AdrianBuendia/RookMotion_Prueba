package com.example.sdkfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Comprueba si la secion esta iniciada
        compruebaSesionIniciada();


    }

    private void initShareDialog() {
        callbackManager = CallbackManager.Factory.create();
        final ShareDialog shareDialog = new ShareDialog(this);

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.deezer.com/mx/track/12209320"))
                    .build();
            shareDialog.show(linkContent);
        }
        // this part is optional
//        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//            @Override
//            public void onSuccess(Sharer.Result result) {
//
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void compruebaSesionIniciada() {
        if(AccessToken.getCurrentAccessToken() == null ){
            irLogin();
        }else initViews();
    }


    private void initViews() {

        Button buttonShared = findViewById(R.id.compartir);
        buttonShared.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        compartirFacebook();
                    }
                }
        );
    }

    private void compartirFacebook() {
//        LoginManager.getInstance().logInWithReadPermissions(
//                this,
//                Arrays.asList("pages_manage_posts"));
//
//
//        ShareLinkContent content = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("https://www.deezer.com/mx/track/1092225832"))
//                .build();

        Toast.makeText(this, "Aquiii", Toast.LENGTH_SHORT).show();



        initShareDialog();




    }

    private void irLogin() {
        startActivity(
                new Intent(this,LoginActivity.class)
        );
        finish();
    }

    public void onClick(View view) {
        //Cierra Secion
        LoginManager.getInstance().logOut();
        irLogin();
    }
}