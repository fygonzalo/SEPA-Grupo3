package com.sepa.sepaweb.dao;

import java.util.List;

public interface GenericDao<T> {

    List<T> findAll();
    
    T findById(Long id);
    
    T persist(T entity);
    
    void merge(T entity);
    
    void delete(T entity);


}
