/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sepa.sepaweb.dao.impl;

import com.sepa.sepaweb.dao.ComercioDao;
import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Comercio_;
import com.sepa.sepaweb.model.Usuario;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
@Local(ComercioDao.class)
public class ComercioDaoImpl extends GenericDaoImpl<Comercio>
implements ComercioDao {

    @Override
    protected Class<Comercio> getEntityClass() {
        return Comercio.class;
    }

    @Override
    public Comercio findByUsuario(Usuario usuario) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Comercio> criteriaQuery = builder.createQuery(Comercio.class);
        Root<Comercio> c = criteriaQuery.from(Comercio.class);
        criteriaQuery.select(c).where(builder.equal(c.get(Comercio_.usuario), usuario));
        Query query = em.createQuery(criteriaQuery);
        try {
            return (Comercio) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
