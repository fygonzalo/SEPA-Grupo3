package com.sepa.sepaweb.converter;

import com.sepa.sepaweb.dao.CiudadDao;
import com.sepa.sepaweb.model.Ciudad;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class CiudadConverter implements Converter {

    @EJB
    CiudadDao ciudadDao;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return (value instanceof Ciudad) ? ((Ciudad) value).toString() : null;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("[0-9]+")) {
            return null;
        }

        Ciudad provincia = ciudadDao.findById(Long.parseLong(value));
        return provincia;
    }
}