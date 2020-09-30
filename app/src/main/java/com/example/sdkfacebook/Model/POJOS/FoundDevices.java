package com.example.sdkfacebook.Model.POJOS;

public class FoundDevices {
    private String nombreDevice;
    private String direccionMAC;
    private int parametroVinculacion;

    public FoundDevices(String nombreDevice, String direccionMAC, int parametroVinculacion) {
        this.nombreDevice = nombreDevice;
        this.direccionMAC = direccionMAC;
        this.parametroVinculacion = parametroVinculacion;
    }

    public String getNombreDevice() {
        return nombreDevice;
    }

    public void setNombreDevice(String nombreDevice) {
        this.nombreDevice = nombreDevice;
    }

    public String getDireccionMAC() {
        return direccionMAC;
    }

    public void setDireccionMAC(String direccionMAC) {
        this.direccionMAC = direccionMAC;
    }

    public int getParametroVinculacion() {
        return parametroVinculacion;
    }

    public void setParametroVinculacion(int parametroVinculacion) {
        this.parametroVinculacion = parametroVinculacion;
    }
}
