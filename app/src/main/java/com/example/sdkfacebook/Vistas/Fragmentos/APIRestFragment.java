package com.example.sdkfacebook.Vistas.Fragmentos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdkfacebook.Model.POJOS.Cancion;
import com.example.sdkfacebook.Model.POJOS.DataResouesta;
import com.example.sdkfacebook.R;
import com.example.sdkfacebook.ViewModels.MyViewModel;
import com.example.sdkfacebook.Vistas.Adaptadores.AdaptadorRecyclerView;
import com.facebook.CallbackManager;

import java.util.ArrayList;

public class APIRestFragment extends Fragment {

    private CallbackManager callbackManager;
    private MyViewModel viewModel;
    private RecyclerView recyclerView;
    private AdaptadorRecyclerView adaptadorRecyclerView;
    private TextView conexionText;

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_api_rest, container, false);

        initViews();

        return root;
    }

    private void initViews(){
        SearchView searchView = root.findViewById(R.id.search_fragment);
        searchView.setIconified(false);
        conexionText = root.findViewById(R.id.text_iternet);

        callbackManager = CallbackManager.Factory.create();
        initRecycler();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.e("submit",s);

                lanzarBusquedaSafe(s);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("Change",s);

                lanzarBusquedaSafe(s);

                return true;
            }
        });

    }

    private void lanzarBusquedaSafe(String s) {
        if(buscarCancionConexionSegura()){
            recyclerView.setVisibility(View.VISIBLE);
            conexionText.setVisibility(View.INVISIBLE);
            buscarCancion(s);
        }else{
            recyclerView.setVisibility(View.INVISIBLE);
            conexionText.setVisibility(View.VISIBLE);
        }
    }

    private void buscarCancion(String entradaTexto) {

        String search_term;
        search_term = limpiarCadenaEspaciosRetornosCarro( entradaTexto );

        viewModel.getNewsRepository( search_term ).observe(this, new Observer<DataResouesta>() {
            @Override
            public void onChanged(DataResouesta dataResouesta) {

                if(dataResouesta != null ){
                    setCancionesAdapter(
                            dataResouesta.getData()
                    );
                }else Toast.makeText(getContext(), "Verificar conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean buscarCancionConexionSegura(){
        ConnectivityManager cm =
                (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private String limpiarCadenaEspaciosRetornosCarro(String cadena) {

        // Elimina espacios delante y detrás
        cadena.trim();

        // Elimina espacios, tabuladores, retornos
        cadena.replaceAll("//s","%20");

        return cadena;

    }

    public void initRecycler(){
        recyclerView = root.findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        initAdapterRecycler();
    }

    private void initAdapterRecycler(){
        adaptadorRecyclerView = new AdaptadorRecyclerView(this,callbackManager, new ArrayList<Cancion>());
        recyclerView.setAdapter(adaptadorRecyclerView);
    }

    private void setCancionesAdapter(ArrayList<Cancion> canciones){
        adaptadorRecyclerView.setCanciones(canciones);
    }


    @Override
    public void onStart() {
        super.onStart();
        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
    }

}