package com.sepa.sepaweb.services.impl;

import com.sepa.sepaweb.dao.CiudadDao;
import com.sepa.sepaweb.dao.ComercioDao;
import com.sepa.sepaweb.dao.ProvinciaDao;
import com.sepa.sepaweb.dao.SucursalDao;
import com.sepa.sepaweb.model.Ciudad;
import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Provincia;
import com.sepa.sepaweb.model.Roles;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.model.Usuario;
import com.sepa.sepaweb.model.constraints.Geocoding;
import com.sepa.sepaweb.services.ComercioService;
import com.sepa.sepaweb.services.GeocodingService;
import com.sepa.sepaweb.services.SucursalService;
import com.sepa.sepaweb.services.UsuarioService;
import com.sepa.sepaweb.util.GeocodingException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.validation.Valid;

@Stateless
@Local(ComercioService.class)
public class ComercioServiceImpl implements ComercioService {

    @EJB ProvinciaDao provinciaDao;

    @EJB CiudadDao ciudadDao;
    
    @EJB UsuarioService usuarioService;
    
    @EJB ComercioDao comercioDao;

    @EJB
    SucursalDao sucursalDao;

    @EJB
    GeocodingService geocodingService;
    
    @Override
    public void nuevoComercio(Comercio comercio, Ubicacion ubicacion) {

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
        usuario.setUsername(comercio.getCuit());
        usuarioService.nuevoUsuario(usuario, Roles.COMERCIO);
        
        comercio.setCiudad(ciudad);
        comercio.setDireccion(ubicacion.getDireccion());
        comercio.setUsuario(usuario);
        comercioDao.persist(comercio);
    }

    public void editarComercio(Comercio comercio, Ubicacion ubicacion) {

        Provincia provincia = provinciaDao.findByNombre(ubicacion.getCiudad());
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

        comercio.setCiudad(ciudad);
        comercio.setDireccion(ubicacion.getDireccion());
        comercioDao.merge(comercio);
    }

    @Override
    public void deshabilitarComercio(Comercio comercio) {
        comercio.setHabilitado(false);
        sucursalDao.deshabilitarByComercio(comercio);
        comercioDao.merge(comercio);
    }
}
