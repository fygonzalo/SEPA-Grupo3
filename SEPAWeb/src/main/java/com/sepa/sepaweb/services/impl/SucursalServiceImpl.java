package com.sepa.sepaweb.services.impl;

import com.sepa.sepaweb.dao.CiudadDao;
import com.sepa.sepaweb.dao.ProvinciaDao;
import com.sepa.sepaweb.dao.SucursalDao;
import com.sepa.sepaweb.dao.UsuarioDao;
import com.sepa.sepaweb.model.*;
import com.sepa.sepaweb.services.GeocodingService;
import com.sepa.sepaweb.services.SucursalService;
import com.sepa.sepaweb.services.UsuarioService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by fygon on 30/5/2016.
 */
@Stateless
@Local(SucursalService.class)
public class SucursalServiceImpl implements SucursalService {

    @EJB
    ProvinciaDao provinciaDao;

    @EJB
    CiudadDao ciudadDao;

    @EJB
    GeocodingService geocodingService;

    @EJB
    UsuarioService usuarioService;

    @EJB
    SucursalDao sucursalDao;

    @Override
    public void nuevaSucursal(Sucursal sucursal, Ubicacion ubicacion, Comercio comercio) {
        Provincia provincia = provinciaDao.findByNombre(ubicacion.getProvincia());
        if (provincia == null) {
            provincia = new Provincia();
            provincia.setNombre(ubicacion.getProvincia());
            provinciaDao.persist(provincia);
        }

        Ciudad ciudad = ciudadDao.findByProvinciayNombre(provincia, ubicacion.getCiudad());
        if (ciudad == null) {
            ciudad = new Ciudad();
            ciudad.setNombre(ubicacion.getCiudad());
            ciudad.setProvincia(provincia);
            ciudadDao.persist(ciudad);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(comercio.getCuit() + comercio.getSucursales().size());
        usuarioService.nuevoUsuario(usuario, Roles.SUCURSAL);

        sucursal.setLatitud(ubicacion.getLatitud());
        sucursal.setLongitud(ubicacion.getLongitud());
        sucursal.setProvincia(provincia);
        sucursal.setCiudad(ciudad);
        sucursal.setDireccion(ubicacion.getDireccion());
        sucursal.setUsuario(usuario);
        sucursal.setComercio(comercio);

        sucursalDao.persist(sucursal);
    }
    
}
