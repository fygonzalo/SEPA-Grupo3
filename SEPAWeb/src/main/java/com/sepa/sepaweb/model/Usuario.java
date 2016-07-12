package com.sepa.sepaweb.model;

import com.sepa.sepaweb.model.constraints.Geocoding;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity {

    @Column(unique = true)
    @NotNull
    @Size(min = 8, max = 15)
    String username;

    @NotNull
    @Size(min = 0, max = 32)
    String password;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
