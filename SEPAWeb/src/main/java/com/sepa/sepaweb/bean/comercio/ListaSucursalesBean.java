package com.sepa.sepaweb.bean.comercio;

import com.sepa.sepaweb.bean.AuthBean;
import com.sepa.sepaweb.dao.CiudadDao;
import com.sepa.sepaweb.dao.ProvinciaDao;
import com.sepa.sepaweb.dao.SucursalDao;
import com.sepa.sepaweb.model.Ciudad;
import com.sepa.sepaweb.model.Provincia;
import com.sepa.sepaweb.model.Sucursal;
import com.sepa.sepaweb.services.SucursalService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListaSucursalesBean {

    List<Sucursal> sucursalList;
    Provincia provincia;
    List<Provincia> provinciaList;
    Ciudad ciudad;
    List<Ciudad> ciudadList;
    String direccion;

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @EJB
    SucursalDao sucursalDao;

    @EJB
    ProvinciaDao provinciaDao;

    @EJB
    CiudadDao ciudadDao;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    @PostConstruct
    public void init() {
        provincia = null;
        ciudad = null;
        direccion = "";
        provinciaList = provinciaDao.findByComercioSucursales(authBean.getComercio());
        filtrarSucursales();
    }

    public void listarCiudades(AjaxBehaviorEvent event) {
        if (provincia != null) {
            ciudadList = ciudadDao.findByComercioProvinciaSucursal(authBean.getComercio(), provincia);
        }
        else {
            ciudadList = null;
        }
    }

    public void filtrarSucursales() {
        sucursalList = sucursalDao.findByComercioAndUbicacion(authBean.getComercio(), provincia, ciudad, direccion);
    }

    public void filtrarSucursales(AjaxBehaviorEvent event) {
        filtrarSucursales();
    }
}
