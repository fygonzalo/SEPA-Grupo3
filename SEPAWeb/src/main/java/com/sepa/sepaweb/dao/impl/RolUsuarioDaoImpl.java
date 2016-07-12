/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sepa.sepaweb.dao.impl;

import com.sepa.sepaweb.model.RolUsuario;
import com.sepa.sepaweb.dao.RolUsuarioDao;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(RolUsuarioDao.class)
public class RolUsuarioDaoImpl extends GenericDaoImpl<RolUsuario>
implements RolUsuarioDao {

    @Override
    protected Class<RolUsuario> getEntityClass() {
        return RolUsuario.class;
    }
}
