package com.sepa.sepaweb.dao.impl;

import com.sepa.sepaweb.dao.SucursalDao;
import com.sepa.sepaweb.model.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

@Stateless
@Local(SucursalDao.class)
public class SucursalDaoImpl extends GenericDaoImpl<Sucursal> implements SucursalDao {
    @Override
    protected Class<Sucursal> getEntityClass() {
        return Sucursal.class;
    }

    public List<Sucursal> findByComercioAndUbicacion(Comercio comercio, Provincia provincia, Ciudad ciudad, String direccion) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Sucursal> criteriaQuery = builder.createQuery(Sucursal.class);
        Root<Sucursal> s = criteriaQuery.from(Sucursal.class);
        criteriaQuery.select(s);

        List<Predicate> predList = new LinkedList<Predicate>();
        if(provincia != null) {
            predList.add(builder.and(builder.equal(s.get(Sucursal_.provincia), provincia)));
            if (ciudad != null) {
                predList.add(builder.and(builder.equal(s.get(Sucursal_.ciudad), ciudad)));
            }
        }
        if (!direccion.equals("")) {
            String[] arrayDireccion = direccion.split(" ");
            for (String stringDireccion: arrayDireccion) {
                predList.add(builder.and(builder.like(s.get(Sucursal_.direccion), "%" + stringDireccion + "%")));
            }
        }

        if(comercio != null) {
            predList.add(builder.and(builder.equal(s.get(Sucursal_.comercio), comercio)));
        }

        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);
        criteriaQuery.where(predArray);

        Query query = em.createQuery(criteriaQuery);

        return query.getResultList();

    }

    @Override
    public void deshabilitarByComercio(Comercio comercio) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Sucursal> criteriaQuery = builder.createCriteriaUpdate(Sucursal.class);
        Root<Sucursal> s = criteriaQuery.from(Sucursal.class);
        criteriaQuery.set(s.get(Sucursal_.habilitado), false).where(builder.equal(s.get(Sucursal_.comercio), comercio));

        Query query = em.createQuery(criteriaQuery);
        query.executeUpdate();
    }
}
