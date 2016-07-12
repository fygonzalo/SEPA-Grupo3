/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sepa.sepaweb.services.impl;

import com.sepa.sepaweb.dao.RolUsuarioDao;
import com.sepa.sepaweb.dao.UsuarioDao;
import com.sepa.sepaweb.model.RolUsuario;
import com.sepa.sepaweb.model.Roles;
import com.sepa.sepaweb.model.Usuario;
import com.sepa.sepaweb.services.UsuarioService;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.apache.commons.lang3.RandomStringUtils;

@Stateless
@Local(UsuarioService.class)
public class UsuarioServiceImpl implements UsuarioService {
    
    @EJB
    UsuarioDao usuarioDao;
    
    @EJB
    RolUsuarioDao rolUsuarioDao;
    
    @Override
    public void nuevoUsuario(Usuario usuario, Roles rol) {
        usuario.setPassword(RandomStringUtils.randomAlphanumeric(12));
        usuarioDao.persist(usuario);
        
        RolUsuario rolUsuario = new RolUsuario();
        rolUsuario.setUsuario(usuario);
        rolUsuario.setRol(rol.toString());
        rolUsuarioDao.persist(rolUsuario);
    }
    
}
