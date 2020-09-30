package com.example.sdkfacebook.Vistas;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MiAplicacion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


//on create

//        shareButton =  findViewById(R.id.sharing_button);
//        content = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("your-url"))
//                .build();
//
//        shareButton.setShareContent(content);
    }




}
