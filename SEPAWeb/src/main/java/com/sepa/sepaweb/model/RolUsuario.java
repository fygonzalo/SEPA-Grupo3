package com.sepa.sepaweb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rolusuario")
public class RolUsuario extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    Usuario usuario;

    @NotNull
    @Size(max = 50)
    String rol;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
