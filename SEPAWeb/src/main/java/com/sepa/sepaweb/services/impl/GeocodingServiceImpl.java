package com.sepa.sepaweb.services.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.model.constraints.Geocoding;
import com.sepa.sepaweb.services.GeocodingService;
import com.sepa.sepaweb.util.GeocodingException;
import com.sepa.sepaweb.util.GeocodingCode;

import javax.ejb.Local;
import javax.ejb.Stateless;

import javax.inject.Named;

@Stateless
@Local(GeocodingService.class)
public class GeocodingServiceImpl implements GeocodingService {

    static final GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBhawo6y9mH109LgAQES93mJk52SDT_yw8");

    public Ubicacion getSingleResult(Ubicacion ubicacion) throws GeocodingException {
        if (ubicacion.getProvincia() == null || ubicacion.getProvincia().equals("")) {
            throw new GeocodingException(GeocodingCode.NULL_PROVINCIA);
        } else if (ubicacion.getCiudad() == null || ubicacion.getCiudad().equals("")) {
            throw new GeocodingException(GeocodingCode.NULL_CIUDAD);
        } else if (ubicacion.getDireccion() == null || ubicacion.getDireccion().equals("")) {
            throw new GeocodingException(GeocodingCode.NULL_DIRECCION);
        }

        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.newRequest(context).address(ubicacion.getDireccion()).components(
                    ComponentFilter.locality(ubicacion.getCiudad()),
                    ComponentFilter.administrativeArea(ubicacion.getProvincia()),
                    ComponentFilter.country("AR")
            ).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (results.length == 0) {
            throw new GeocodingException(GeocodingCode.NO_RESULT);
        } else if (results.length > 1) {
            throw new GeocodingException(GeocodingCode.MANY_RESULTS);
        } else if (results[0].partialMatch) {
            throw new GeocodingException(GeocodingCode.PARTIAL_MATCH);
        }

        ubicacion.setLatitud(results[0].geometry.location.lat);
        ubicacion.setLongitud(results[0].geometry.location.lng);

        return ubicacion;
    }
}
