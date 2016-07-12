package com.sepa.sepaweb.bean;

import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.services.ComercioService;
import com.sepa.sepaweb.services.GeocodingService;
import com.sepa.sepaweb.util.GeocodingCode;
import com.sepa.sepaweb.util.GeocodingException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class RegistroComercioBean {

    Comercio comercio;
    Ubicacion sucursalUbicacion;

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public Ubicacion getSucursalUbicacion() {
        return sucursalUbicacion;
    }

    public void setSucursalUbicacion(Ubicacion sucursalUbicacion) {
        this.sucursalUbicacion = sucursalUbicacion;
    }

    @PostConstruct
    public void init() {
        comercio = new Comercio();
        sucursalUbicacion = new Ubicacion();
    }

    @EJB
    ComercioService comercioService;

    @EJB
    GeocodingService geocodingService;

    public void nuevoComercio() {

        try {
            sucursalUbicacion = geocodingService.getSingleResult(sucursalUbicacion);
            comercioService.nuevoComercio(comercio, sucursalUbicacion);
        } catch (GeocodingException e) {
            if(e.toString() == GeocodingCode.NULL_PROVINCIA.toString()) {
                FacesContext.getCurrentInstance().addMessage("registroComercio:inputProvincia", new FacesMessage(e.toString(), e.toString()));
            }
            else if (e.toString() == GeocodingCode.NULL_CIUDAD.toString()) {
                FacesContext.getCurrentInstance().addMessage("registroComercio:inputCiudad", new FacesMessage(e.toString()));
            }
            else {
                FacesContext.getCurrentInstance().addMessage("registroComercio:inputDireccion", new FacesMessage(e.toString()));
            }
            System.err.println(e.toString());

        }
    }
}
