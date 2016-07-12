package com.sepa.sepaweb.dao.impl;

import com.sepa.sepaweb.dao.GenericDao;
import com.sepa.sepaweb.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericDaoImpl<T extends BaseEntity> implements GenericDao<T>{

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> queryString = cb.createQuery(getEntityClass());
        queryString.from(getEntityClass());
        TypedQuery<T> query = em.createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public T findById(Long id) {
        return em.find(getEntityClass(), id);
    }

    @Override
    public T persist(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void merge(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        // AIUDAAA
    }

    protected abstract Class<T> getEntityClass();
}
