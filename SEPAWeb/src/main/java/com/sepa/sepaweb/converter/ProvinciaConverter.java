package com.sepa.sepaweb.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sepa.sepaweb.model.Provincia;
import com.sepa.sepaweb.dao.ProvinciaDao;

@ManagedBean
@ViewScoped
public class ProvinciaConverter implements Converter {

    @EJB
    ProvinciaDao provinciaDao;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return (value instanceof Provincia) ? ((Provincia) value).toString() : null;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("[0-9]+")) {
            return null;
        }

        Provincia provincia = provinciaDao.findById(Long.parseLong(value));
        return provincia;
    }
}