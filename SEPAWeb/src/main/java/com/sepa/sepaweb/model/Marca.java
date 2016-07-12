package com.sepa.sepaweb.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "marca")
public class Marca extends BaseEntity {
    @Size(max=50)
    @Column(unique=true)
    @NotNull
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
