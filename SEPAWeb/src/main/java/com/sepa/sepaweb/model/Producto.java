package com.sepa.sepaweb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "producto")
public class Producto extends BaseEntity {
    @NotNull
    @Size(max=50)
    @Column(unique = true)        
    String nombre;
    
    @NotNull
    @Size(max=50)
    String unidad;
    
    @NotNull      
    Long ean;
    
    @Lob
    byte[] imagen;
           
    @NotNull
    @ManyToOne
    @JoinColumn(name = "idRubro")
    Rubro rubro;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "idMarca")
    Marca marca;

    public String getNombre() {
        return nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public Long getEan() {
        return ean;
    }


    public Rubro getRubro() {
        return rubro;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setEan(Long ean) {
        this.ean = ean;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    
    
     
}
