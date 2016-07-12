package com.sepa.sepaweb.bean.comercio;

import com.sepa.sepaweb.model.Sucursal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GestionarSucursalBean {

    Sucursal sucursal;

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
        System.out.println("Sucursal seleccionada: " + sucursal.getDireccion());
    }
}
