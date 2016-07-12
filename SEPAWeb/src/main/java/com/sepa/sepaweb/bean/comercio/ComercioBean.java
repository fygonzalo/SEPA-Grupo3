package com.sepa.sepaweb.bean.comercio;

import com.sepa.sepaweb.bean.AuthBean;
import com.sepa.sepaweb.dao.ComercioDao;
import com.sepa.sepaweb.dao.SucursalDao;
import com.sepa.sepaweb.dao.UsuarioDao;
import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Sucursal;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.model.Usuario;
import com.sepa.sepaweb.services.ComercioService;
import com.sepa.sepaweb.services.GeocodingService;
import com.sepa.sepaweb.services.SucursalService;
import com.sepa.sepaweb.util.GeocodingCode;
import com.sepa.sepaweb.util.GeocodingException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by fygon on 30/5/2016.
 */

@ManagedBean
@ViewScoped
public class ComercioBean {

    Comercio comercio;
    Ubicacion ubicacion;

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @EJB
    ComercioDao comercioDao;

    @EJB
    SucursalService sucursalService;

    @EJB
    SucursalDao sucursalDao;

    @EJB
    GeocodingService geocodingService;

    @EJB
    ComercioService comercioService;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    @PostConstruct
    public void init() {
        comercio = authBean.getComercio();
        ubicacion = new Ubicacion();
        ubicacion.setProvincia(comercio.getCiudad().getProvincia().getNombre());
        ubicacion.setCiudad(comercio.getCiudad().getNombre());
        ubicacion.setDireccion(comercio.getDireccion());
    }

    public void editarComercio() {
        try {
            ubicacion = geocodingService.getSingleResult(ubicacion);
        } catch (GeocodingException e) {
            FacesMessage facesMessage = new FacesMessage(e.getErrorDesc());
            switch (e.getGeocodingCode()) {
                case NULL_PROVINCIA:
                    FacesContext.getCurrentInstance().addMessage("formEditarComercio:inputProvincia", facesMessage);
                    break;
                case NULL_CIUDAD:
                    FacesContext.getCurrentInstance().addMessage("formEditarComercio:inputCiudad",facesMessage);
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage("formEditarComercio:inputDireccion", facesMessage);
                    break;
            }
            return;
        }
        comercioService.editarComercio(comercio, ubicacion);
    }

    public void bloquearComercio() {
        comercioService.deshabilitarComercio(comercio);
    }
}
