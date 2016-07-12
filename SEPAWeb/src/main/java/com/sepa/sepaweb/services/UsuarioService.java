/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sepa.sepaweb.services;

import com.sepa.sepaweb.model.Roles;
import com.sepa.sepaweb.model.Usuario;

/**
 *
 * @author gonzalo
 */
public interface UsuarioService {
    
    void nuevoUsuario(Usuario usuario, Roles rol);
    
}
