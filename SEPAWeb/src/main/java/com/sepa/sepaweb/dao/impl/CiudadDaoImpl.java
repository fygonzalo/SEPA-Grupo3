package com.sepa.sepaweb.dao.impl;

import com.sepa.sepaweb.dao.CiudadDao;
import com.sepa.sepaweb.model.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@Local(CiudadDao.class)
public class CiudadDaoImpl extends GenericDaoImpl<Ciudad> implements CiudadDao {

    @Override
    protected Class<Ciudad> getEntityClass() {
        return Ciudad.class;
    }

    @Override
    public Ciudad findByProvinciayNombre(Provincia provincia, String nombre) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ciudad> criteriaQuery = builder.createQuery(Ciudad.class);
        Root<Ciudad> c = criteriaQuery.from(Ciudad.class);
        criteriaQuery.select(c).where(builder.and(builder.equal(c.get(Ciudad_.provincia), provincia)),
                (builder.equal(c.get(Ciudad_.nombre), nombre)));
        TypedQuery query = em.createQuery(criteriaQuery);
        try {
            return (Ciudad) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Ciudad> findByComercioProvinciaSucursal(Comercio comercio, Provincia provincia) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ciudad> criteriaQuery = builder.createQuery(Ciudad.class);
        Root<Ciudad> c = criteriaQuery.from(Ciudad.class);
        Root<Sucursal> s = criteriaQuery.from(Sucursal.class);

        criteriaQuery.select(c).where(builder.and(
                builder.equal(s.get(Sucursal_.comercio), comercio),
                builder.equal(s.get(Sucursal_.provincia), provincia),
                builder.equal(c.get(Ciudad_.provincia), provincia)
        )).distinct(true).orderBy(builder.asc(c.get(Ciudad_.nombre)));

        Query query = em.createQuery(criteriaQuery);

        return (List<Ciudad>) query.getResultList();
    }
}
