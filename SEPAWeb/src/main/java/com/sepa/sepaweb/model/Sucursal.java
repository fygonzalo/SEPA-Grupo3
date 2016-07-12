package com.sepa.sepaweb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sucursal")
public class Sucursal extends BaseEntity {

    @NotNull
    Boolean habilitado = false;

    @NotNull
    @Size(max = 50)
    String email;

    @NotNull
    String direccion;

    @NotNull
    @Size(min = 8, max = 15)
    String telefono;

    @NotNull
    double latitud;

    @NotNull
    double longitud;

    @NotNull
    @OneToOne
    @JoinColumn(name = "idUsuario")
    Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idCiudad")
    Ciudad ciudad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idProvincia")
    Provincia provincia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idComercio")
    Comercio comercio;

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sucursal sucursal = (Sucursal) o;

        if (Double.compare(sucursal.latitud, latitud) != 0) return false;
        if (Double.compare(sucursal.longitud, longitud) != 0) return false;
        if (!habilitado.equals(sucursal.habilitado)) return false;
        if (!email.equals(sucursal.email)) return false;
        if (!direccion.equals(sucursal.direccion)) return false;
        if (!telefono.equals(sucursal.telefono)) return false;
        if (!usuario.equals(sucursal.usuario)) return false;
        if (!ciudad.equals(sucursal.ciudad)) return false;
        if (!provincia.equals(sucursal.provincia)) return false;
        return comercio.equals(sucursal.comercio);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = habilitado.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + telefono.hashCode();
        temp = Double.doubleToLongBits(latitud);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitud);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + usuario.hashCode();
        result = 31 * result + ciudad.hashCode();
        result = 31 * result + provincia.hashCode();
        result = 31 * result + comercio.hashCode();
        return result;
    }
}
