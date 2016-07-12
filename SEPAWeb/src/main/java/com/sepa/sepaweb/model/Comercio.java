package com.sepa.sepaweb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "comercio")
public class Comercio extends BaseEntity {

    @Column(unique = true)
    @NotNull
    @Size(min = 11, max = 11)
    String cuit;

    @Column(unique = true)
    @NotNull
    @Size(max = 40)
    String nombre;

    @NotNull
    Boolean habilitado = false;

    @NotNull
    Boolean nuevo = true;

    @NotNull
    @Size(max = 50)
    String email;

    @NotNull
    String direccion;

    @NotNull
    @Size(min = 8, max = 15)
    String telefono;

    @NotNull
    @OneToOne
    @JoinColumn(name = "idUsuario")
    Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idCiudad")
    Ciudad ciudad;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "comercio")
    List<Sucursal> sucursales;

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
