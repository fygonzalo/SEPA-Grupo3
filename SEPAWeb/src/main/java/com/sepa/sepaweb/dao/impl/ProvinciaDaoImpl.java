package com.sepa.sepaweb.dao.impl;

import com.sepa.sepaweb.dao.ProvinciaDao;
import com.sepa.sepaweb.model.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@Local(ProvinciaDao.class)
public class ProvinciaDaoImpl extends GenericDaoImpl<Provincia> implements ProvinciaDao {

    @Override
    protected Class<Provincia> getEntityClass() {
        return Provincia.class;
    }

    @Override
    public Provincia findByNombre(String nombre) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Provincia> criteriaQuery = builder.createQuery(Provincia.class);
        Root<Provincia> p = criteriaQuery.from(Provincia.class);
        criteriaQuery.select(p).where(builder.equal(p.get(Provincia_.nombre), nombre));
        Query query = em.createQuery(criteriaQuery);
        try {
            return (Provincia) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Provincia> findByComercioSucursales(Comercio comercio) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Provincia> criteriaQuery = builder.createQuery(Provincia.class);
        Root<Provincia> p = criteriaQuery.from(Provincia.class);
        Root<Sucursal> s = criteriaQuery.from(Sucursal.class);

        criteriaQuery.select(p).where(builder.and(
                builder.equal(s.get(Sucursal_.comercio), comercio),
                builder.equal(s.get(Sucursal_.provincia), p)
                )
        ).distinct(true).orderBy(builder.asc(p.get(Provincia_.nombre)));

        Query query = em.createQuery(criteriaQuery);
        return (List<Provincia>) query.getResultList();
    }
}
