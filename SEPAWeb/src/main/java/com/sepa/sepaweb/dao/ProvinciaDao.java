package com.sepa.sepaweb.dao;

import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Provincia;

import java.util.List;

public interface ProvinciaDao extends GenericDao<Provincia> {

    Provincia findByNombre(String nombre);

    List<Provincia> findByComercioSucursales(Comercio comercio);

}
