package com.example.sdkfacebook.Vistas.Fragmentos;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sdkfacebook.R;

public class HomeFragment extends Fragment {

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false);

        initViews();

        return root;
    }

    private void initViews() {
        CardView cardBluetooth = root.findViewById(R.id.boton_bluetooth);
        CardView cardAPI = root.findViewById(R.id.api_busca_cancion);
        CardView cardFoto= root.findViewById(R.id.camara);

        cardBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_bluetoothDiscoveryFragment);
            }
        });

        cardAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_paraPeticionesFragment);
            }
        });

        cardFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_camaraFragment);
            }
        });
    }

}