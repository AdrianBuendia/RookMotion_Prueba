package com.example.sdkfacebook.Vistas.Adaptadores;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sdkfacebook.Model.POJOS.Cancion;
import com.example.sdkfacebook.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;


public class AdaptadorRecyclerView extends RecyclerView.Adapter<AdaptadorRecyclerView.MyViewHolder> {

    private ArrayList<Cancion> canciones;
    private CallbackManager callbackManager;
    private Fragment fragment;

    public AdaptadorRecyclerView(Fragment fragment,CallbackManager callbackManager, ArrayList<Cancion> canciones) {
        this.fragment = fragment;
        this.callbackManager = callbackManager;
        this.canciones = canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }

    @NonNull
    @Override
    public AdaptadorRecyclerView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdaptadorRecyclerView.MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String cancionTitle = canciones.get(position).getTitle();
        String imagenAlbumURL = canciones.get(position).getAlbum().getCover();
        String  artistaName= canciones.get(position).getArtist().getName();
        String cadenaCompleta = artistaName+"-"+cancionTitle;
        String link = canciones.get(position).getLink();

        //ImageView
        Glide.with(holder.carta).load(imagenAlbumURL).into(holder.imageView);

        holder.artistaCancion.setText(cadenaCompleta);
        holder.carta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Compartir link
                compartirLink(link,view);
            }
        });

    }



    private void compartirLink(String link,View view) {

        callbackManager = CallbackManager.Factory.create();
        final ShareDialog shareDialog = new ShareDialog(fragment);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(view.getContext(), "Compartido", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(view.getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(view.getContext(), "fallo", Toast.LENGTH_SHORT).show();
            }
        });

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(link))
                    .build();
            shareDialog.show(linkContent);

        }

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView carta;
        private TextView artistaCancion;
        private ImageView imageView;

        public MyViewHolder(View v) {
            super(v);
            carta = v.findViewById(R.id.carta);
            artistaCancion = v.findViewById(R.id.texto_artista_cancion);
            imageView = v.findViewById(R.id.image_view);
        }

    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public CallbackManager getCallbackManager() {

        return callbackManager;
    }

    public void setCallbackManager(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }
}

