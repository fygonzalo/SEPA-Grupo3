package com.sepa.sepaweb.services;

import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Sucursal;
import com.sepa.sepaweb.model.Ubicacion;

/**
 * Created by fygon on 30/5/2016.
 */
public interface SucursalService {

    void nuevaSucursal(Sucursal sucursal, Ubicacion ubicacion, Comercio comercio);

}
