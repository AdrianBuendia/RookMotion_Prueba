package com.example.sdkfacebook;

import android.app.Application;
import android.net.Uri;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;

public class MiAplicacion extends Application {

    private ShareButton shareButton;
    private ShareLinkContent content;


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
