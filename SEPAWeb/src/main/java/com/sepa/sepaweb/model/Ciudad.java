package com.sepa.sepaweb.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ciudad")
public class Ciudad extends BaseEntity {

    @NotNull
    @Size(max = 60)
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProvincia")
    private Provincia provincia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ciudad ciudad = (Ciudad) o;

        if (!nombre.equals(ciudad.nombre)) return false;
        return provincia.equals(ciudad.provincia);

    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + provincia.hashCode();
        return result;
    }
}
