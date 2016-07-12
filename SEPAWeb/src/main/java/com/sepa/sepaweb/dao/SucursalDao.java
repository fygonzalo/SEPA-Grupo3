package com.sepa.sepaweb.dao;

import com.sepa.sepaweb.model.Ciudad;
import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Provincia;
import com.sepa.sepaweb.model.Sucursal;

import java.util.List;

public interface SucursalDao extends GenericDao<Sucursal> {

    public List<Sucursal> findByComercioAndUbicacion(Comercio comercio, Provincia provincia, Ciudad ciudad, String direccion);

    void deshabilitarByComercio(Comercio comercio);
}
