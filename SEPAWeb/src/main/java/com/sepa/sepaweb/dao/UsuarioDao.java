/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sepa.sepaweb.dao;

import com.sepa.sepaweb.model.Usuario;

/**
 *
 * @author gonzalo
 */
public interface UsuarioDao extends GenericDao<Usuario> {
    
    Usuario findByUsername(String username);

}
