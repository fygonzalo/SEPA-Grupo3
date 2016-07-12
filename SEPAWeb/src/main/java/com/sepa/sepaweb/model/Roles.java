/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sepa.sepaweb.model;

/**
 *
 * @author gonzalo
 */
public enum Roles {
    ADMINISTRADOR("ADMINISTRADOR"),
    COMERCIO("COMERCIO"),
    SUCURSAL("SUCURSAL");
    
    private final String rol;
    
    private Roles(String rol) {
        this.rol = rol;
    }
    
    @Override
    public String toString() {
        return this.rol;
    }
}
