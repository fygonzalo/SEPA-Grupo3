package com.sepa.sepaweb.services;

import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.util.GeocodingException;

public interface GeocodingService {

    public Ubicacion getSingleResult(Ubicacion ubicacion) throws GeocodingException;

}
