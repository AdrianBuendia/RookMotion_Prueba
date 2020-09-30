package com.example.sdkfacebook.Vistas.Fragmentos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sdkfacebook.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import static android.app.Activity.RESULT_OK;


public class CamaraFragment extends Fragment {

    private View root;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_camara, container, false);

        dispatchTakePictureIntent();

        return root;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //imageView.setImageBitmap(imageBitmap);
            compartirLink(imageBitmap);

        }
    }

    private void compartirLink(Bitmap imageBitmap) {

        callbackManager = CallbackManager.Factory.create();
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(imageBitmap)
                .build();

        final ShareDialog shareDialog = new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(getContext(), "Compartido", Toast.LENGTH_SHORT).show();
                dispatchTakePictureIntent();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), "fallo", Toast.LENGTH_SHORT).show();
            }
        });


        if (ShareDialog.canShow(ShareLinkContent.class)) {
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            shareDialog.show( content );

        }
    }


}