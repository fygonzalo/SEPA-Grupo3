package com.sepa.sepaweb.bean;

import com.sepa.sepaweb.dao.ComercioDao;
import com.sepa.sepaweb.dao.UsuarioDao;
import com.sepa.sepaweb.model.Comercio;
import com.sepa.sepaweb.model.Usuario;

import java.io.IOException;
import java.security.Principal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AuthBean {

    private Usuario usuario; // The JPA entity.
    private Comercio comercio;

    @EJB
    UsuarioDao usuarioDao;

    @EJB
    ComercioDao comercioDao;

    public Usuario getUsuario() {
        if (usuario == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                usuario = usuarioDao.findByUsername(principal.getName()); // Find User by j_username.
                comercio = comercioDao.findByUsuario(usuario);
            }
        }
        return usuario;
    }

    public Comercio getComercio() {
        if (comercio == null){
            getUsuario();
        }
        return comercio;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

}