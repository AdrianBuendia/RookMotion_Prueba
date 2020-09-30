package com.example.sdkfacebook.Vistas.Fragmentos;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdkfacebook.Model.POJOS.FoundDevices;
import com.example.sdkfacebook.R;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class BluetoothDiscoveryFragment extends Fragment {

    private View root;
    private BluetoothAdapter btAdapter = null ;
    private Boolean verificarReceiver; //Checar si se puede quitar al crear a devicesBTFoundB... en otro metodo que no sea onCreate
    private DevicesBTFoundBroadcastReceiver receiver; //Esta hacerla final despues
    private TextView textoLogo;
    private ProgressBar progressBar;
    private TextView listaDevices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_bluetooth_discovery, container, false);

        initComponents();
        return root;
    }


    private void initComponents() {
        btAdapter=BluetoothAdapter.getDefaultAdapter();
        receiver =new DevicesBTFoundBroadcastReceiver();
        textoLogo = root.findViewById(R.id.texgo_buscar);
        listaDevices = root.findViewById(R.id.text_lista_devices);
        progressBar = root.findViewById(R.id.progress_circular);
        verificarReceiver = false;

    }


    @Override
    public void onResume() {
        super.onResume();
        verificarEstadoBT();
    }


    /**
     * Comprueba que el Bluetooth
     * esta activado
     */
    private void verificarEstadoBT() {
        if(btAdapter==null) {
            Toast.makeText(getContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (!btAdapter.isEnabled()) {
                lanzarDialogoActivaBluetooth();
            }else{
                //Toast.makeText(getContext(), "es necesario encender conexión Bluetooth", Toast.LENGTH_LONG).show();
                scannerBT();
            }
        }
    }

    private void lanzarDialogoActivaBluetooth() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        int CODIGO_RESPUESTA = 1;
        startActivityForResult(enableBtIntent, CODIGO_RESPUESTA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode >= 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                scannerBT();
            } else {
                verificarReceiver = true;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Para deshabilitar BroadCastReceiver

        if(!verificarReceiver) {
            getActivity().unregisterReceiver(receiver); //Log.e("Hereeee","OnDestroy, si Receiver");
        }
        cancelarBusquedaBT();
    }

    public void scannerBT(){
        if(btAdapter != null && btAdapter.isEnabled()) {
            cancelarBusquedaBT();
            hiloStartDiscoverBluetoothsDevices();
            temporizadorCierraActivity();
        }
    }

    private void hiloStartDiscoverBluetoothsDevices() {

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                getActivity().registerReceiver(receiver, filter);

                btAdapter.startDiscovery();

                showLoadingViews();
            }

        });
    }


    private void showLoadingViews(){
        textoLogo.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        listaDevices.setVisibility(View.INVISIBLE);
        animarObjeto(textoLogo);
    }

    private void animarObjeto(View view){
        final AnimatorSet bouncer = new AnimatorSet();
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationX", 0f, 1000f);
        anim.setDuration(1000);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "translationX", -1000f, 0f);
        anim2.setDuration(1000);
        ObjectAnimator animIntermedio = ObjectAnimator.ofFloat(view, "translationX", 0f, 0f);
        animIntermedio.setDuration(1000);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "translationX", 0f, -1000f);
        anim3.setDuration(1000);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f);
        anim4.setDuration(1000);

        bouncer.playSequentially(anim, anim2, animIntermedio, anim3, anim4);
        bouncer.addListener(listerneRpeat(bouncer));
        bouncer.start();
    }

    private Animator.AnimatorListener listerneRpeat(final AnimatorSet bouncer ){
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                bouncer.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };
    }

    private void temporizadorCierraActivity() {
        int DOCE_SEGUNDOS = 12000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                cancelarBusquedaBT();
                temporizadorTerminarActivity();

            }
        }, DOCE_SEGUNDOS);
    }

    private void temporizadorTerminarActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarResultados();
            }
        },500);
    }

    private void mostrarResultados() {
        //Arreglo


        listaDevices.setVisibility(View.VISIBLE);

        if(DevicesBTFoundBroadcastReceiver.foundDevicesBT.size() <= 0){
            listaDevices.setText("no se encontro ningún dispositivo");
            esconderVistas();
        }else{
            //Log.e("dsds",DevicesBTFoundBroadcastReceiver.foundDevicesBT.get(0).getNombreDevice());
            ArrayList<FoundDevices> dispositivosEncontrados = DevicesBTFoundBroadcastReceiver.foundDevicesBT;
            for(FoundDevices device:dispositivosEncontrados){
                String content="";
                content += "Nombre del Dispositivo:"+device.getNombreDevice()+"\n";
                content += "Dirección MAC:"+device.getDireccionMAC()+"\n";

                if(device.getParametroVinculacion()==1) content += "Vinculado"+"\n";
                else content += "No vinculado \n";

                listaDevices.append(content+"\n");

            }
            esconderVistas();
        }
    }

    private void esconderVistas() {
        textoLogo.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }


    private void cancelarBusquedaBT() {
        if(btAdapter.isDiscovering()){
            //Log.e("OnDestroy","Esta buscando");
            btAdapter.cancelDiscovery();
//            unregisterReceiver(receiver);

        }

    }

    /**
     * Descubre nuevos dispositivos
     */
    public static class DevicesBTFoundBroadcastReceiver extends BroadcastReceiver {


        public static ArrayList<FoundDevices> foundDevicesBT =  new ArrayList<FoundDevices>();

        public ArrayList<FoundDevices> getFoundDevicesBT() {
            return foundDevicesBT;
        }

        public void setFoundDevicesBT(ArrayList<FoundDevices> foundDevicesBT) {
            this.foundDevicesBT = foundDevicesBT;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            BluetoothAdapter.getDefaultAdapter();

            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address


                //Log.e("DEviceNAme",deviceName);
                if (deviceName != null && deviceHardwareAddress != null ){

                    if(getDeviceMACVinculada( BluetoothAdapter.getDefaultAdapter(),deviceHardwareAddress) ){
                        Toast.makeText(context, "Dispositivo encontrado y vinculado "+deviceName,Toast.LENGTH_SHORT).show();
                        Log.e("Vinculado","Dispositivo encontrado y vinculado "+deviceName);
                        //Dispositivo Vinculado y listo para conectaar
                        //irMainActivity(context,deviceHardwareAddress);
                        foundDevicesBT.add(
                                new FoundDevices(deviceName,deviceHardwareAddress,1)
                        );
                    }else{

                        Toast.makeText(context, "Dispositivo encontrado NO vinculado",Toast.LENGTH_SHORT).show();
                        Log.e("Vinculado","Dispositivo encontrado NO vinculado"+deviceName);
                        //Toast.makeText(context, R.string.no_vinculado,Toast.LENGTH_LONG).show();


                        foundDevicesBT.add(
                                new FoundDevices(deviceName,deviceHardwareAddress,0)
                        );
                    }

                } else {

                    Toast.makeText(context, "Device oculto",Toast.LENGTH_LONG).show();
                }

            }

        }

        private boolean getDeviceMACVinculada(BluetoothAdapter bluetoothAdapter, String deviceHardwareAddress){

            boolean var = false;

            for (BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()) {
                if (bluetoothDevice.getAddress().equals(deviceHardwareAddress)) {
                    var = true;
                    break;
                }
            }

            return var;
        }

    }



}