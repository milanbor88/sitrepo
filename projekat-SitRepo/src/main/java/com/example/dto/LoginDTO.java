package com.example.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class LoginDTO implements Serializable {

    @NotNull(message = "sifra ne moze biti prazna")
    @Size(min = 1, max = 50, message = "Password cant be under {min} and over {max} ")
    private String password;

    @NotNull(message = "email ne moze biti prazna")
    @Email(message = "{email.badtype}")
    @Size(min = 1, max = 40, message = "Email cant be under {min} and over {max} ")
    private String username;

    private String firstName;

    private String lastName;

    private String promoKod;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPromoKod() {
        return promoKod;
    }

    public void setPromoKod(String promoKod) {
        this.promoKod = promoKod;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
