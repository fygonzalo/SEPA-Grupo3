package com.sepa.sepaweb.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "provincia")
public class Provincia extends BaseEntity {

    @NotNull
    @Size(max = 60)
    String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia")
    Collection<Ciudad> ciudades;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Collection<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provincia provincia = (Provincia) o;

        return nombre.equals(provincia.nombre);

    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
