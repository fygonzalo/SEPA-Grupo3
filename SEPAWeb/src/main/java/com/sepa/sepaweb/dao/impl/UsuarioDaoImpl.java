/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sepa.sepaweb.dao.impl;

import com.sepa.sepaweb.dao.UsuarioDao;
import com.sepa.sepaweb.model.Usuario;
import com.sepa.sepaweb.model.Usuario_;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
@Local(UsuarioDao.class)
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> 
implements UsuarioDao {

    @Override
    protected Class<Usuario> getEntityClass() {
        return Usuario.class;
    }

    @Override
    public Usuario findByUsername(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> u = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(u).where(builder.equal(u.get(Usuario_.username), username));
        Query query = em.createQuery(criteriaQuery);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
