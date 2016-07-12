package com.sepa.sepaweb.services;


import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.model.constraints.Geocoding;
import com.sepa.sepaweb.util.GeocodingException;

import javax.validation.Valid;

public interface ComercioService {

    void nuevoComercio(Comercio comercio, Ubicacion ubicacion) throws GeocodingException;

    void editarComercio(Comercio comercio, Ubicacion ubicacion);

    void deshabilitarComercio(Comercio comercio);
}
