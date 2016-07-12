package com.sepa.sepaweb.model.constraints.validator;

import com.google.maps.model.GeocodingResult;
import com.sepa.sepaweb.model.Ubicacion;
import com.sepa.sepaweb.model.constraints.Geocoding;
import com.sepa.sepaweb.services.GeocodingService;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeocodingValidator implements ConstraintValidator<Geocoding, Ubicacion> {
    @Override
    public void initialize(Geocoding constraint) {

    }
    
    @EJB
    GeocodingService geocodingService;

    @Override
    public boolean isValid(Ubicacion ubicacion, ConstraintValidatorContext context) {

        // Si falta la ciudad o provincia no validamos
        if (ubicacion.getCiudad() == null || ubicacion.getProvincia() == null) return false;

        GeocodingResult[] results = null;

        if (results.length == 0) return false; // No encontramos ningun resultado
        else if (results.length > 1) return false; // Encontramos mas de un resultado
        else if (results[0].partialMatch) return false; // El resultado no es preciso
        
        return true;
    }
}
