package com.example.dto;

import java.io.Serializable;

public class NamenaUpotrebeVozilaDTO implements Serializable {

    Long id;

    String privatno;
    String poslovno;
    String profesionalno;

    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    private UserDTO userDTO;

    public UserDTO getUser() {
        return userDTO;
    }

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivatno() {
        return privatno;
    }

    public void setPrivatno(String privatno) {
        this.privatno = privatno;
    }

    public String getPoslovno() {
        return poslovno;
    }

    public void setPoslovno(String poslovno) {
        this.poslovno = poslovno;
    }

    public String getProfesionalno() {
        return profesionalno;
    }

    public void setProfesionalno(String profesionalno) {
        this.profesionalno = profesionalno;
    }

}
