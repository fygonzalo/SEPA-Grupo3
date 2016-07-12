package com.sepa.sepaweb.dao;

import com.sepa.sepaweb.model.Ciudad;
import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Provincia;

import java.util.List;

public interface CiudadDao extends GenericDao<Ciudad> {

    Ciudad findByProvinciayNombre(Provincia provincia, String nombre);

    List<Ciudad> findByComercioProvinciaSucursal(Comercio comercio, Provincia provincia);
}
