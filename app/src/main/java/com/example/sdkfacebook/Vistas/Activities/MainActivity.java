package com.example.sdkfacebook.Vistas.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sdkfacebook.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISO_LOCALIZACION_BLUETOOTH=8051994;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     compruebaSesionIniciada();

    }

    private void compruebaSesionIniciada() {
        if(AccessToken.getCurrentAccessToken() == null && AccessToken.isDataAccessActive()){
            irLogin();
        }else{
            initAppBarToolBar();
            activaPermisos();
            //Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show();
        }
    }

    private void initAppBarToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(toolbar,navController);

        CardView cardView = findViewById(R.id.login_card);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void irLogin() {
        startActivity(
                new Intent(this, LoginActivity.class)
        );
        finish();
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        irLogin();
    }

    private void activaPermisos() {
        int permissionCheck = 0;
        permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");

        //En su momento también meter el permiso de setAlarma

        if (permissionCheck != 0) {
            //funciona api arriba de 23
            this.requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_PERMISO_LOCALIZACION_BLUETOOTH);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISO_LOCALIZACION_BLUETOOTH
                    :
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Ir logo dos", Toast.LENGTH_SHORT).show();
                    //intentIrLogoDos();
                    //finish();
                }  else {
                    Toast.makeText(this, getResources().getText(R.string.solicitar_permisos), Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

}