package com.sepa.sepaweb.bean.comercio;

import com.sepa.sepaweb.bean.AuthBean;
import com.sepa.sepaweb.dao.ComercioDao;
import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Sucursal;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.services.GeocodingService;
import com.sepa.sepaweb.services.SucursalService;
import com.sepa.sepaweb.util.GeocodingException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ViewScoped
@ManagedBean
public class RegistrarSucursalBean {

    Sucursal sucursal;
    Ubicacion ubicacion;

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @EJB
    SucursalService sucursalService;

    @EJB
    GeocodingService geocodingService;

    @EJB
    ComercioDao comercioDao;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    @ManagedProperty(value = "#{listaSucursalesBean}")
    private ListaSucursalesBean listaSucursalesBean;

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    public void setListaSucursalesBean(ListaSucursalesBean listaSucursalesBean) {
        this.listaSucursalesBean = listaSucursalesBean;
    }

    @PostConstruct
    public void init() {
        sucursal = new Sucursal();
        ubicacion = new Ubicacion();
    }

    public void registrarSucursal() {
        try {
            ubicacion = geocodingService.getSingleResult(ubicacion);
        } catch (GeocodingException e) {
            FacesMessage facesMessage = new FacesMessage(e.getErrorDesc());
            switch (e.getGeocodingCode()) {
                case NULL_PROVINCIA:
                    FacesContext.getCurrentInstance().addMessage("formRegistrarSucursal:inputProvincia", facesMessage);
                    break;
                case NULL_CIUDAD:
                    FacesContext.getCurrentInstance().addMessage("formRegistrarSucursal:inputCiudad", facesMessage);
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage("formRegistrarSucursal:inputDireccion", facesMessage);
                    break;
            }
            return;
        }
        Comercio comercio = comercioDao.findByUsuario(authBean.getUsuario());
        sucursalService.nuevaSucursal(sucursal, ubicacion, comercio);
        listaSucursalesBean.filtrarSucursales();
    }
}
