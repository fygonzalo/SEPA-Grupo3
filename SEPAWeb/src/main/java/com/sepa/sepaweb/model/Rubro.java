package com.sepa.sepaweb.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "rubro")
public class Rubro extends BaseEntity {

    @NotNull
    @Column(unique=true)
    @Size(max=50)
    String nombre;

    byte[] imagen; 

    @OneToOne
    @JoinColumn(name = "idRubro")
    Rubro padre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Rubro getPadre() {
        return padre;
    }

    public void setPadre(Rubro padre) {
        this.padre = padre;
    }
        
    
}
